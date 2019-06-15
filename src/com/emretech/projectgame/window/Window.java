package com.emretech.projectgame.window;

import java.awt.*;
import javax.swing.*;

public class Window {
	public Window(int w, int h, String title, Game game) {
		JFrame frame = new JFrame(title);
		int windowWidth = 0;
		int windowHeight = 0;
		game.setPreferredSize(new Dimension(w,h));
		game.setMaximumSize(new Dimension(windowWidth,windowHeight));
		game.setMinimumSize(new Dimension(windowWidth,windowHeight));
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		windowWidth = frame.getWidth();
		windowHeight = frame.getHeight();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
}
