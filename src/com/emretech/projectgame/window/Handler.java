package com.emretech.projectgame.window;

import java.awt.*;
//import java.awt.image.BufferStrategy;
import java.util.*;
import com.emretech.projectgame.framework.GameObject;
import com.emretech.projectgame.framework.ObjectId;


public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;

	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}
	
	
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			if (tempObject.getId() == ObjectId.Block) {
				tempObject.renderLayout(g, new Color(0,0,0));
				tempObject.render(g);
				
			} 
			else
				tempObject.render(g);
		}
	}
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	/*public void createLevel() {
		for (int xx = 0; xx < Game.WIDTH + 32; xx += 32)
			addObject(new Block(xx, Game.HEIGHT - 64, ObjectId.Block));
		for (int xx = 0; xx < Game.HEIGHT - 150; xx += 32)
			addObject(new Block(xx + 125, (Game.WIDTH / 2)- 32, ObjectId.Block));
		for (int xx = 0; xx < Game.WIDTH + 32; xx += 32)
			addObject(new Block(Game.WIDTH - 128, xx, ObjectId.Block));
		for (int xx = 0; xx < Game.WIDTH + 32; xx += 32)
			addObject(new Block(75, xx, ObjectId.Block));
	}*/
	
}
