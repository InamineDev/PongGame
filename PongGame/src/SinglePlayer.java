import javax.swing.Timer;

import java.awt.Color;

import javax.swing.JFrame;


public class SinglePlayer 
{
	static Timer timer;
	static DrawHere d = new DrawHere();
	static int height = 800;
	static int width = 750;
	static int startDelay = 200;
	
	
	public void open()
	{
		// Set up jFrame window for drawing
		JFrame frame = new JFrame("Pong! Single Player");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setContentPane(d);
		frame.getRootPane().setBackground(Color.BLACK);
		// Set up Timer
		timer = new Timer(1, d); // Set time, and this object gets event
		timer.setInitialDelay(startDelay); // Initial wait
		timer.setCoalesce(true); // Don't stack up events
		timer.start(); // Start the timer object
	}
	
}
