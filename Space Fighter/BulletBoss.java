package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BulletBoss extends GameObject{
	
	private Handler handler;
	Random r = new Random();

	public BulletBoss(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX =  (float) ((Math.random() * 2) - 0.5);
		velY = 2;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if (y >= Game.HEIGHT) handler.removeObject(this);

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
