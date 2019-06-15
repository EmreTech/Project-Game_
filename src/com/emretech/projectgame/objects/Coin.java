package com.emretech.projectgame.objects;

import java.awt.*;
import java.util.LinkedList;

import com.emretech.projectgame.framework.*;

public class Coin extends GameObject {
	
	private float width = 32, height = 32;
	
	public Coin(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
	}
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.blue);
		g2d.draw(getBounds());
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)), (int) ((int)y+(height/2)), (int)width/2, (int)height/2);
	}
	

	@Override
	public void renderLayout(Graphics g, Color c) {
		return;
		
	}

}
