package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	Game game;
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
		
	}
	
	public void keyPressed(KeyEvent e) {
		// reads num value corresponding with pressed key
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObj = handler.object.get(i);
			
			if (tempObj.getID() == ID.Player) {
				if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) { tempObj.setVelY(-2); keyDown[0] = true; }
				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) { tempObj.setVelX(-2); keyDown[1] = true; }
				if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) { tempObj.setVelY(2); keyDown[2] = true; }
				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) { tempObj.setVelX(2); keyDown[3] = true; }
				
				if (key == KeyEvent.VK_SPACE  || key == KeyEvent.VK_ENTER) handler.addObject(new BulletPlayer(tempObj.getX() + 4, tempObj.getY() - 1, ID.pBullet, handler));
			}
			
		}
		
		// pause
		if (key == KeyEvent.VK_M) {
			if (game.gameState == STATE.Game) {
				if (Game.paused) {
					Game.paused = false;
				} else Game.paused = true;
			}
			
		}
		
		// exit game with esc key
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObj = handler.object.get(i);
			
			if (tempObj.getID() == ID.Player) {

				if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keyDown[0] = false;
				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyDown[1] = false;
				if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyDown[2] = false;
				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyDown[3] = false;
				// vertical movement
				if (!keyDown[0] && !keyDown[2]) { tempObj.setVelY(0); }
				// horizontal movement
				if (!keyDown[1] && !keyDown[3]) { tempObj.setVelX(0); }
			}
			

		}
	}
}
