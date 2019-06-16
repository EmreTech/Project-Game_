package com.emretech.projectgame.framework;

import java.awt.*;
import java.util.*;

public abstract class GameObject {
	
	
	protected float x,y;
	protected ObjectId id;
	protected float velX = 0, velY = 0;
	protected boolean falling = true;
	protected boolean jumping = false;
	protected boolean doubleJumping = false;
	protected boolean leftCollision = false;
	protected boolean rightCollision = false;
	
	public boolean isLeftCollision() {
		return leftCollision;
	}

	public void setLeftCollision(boolean leftCollision) {
		this.leftCollision = leftCollision;
	}

	public boolean isRightCollision() {
		return rightCollision;
	}

	public void setRightCollision(boolean rightCollision) {
		this.rightCollision = rightCollision;
	}
	
	
	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	public boolean isdoubleJumping() {
		return doubleJumping;
	}
	public void setdoubleJumping(boolean doubleJumping) {
		this.doubleJumping = doubleJumping;
	}
	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	public float getVelX() {
		return velX;
	}
	public float getVelY() {
		return velY;
	}
	public void setVelX(float velX) {
		this.velX = velX;
	}
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public ObjectId getId() {
		return id;
	}
	public abstract void renderLayout(Graphics g, Color c);
}
