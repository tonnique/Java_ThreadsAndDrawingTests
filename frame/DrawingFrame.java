package threadsAndDrawingTests.frame;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public abstract class DrawingFrame extends JFrame implements ActionListener  {
	
	private static final long serialVersionUID = 1L;
	
	private int width = 400;
	private int height = 300;
	final int BLOCK_SIZE = 20;
	
	private JMenuBar mnuBar = new JMenuBar();
	private JMenuItem mnuRestart = new JMenuItem("Restart");
	
	TestCanvas canvas;
	
	public DrawingFrame() {
		super("Test with drawing and Threads");
		
		canvas = new TestCanvas(width, height);
		
		setComponents();
		
		setLocationRelativeTo(null);		
		setSize(width, height);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
		setVisible(true);
	}
	
	private void setComponents() {
		
		Container c = getContentPane();
		c.add(canvas);
		
		this.setJMenuBar(mnuBar);
		mnuRestart.setActionCommand("Restart");
		mnuRestart.addActionListener(this);
		mnuBar.add(mnuRestart);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		start();		
	}
	
	abstract void start();

}
