package com.emretech.projectgame.objects;

import java.awt.*;
import java.util.LinkedList;

import com.emretech.projectgame.framework.GameObject;
import com.emretech.projectgame.framework.ObjectId;

public class BrownBox extends GameObject{
	
	private float width = 42, height = 42;

	public BrownBox(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(216,111,13));
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)), (int) ((int)y+(height/2)), (int)width/2, (int)height/2);
	}

	@Override
	public void renderLayout(Graphics g, Color c) {
		
	}

}
