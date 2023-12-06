import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);
	}

	@Override
	public void tick() {
		x += velX;
		
		x = Game.clamp((int) x, 0, Game.WIDTH - 46);
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect((int) x, (int) y, 45, 10);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 45, 10);
	}
}
