package com.emretech.projectgame.objects;

import java.awt.*;
import com.emretech.projectgame.framework.ObjectId;
import java.util.LinkedList;

import com.emretech.projectgame.framework.GameObject;

public class Block extends GameObject{

	public Block(float x, float y, ObjectId id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}
 
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, 32, 32);
		
	}
	public void tick(LinkedList<GameObject> object) {
		
		
	} 
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32,32);
	}

	public void renderLayout(Graphics g, Color c) {
		g.setColor(c);
		g.drawRect((int)x, (int)y, 32, 32);
	}
	
}
