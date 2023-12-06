package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss extends GameObject{
	
	private Handler handler;
	Random r = new Random();
	// timer: vertical, timer2: time before it start moving and shooting
	private float timer = 150, timer2 = 100;

	public Boss(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = 0;
		velY = 0.2f;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if (timer <= 0) {
			velY = 0; // stops moving down
		} else {
			timer--;
		}
		
		if (timer <= 0) timer2--;
		if (timer2 <= 0) {
			if (velX == 0) velX = 1;
			// if spawn == 0, shoot a projectile
			int spawn = r.nextInt(15);
			if (spawn == 0) handler.addObject(new BulletBoss(x + 15, y + 15, ID.eBullet, handler));
		}
		
		if (x <= 0 || x >= Game.WIDTH - 88) velX *= -1; 
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(120, 0, 0));
		g.fillRect((int) x, (int) y, 32, 32);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
