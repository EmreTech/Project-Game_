package com.emretech.projectgame.objects;

import java.awt.*;
import java.util.LinkedList;

import com.emretech.projectgame.framework.*;
import com.emretech.projectgame.window.Handler;
import com.emretech.projectgame.window.Game;

public class Player extends GameObject{
	
	private float width = 48, height = 96;
	
	private double gravity = 0.05f;
	private final float MAX_SPEED = 10;
	
	
	private Handler handler;
	
	public Player(float x, float y, Handler handler,ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		// TODO Auto-generated constructor stub
	}

	
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		//System.out.println(y);
		
		if (falling || jumping) {
			velY += gravity;
			
			if (velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		
		Collision(object);
		
		controlSpeed(velX);
		//System.out.println(Game.coins);
	}
	private void controlSpeed(float Velx) {
		if (Game.super_speed) {
			if (Velx == 5)
				setVelX(12);
			else if (Velx == -5)
				setVelX(-12);
		}
		else
			return;
	}
	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Block) {
				//Top
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;	
				}
				else
					falling = true;
				//Bottom
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}
				else
					falling = true;
				//Right
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width - 3;	
				}
				else
					falling = true;
				//Left
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 35;	
				}
				else
					falling = true;
			}
			if (tempObject.getId() == ObjectId.Coin) {
				// Top
				if (getBoundsTop().intersects(tempObject.getBounds())) {
						Game.coins += 1;
						handler.removeObject(tempObject);
				}
				// Bottom
				if (getBounds().intersects(tempObject.getBounds())) {
						Game.coins += 1;
						handler.removeObject(tempObject);
				}
				// Right
				if (getBoundsRight().intersects(tempObject.getBounds())) {
						Game.coins += 1;
						handler.removeObject(tempObject);
				}
				// Left
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
						Game.coins += 1;
						handler.removeObject(tempObject);
				}
			}
			if (tempObject.getId() == ObjectId.BrownBox) {
				float boxX = tempObject.getX();
				float boxY = tempObject.getY();
				
				// Top
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					
					handler.addObject(new Coin(boxX, boxY, ObjectId.Coin));
					handler.removeObject(tempObject);
				}
				// Bottom
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
				}
				// Right
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width - 3;	
				}
				else
					falling = true;
				// Left
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 35;	
				}
				else
					falling = true;
			}
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.draw(getBounds());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());
		//g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)), (int) ((int)y+(height/2)), (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)), (int)y, (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x+width-5), (int)y+5, (int)5, (int)height-10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+5, (int)5, (int)height-10);
	}

	public void renderLayout(Graphics g, Color c) {
		return;
	}
	
}
