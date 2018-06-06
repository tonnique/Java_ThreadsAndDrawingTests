package threadsAndDrawingTests.frame;

import java.awt.Color;
import java.awt.Graphics;

public class Block {
	Color[] colors = {Color.GRAY, Color.CYAN, Color.GREEN, Color.RED};
	
	private int x = 7;
	private int y = 0;
	private int width;
	private int height;
	
	private Color color;
	int colorIndex = 3; // 3 = Red Color
	 
	 
	public Block(int x, int y, int h, int w, Color c ) {
		this.x = x;
		this.y = y;
		this.height = h;
		this.width = w;	
		this.color = c;		 
	}
	
	public void setX(int x) {
		this.x = x;
	}
	 
	public void setY(int y) {
		this.y = y;		 
	}
	 
	public int getX() {return x;}
	public int getY() {return y;}
	public Color getColor() {return color;}
	public void setColor(Color c) {color = c;}
	 
	public void paintBlock(Graphics g) {	
		 g.setColor(color);
		 g.fillRect(x, y, width, height);
		 g.setColor(Color.BLACK);
		 g.drawRect(x, y, width, height);		 
	}
	
	public void changeColor() {			
		colorIndex = (colorIndex + 1) % colors.length;			
		setColor(colors[colorIndex]);
	}
}