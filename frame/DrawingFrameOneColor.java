package threadsAndDrawingTests.frame;

/**
 * Ёта программа была написана мной дл€ изучени€ работы потоков. 
 * —десь на форме рисуетс€ красный квадрат, и он перемещаетс€.
 * ѕеремещение организовано в отдельном потоке и испозуетс€ класс Timer  
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class DrawingFrameOneColor extends DrawingFrame {

	private Block block = new Block(0, 0, BLOCK_SIZE, BLOCK_SIZE, Color.RED);	
	private Timer timer;
	private int value = 0;
	
	
	public DrawingFrameOneColor() {
		super();
		canvas.setBlock(block);
			
		start();
	}	
			
	public void start() {
		
		value = 0;
		block.setX(0);
		block.setY(0);
		
		
			if (timer != null) {
				timer.stop();
			}
			timer = new Timer(20, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				
				canvas.repaint();
				
				block.setX(block.getX()+1);
				block.setY(block.getY()+1);				
				value++;
				
				if (value > 200) timer.stop(); 
			}
		});

		
		timer.start();
	}
}