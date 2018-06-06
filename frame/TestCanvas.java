package threadsAndDrawingTests.frame;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TestCanvas extends JPanel {
	
	private static final long serialVersionUID = 1L;
	Block block;
	
	public TestCanvas(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}
			
	public TestCanvas(int width, int height, Block b ) {
		this(width, height);
		this.block = b;		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (block != null) {
			block.paintBlock(g);
		}
	}
	
	public void setBlock(Block newBlock) {
		this.block = newBlock;
	}
}
