package main;

import java.awt.Graphics;
import java.util.LinkedList;

//maintain and render objects
//loops thru all objects in game and individually update and render them to screen
public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObj = object.get(i);
			tempObj.tick();
		}
		
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObj = object.get(i);
			tempObj.render(g);;
		}
		
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
		
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
		
	}

	
}
