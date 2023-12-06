import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	private Handler handler;
	public static int score = 0;
	
	public HUD(Handler handler) {
		this.handler = handler;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawString("Score: " + score, 20, 420);
		
		g.drawString("Balls: " + handler.getBalls(), 150, 420);
		
	}
	
	public int getScore() {
		return score;
	}

}
