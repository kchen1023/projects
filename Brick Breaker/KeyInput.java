import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	private Handler handler;
	private boolean[] keyDown = new boolean[2];
	Game game;
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
		
		keyDown[0] = false; // a
		keyDown[1] = false; // d
		
	}
	
	public void keyPressed(KeyEvent e) {
		// reads num value corresponding with pressed key
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObj = handler.object.get(i);
			
			if (tempObj.getID() == ID.Player) {
				// go left
				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) { tempObj.setVelX(-3); keyDown[0] = true; }
				// go left fast
				if (key == KeyEvent.VK_SHIFT && keyDown[0]) tempObj.velX = -7;
				// go right
				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) { tempObj.setVelX(3); keyDown[1] = true; }
				// go right fast
				if (key == KeyEvent.VK_SHIFT && keyDown[1]) tempObj.velX = 7;
				
			}
			
		}
		
		// pause
		if (key == KeyEvent.VK_M) {
			if (game.gameState == STATE.Game) {
				if (Game.paused) {
					Game.paused = false;
				} else {
					Game.paused = true;
				}
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
				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyDown[0] = false;
				if (key == KeyEvent.VK_SHIFT && keyDown[0]) tempObj.velX = -3;
				
				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyDown[1] = false;
				if (key == KeyEvent.VK_SHIFT && keyDown[1]) tempObj.velX = 3;

				if (!keyDown[0] && !keyDown[1]) { tempObj.setVelX(0); }
			}
			

		}
	}
}
