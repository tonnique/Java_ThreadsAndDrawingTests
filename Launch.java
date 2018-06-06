package threadsAndDrawingTests;

import java.awt.EventQueue;
import java.util.Scanner;

import threadsAndDrawingTests.frame.*;


public class Launch {
	
	private static DrawingFrame frame = null;
	static int answer = 0;
		
	public static void main(String[] args) {
		
		boolean debug = false; // set this to true if you do not want to see the dialogs when program is started 										  	
		int debugInputValue = 3; // and set the value you want the program to select when you run in 
										   // debug mode and it will chose it bypassing the dialogs
		
		Scanner scanner = new Scanner(System.in);		
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
		
				if (debug) {
					processUserInput(debugInputValue);
					return;
				}
				
				do {
					System.out.println("Please select a choise: ");
					System.out.println("\t 1: for launching DrawingTest with one color");
					System.out.println("\t 2: for launching DrawingTest with two colors");
					System.out.println("\t 3: for launching DrawingTest with multiple colors");
						
					while(!scanner.hasNextInt()) {
						System.out.println("Please select correct integer value");
						scanner.next();
					} 
					answer = scanner.nextInt();
				
				} while (!processUserInput(answer));				
			}
		});
		
		scanner.close();
	}
	
	private static boolean processUserInput(int choice) {
		boolean result = false;
		
		switch (choice) {
		case 1:
			frame = new DrawingFrameOneColor();
			result = true;
			break;
		case 2:
			frame = new DrawingFrameTwoColors();
			result = true;
			break;
		case 3:
			frame = new DrawingFrameManyColors();
			result = true;
			break;	
			
		default:
			result = false;
			break;
		}
		return result;
	}
}
