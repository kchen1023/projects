package main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	// protected: can only be accessed by children
	protected float x, y, velX, velY;
	protected ID id;
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		
	}
	
	public abstract void tick();
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
	
	public ID getID() {
		return id;
		
	}
	
	public void setID(ID id) {
		this.id = id;
		
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

}
