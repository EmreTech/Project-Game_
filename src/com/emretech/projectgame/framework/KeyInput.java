package com.emretech.projectgame.framework;

import java.awt.event.*;
import com.emretech.projectgame.framework.GameStateManager;
import com.emretech.projectgame.window.Handler;
import com.emretech.projectgame.window.Game;

public class KeyInput extends KeyAdapter{

	Handler handler;
	GameStateManager gameState;
	public KeyInput(Handler handler, GameStateManager gameState) {
		this.handler = handler;
		this.gameState = gameState;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (!gameState.isPaused()) {
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getId() == ObjectId.Player) {
					if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) tempObject.setVelX(5);
					if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) tempObject.setVelX(-5);
					if (key == KeyEvent.VK_SPACE && !tempObject.isJumping()) { 
						tempObject.setJumping(true);
						tempObject.setdoubleJumping(false);
						tempObject.setVelY(-6);
					}
					if (key == KeyEvent.VK_SHIFT && tempObject.isJumping() && !tempObject.isdoubleJumping()) {
						tempObject.setdoubleJumping(true);
						tempObject.setVelY(-6);
					}
					if (key == KeyEvent.VK_ENTER && Game.super_speed != true) Game.super_speed = true;
					if (key == KeyEvent.VK_UP && !tempObject.isJumping()) {
						tempObject.setJumping(true);
						tempObject.setdoubleJumping(false);
						tempObject.setVelY(-6);
					}
					
				}
			}
			if (key == KeyEvent.VK_P && !gameState.isPaused()) {
				gameState.setPaused(true);
				System.out.println(gameState.paused);
			}
			if (key == KeyEvent.VK_ESCAPE) {
				System.exit(1);
			}
		}
		if (key == KeyEvent.VK_ENTER && gameState.isPaused()) {
			gameState.setPaused(false);
			System.out.println(gameState.paused);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
				if (key == KeyEvent.VK_ENTER) Game.super_speed = false;

			}
		}


	}
}
