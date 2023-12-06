import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Brick extends GameObject {
	private int health = 3, colorValue = 255, spawn;
	private Random r = new Random();

	public Brick(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);
		spawn = r.nextInt(4);
	}

	@Override
	public void tick() {
		if (health == 0) {
			handler.removeObject(this);
			Handler.numBricks--;
			if (spawn == 0) {
				handler.addObject(new PowerUp(x, y, ID.PowerUp, handler));
			}
		}
		collision();
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
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObj = handler.object.get(i);
			
			if (tempObj.getID() == ID.Ball) {
				if (getBounds().intersects(tempObj.getBounds())) {
					health--;
					colorValue -= 85;
					HUD.score += 10;
				}
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(colorValue, colorValue, colorValue));
		g.fillRect((int) x, (int) y, 25, 20);
//		g.fillRect((int) x, (int) y, 100, 100);
	}
	

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 25, 20);
//		return new Rectangle((int) x, (int) y, 100, 100);	
	}

}
