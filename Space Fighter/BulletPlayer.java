package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BulletPlayer extends GameObject{
	
	private Handler handler;
	Random r = new Random();

	public BulletPlayer(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		

		velY = 3;
	}

	@Override
	public void tick() {
		y -= velY;
		
		if (y <= 0) handler.removeObject(this);
		
		collision();

	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObj = handler.object.get(i);
			
			if (tempObj.getID() == ID.Enemy || tempObj.getID() == ID.Boss) {
				if (getBounds().intersects(tempObj.getBounds())) {
					handler.removeObject(this);
				}
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int) x, (int) y, 2, 6);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 2, 6);
	}

}
