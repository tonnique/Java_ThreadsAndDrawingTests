package threadsAndDrawingTests.frame;

import java.awt.Color;

/**
 * This program was written while I was learning how to use Thread class.
 * It demonstrates how to draw a moving block on the frame which will have different colors while the block is moving on the frame.
 * It uses two Threads. One thead is for moving the block and another one is for changing colors for the object
 * 
 * @author Antony
 */
public class DrawingFrameManyColors extends DrawingFrame {
		
		private static final long serialVersionUID = 1L;
		
		private Block block = new Block(0, 0, BLOCK_SIZE, BLOCK_SIZE, Color.RED );
		
		
		int currentMove = 0; // currentMove is a number of steps that thread does for moving the block
		int maxMovingSteps = 270;
		
		Thread runningThread;
		
		public DrawingFrameManyColors() {
			super();			
			start();
		}	
		
		public void start() {
			
			// BEGIN: stoping existing threads before restart
			if (runningThread != null) {
				runningThread.interrupt();				
				runningThread = null;
			}			
			// END: stoping existing threads before restart
			
			int runningTimerSleepDelay = 40;
			
			currentMove = 0;			
			block.setX(0);
			block.setY(0);
			canvas.setBlock(block);
			
			runningThread = new Thread(new Runnable() {
				
				private Thread colorsThread;
				
				@Override
				public void run() {
					
					if (colorsThread == null) {						
						colorsThread = new Thread(new ColorsThread());
						colorsThread.start();
					}
																					
					while (currentMove < maxMovingSteps ) {
						
						try {
							Thread.sleep(runningTimerSleepDelay);
							
						} catch (InterruptedException e) {							
							Thread.currentThread().interrupt();
							colorsThread.interrupt();
						}
						
						canvas.repaint();
										
						block.setX(block.getX() + 1);
						block.setY(block.getY() + 1);
						
						currentMove++;
					}
				}				
			});

			runningThread.start();			
		}
		
		class ColorsThread implements Runnable {

			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					
					try {
						Thread.sleep(750);
						
						block.changeColor();
						canvas.repaint();						
						
					} catch (InterruptedException e) {						
						Thread.currentThread().interrupt();
					}
				}
		 }
	}	
}