import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private int spawnBallX;
	
	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
		spawnBallX = r.nextInt(700);
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX(), my = e.getY();
		// play button
		if (game.getState() == STATE.Menu) {
			if (mouseOver(mx, my, 282, 170, 140, 50)) {
				int i, j, xLocation, yLocation = 20;
				game.setState(STATE.Game);
				// add player
				handler.addObject(new Player(335, 420, ID.Player, handler));
				// add bricks
//				handler.addObject(new Brick(100, 100, ID.Brick, handler));
				for (i = 0; i < 4; i++) {
					xLocation = 27;
					for (j = 0; j < 15; j++) {
						handler.addObject(new Brick(xLocation, yLocation, ID.Brick, handler));
						xLocation += 45;
					}
					yLocation += 30;
				}
				// add ball
				handler.addObject(new Ball(spawnBallX, 220, ID.Ball, handler));
			} else if (mouseOver(mx, my, 282, 230, 140, 50)) { // help
				game.setState(STATE.Help);
			} else if (mouseOver(mx, my, 282, 290, 140, 50)) { // quit
				System.exit(1);
			}
		}
		
		// back button inside help
		if (game.getState() == STATE.Help) {
			if (mouseOver(mx, my, 282, 360, 140, 50)) {
				game.setState(STATE.Menu);
			}
		}
				
		// dead
		if (game.getState() == STATE.Dead) {
			if (mouseOver(mx, my,282, 300, 140, 50)){ // play again
				game.setState(STATE.Game);
				int i, j, xLocation, yLocation = 20;
				game.setState(STATE.Game);
				// add player
				handler.addObject(new Player(335, 420, ID.Player, handler));
				// add bricks
				for (i = 0; i < 4; i++) {
					xLocation = 27;
					for (j = 0; j < 15; j++) {
						handler.addObject(new Brick(xLocation, yLocation, ID.Brick, handler));
						xLocation += 45;
					}
					yLocation += 30;
				}
				// add ball
				handler.addObject(new Ball(spawnBallX, 220, ID.Ball, handler));
				// reset score
				HUD.score = 0;
			} else if (mouseOver(mx, my, 282, 360, 140, 50)) { //go back to menu
				game.setState(STATE.Menu);
			}
		}
		
		// end
		if (game.getState() == STATE.End) {
			if (mouseOver(mx, my,282, 300, 140, 50)){ // play again
				game.setState(STATE.Game);
				int i, j, xLocation, yLocation = 20;
				game.setState(STATE.Game);
				// add player
				handler.addObject(new Player(335, 420, ID.Player, handler));
				// add bricks
				for (i = 0; i < 6; i++) {
					xLocation = 27;
					for (j = 0; j < 15; j++) {
						handler.addObject(new Brick(xLocation, yLocation, ID.Brick, handler));
						xLocation += 45;
					}
					yLocation += 30;
				}
				// add ball
				handler.addObject(new Ball(spawnBallX, 220, ID.Ball, handler));
				// reset score
				HUD.score = 0;
			} else if (mouseOver(mx, my, 282, 360, 140, 50)) { //go back to menu
				game.setState(STATE.Menu);
			}
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
		if (game.getState() == STATE.Menu) { // main menu
			g.setFont(new Font("monospaced", 4, 40));
			g.setColor(Color.white);
			g.drawRect(282, 170, 140, 50);
			g.drawString("PLAY", 305, 208);
			g.drawRect(282, 230, 140, 50);
			g.drawString("HELP", 305, 268);
			g.drawRect(282, 290, 140, 50);
			g.drawString("EXIT", 305, 328);
			
			g.setFont(new Font("monospaced", 3, 60));
			g.setColor(Color.orange);
			g.drawString("BRICK BREAKER", 125, 105);
			
			g.setFont(new Font("arial", 4, 15));
			g.setColor(Color.orange);
			g.drawString("game inspired by RealTutsGML", 250, 400);
			
		} else if (game.getState() == STATE.Help) { // help menu
			g.setFont(new Font("monospaced", 4, 40));
			g.setColor(Color.white);
			g.drawRect(282, 360, 140, 50);
			g.drawString("BACK", 305, 396);
			
			g.setFont(new Font("arial", 4, 15));
			g.setColor(Color.gray);
			g.drawString("this game is based off of classic brick breaker arcade games", 140, 100);
			g.drawString("use WASD and the arrow keys to move the yellow block (you)", 142, 130);
			g.drawString("hold down shift while moving to gain aminor speed boost", 145, 160);
			g.drawString("deflect the bouncing ball(s) into the bricks to break them", 160, 190);
			g.drawString("collect power ups (blue squares) to get more balls", 180, 220);
			g.drawString("game is over when all the bricks are broken or if all the balls are lost", 125, 250);
			
		} else if (game.getState() == STATE.Dead) { // when Handler.numBalls == 0
			g.setFont(new Font("arial", 4, 42));
			g.setColor(Color.red);
			g.drawString("YOU LOST! try again?", 140, 160);
			if (HUD.score == 0) {
				g.drawString("your score: " + HUD.score, 230, 220);
			} else if (HUD.score >= 10) {
				g.drawString("your score: " + HUD.score, 220, 220);
			} else if (HUD.score >= 100) {
				g.drawString("your score: " + HUD.score, 150, 220);
			} else if (HUD.score >= 1000) {
				g.drawString("your score: " + HUD.score, 100, 220);
			}
			
			g.setFont(new Font("monospaced", 4, 40));
			g.setColor(Color.white);
			g.drawRect(282, 300, 140, 50);
			g.drawString("AGAIN", 293, 337);
			g.drawRect(282, 360, 140, 50);
			g.drawString("MENU", 305, 397);
			
		} else if (game.getState() == STATE.End) { // when all bricks are cleared
			// WIN
			g.setFont(new Font("arial", 1, 50));
			g.setColor(Color.yellow);
			g.drawString("WINNER!", 240, 150);
			g.setFont(new Font("arial", 1, 40));
			g.drawString("thanks for playing!", 170, 200);
			
			g.setFont(new Font("monospaced", 4, 40));
			g.setColor(Color.white);
			g.drawRect(282, 300, 140, 50);
			g.drawString("AGAIN", 293, 337);
			g.drawRect(282, 360, 140, 50);
			g.drawString("MENU", 305, 397);
		}
	}
}
