package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX(), my = e.getY();
		// play button
		if (game.getState() == STATE.Menu && mouseOver(mx, my, 282, 170, 140, 50)) {
			game.setState(STATE.Game);
			handler.addObject(new Player(345, 350, ID.Player, handler));

		}
		
		// help 
		if (game.getState() == STATE.Menu && mouseOver(mx, my, 282, 230, 140, 50)) {
			game.setState(STATE.Help);
		}
		
		// back button inside help
		if (game.getState() == STATE.Help) {
			if (mouseOver(mx, my, 282, 360, 140, 50)) {
				game.setState(STATE.Menu);
			}
		}
				
		// quit
		if (game.getState() == STATE.Menu && mouseOver(mx, my, 282, 290, 140, 50)) {
			System.exit(1);
		}
		
		// end
		if (game.getState() == STATE.End) {
			
		}

	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			}
		} 
		
		return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		Font font;
		if (game.getState() == STATE.Menu) {
			font = new Font("monospaced", 4, 40);
			g.setFont(font);
			g.setColor(Color.white);
			g.drawRect(282, 170, 140, 50);
			g.drawString("PLAY", 305, 208);
			g.drawRect(282, 230, 140, 50);
			g.drawString("HELP", 305, 268);
			g.drawRect(282, 290, 140, 50);
			g.drawString("EXIT", 305, 328);
			
			font = new Font("monospaced", 3, 60);
			g.setFont(font);
			g.setColor(Color.orange);
			g.drawString("SPACE FIGHTER", 125, 105);
			
			font = new Font("arial", 4, 15);
			g.setFont(font);
			g.setColor(Color.orange);
			g.drawString("game inspired by RealTutsGML", 250, 400);
			
		} else if (game.getState() == STATE.Help) {
			font = new Font("monospaced", 4, 40);
			g.setFont(font);
			g.setColor(Color.white);
			g.drawRect(282, 360, 140, 50);
			g.drawString("BACK", 305, 396);
			
			font = new Font("arial", 4, 15);
			g.setFont(font);
			g.setColor(Color.gray);
			g.drawString("This game is based off of classic arcade games of Galaga and Space Invaders", 85, 100);
			g.drawString("You will travel through space while fighting against aliens", 150, 150);
			g.drawString("Use WASD or the arrow keys to move your character", 164, 200);
			g.drawString("Press space to shoot, damage, and destroy enemies", 165, 250);
			g.drawString("The game will end when you die or the boss is defeated", 158, 300);
			
		} else if (game.getState() == STATE.Dead) {
			// LOSE
			font = new Font("arial", 4, 15);
			g.setFont(font);
			g.setColor(Color.gray);
			g.drawString("DEAD", 100, 100);
			
		} else if (game.getState() == STATE.End) { // only when the boss is defeated
			// WIN
			font = new Font("arial", 4, 15);
			g.setFont(font);
			g.setColor(Color.gray);
			g.drawString("WIN", 100, 100);
		}
		
		
	}
	
	

}
