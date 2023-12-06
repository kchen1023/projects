package main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	private static final long serialVersionUID = -4810618286807932601L;

	
	public Window(int width, int height, String title, Game game) {
		// frame of the window, built-in library in java
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		// makes sure the x button closes application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// cannot resize window
		frame.setResizable(false);
		// loads window in the middle of screen
		frame.setLocationRelativeTo(null);
		// adds Game class into frame
		frame.add(game);
		frame.setVisible(true);
		// in multiple threads, use start because run will wait until thread is finished to run next one
		// start will allow concurrency
		game.start();

		
	}
}
