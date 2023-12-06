package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BulletEnemy extends GameObject{
	
	private Handler handler;
	Random r = new Random();

	public BulletEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = 0;
		velY = 3;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if (y >= Game.HEIGHT) handler.removeObject(this);
		
		collision();
		
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObj = handler.object.get(i);
			
			if (tempObj.getID() == ID.Player) {
				if (getBounds().intersects(tempObj.getBounds())) {
					handler.removeObject(this);
					HUD.HEALTH -= 2;
				}
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(255, 60, 60));
		g.fillRect((int) x, (int) y, 4, 10);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 4, 10);
	}

}
