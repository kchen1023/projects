package main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int HEALTH = 100;
	private int greenValue = 255, score = 0;

	public void tick() {
		HEALTH = clamp(HEALTH, 0, 100);
		score++;
		
	}
	
	// makes sure our x and y values doesnt go past min and max
	public int clamp(int var, int min, int max) {
		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}
	}
	
	public void render(Graphics g) {
		// background
		g.setColor(Color.gray);
		g.fillRect(10, 424, 200, 8);
		// actual health
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(10, 424, HEALTH * 2, 8);
		// border
		g.setColor(Color.white);
		g.drawRect(10, 424, 200, 8);
		
		g.drawString("Score: " + score, 10, 30);
		
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
}
