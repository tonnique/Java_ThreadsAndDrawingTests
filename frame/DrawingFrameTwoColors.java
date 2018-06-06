/* Эта программа (на подобие той что в пакете testWithThread) была написана для изучения работы с потоками Thread.  
* Суть в том, что мне было интересно реализовать движение объекта в потоке и при этом объект должен был менять цвет.
* 
* Здесь при работе потока movingThread (в методе run) каждый раз создается новый поток GreenColorThread, который меняет текущий красный цвет 
* на зеленый. Делолось это специально, поскольку мне было важно понять, как сделать так, чтобы один поток мог дождаться пока второй поток 
* не завершит свою задачу. 
* 
* [Например в игре Columns, поток, который отвечает за игру (повторяется пока игрок не выиграл или проиграл) должен дождаться пока второй поток,
* выделит определенным цветом блоки, которые должны быть удалены, а затем когда второй поток завершится, и выделит блоки, 
* первый поток их может удалить. ] 
*  
* Здесь используется Thread.join - т.е. поток, который отвечает за движение блока, он ожидает, пока второй поток не выполнит свою работу (изменит цвет), 
* - поэтому объект двигается медленно. 
* 
* TODO можно попробовать сделать возможность переносить объект мышкой или ещё добавить новые блоки на поле, ради интереса
*/    

package threadsAndDrawingTests.frame;

import java.awt.*;

public class DrawingFrameTwoColors extends DrawingFrame {
	
	private static final long serialVersionUID = 1L;
	private final Color RED_COLOR = Color.RED;
	private final Color GREEN_COLOR = Color.GREEN;
	
	private Block block = new Block(0, 0, BLOCK_SIZE, BLOCK_SIZE, RED_COLOR );
	
	int currentMove = 0; // currentMove is a number of steps that thread does for moving the block 
	
	Thread movingThread;

	public DrawingFrameTwoColors() {
		super();
		
		canvas.setBlock(block);		
		start();
	}
			
	public void start() {
		int maxMovingSteps = 220;		
		int timerSleepDelay = 20;
				
		// BEGIN: stoping existing threads before restart
			if (movingThread != null) {
				currentMove = maxMovingSteps;
				while(movingThread.isAlive()) {				
				}
				movingThread = null;								
			}			
		// END: stoping existing threads before restart
			
		block.setX(0);
		block.setY(0);	
		currentMove = 0; // reset value for running block from the start
			
		movingThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
															
				while (currentMove < maxMovingSteps) {
					
					try {
						Thread.sleep(timerSleepDelay);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					canvas.repaint();
										
					Thread GreenColorThread = new Thread(new Runnable() {
						
						@Override
						public void run() {
							try {								
								Thread.sleep(350);
								block.setColor(GREEN_COLOR);
								canvas.repaint();
								
							} catch (InterruptedException e) {							
								e.printStackTrace();
							}
						}
					});
					GreenColorThread.start();						
					
					try {
						GreenColorThread.join();
						Thread.sleep(350);
						
					} catch (InterruptedException e) {							
						e.printStackTrace();
					}
					
					block.setColor(RED_COLOR);
									
					block.setX(block.getX()+1);
					block.setY(block.getY()+1);
					
					currentMove++;
				}				
			}
		});
		
		movingThread.start();		
	}
}