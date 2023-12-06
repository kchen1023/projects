package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	
	Handler handler;
	
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = clamp(x, 0, Game.WIDTH - 30);
		y = clamp(y, Game.HEIGHT / 2 - 52, Game.HEIGHT - 52);
		
		collision();
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect((int) x, (int) y, 16, 16);
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObj = handler.object.get(i);
			
			if (tempObj.getID() == ID.Enemy) {
				if (getBounds().intersects(tempObj.getBounds())) {
					// collision code
					HUD.HEALTH -= 2;
				}
			}
		}
		
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
		
	}
	
	// makes sure our x and y values doesnt go past min and max
	public static float clamp(float var, float min, float max) {
		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}
	}

}
