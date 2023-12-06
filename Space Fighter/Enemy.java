package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject{
	
	private Handler handler;
	private float radius, angle;
	private int health = 3;
	Random r = new Random();
	
	public Enemy(float x, float y, float radius, float angle, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.radius = radius;
		this.angle = angle;

	}

	@Override
	public void tick() { 
		x += radius * Math.cos(angle);
		y += radius * Math.sin(angle);
		angle += 0.01;
		
		if (this.getX() >= 0 && this.getY() >= 0) {
			int spawn = r.nextInt(500);
			if (spawn == 0) handler.addObject(new BulletEnemy(x + 5, y + 5, ID.eBullet, handler));
		}
		
		if (health == 0) {
			handler.removeObject(this);
		}
		
		collision();
		
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObj = handler.object.get(i);
			
			if (tempObj.getID() == ID.pBullet) {
				if (getBounds().intersects(tempObj.getBounds())) {
					health--;
				}
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, 16, 16);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

}
