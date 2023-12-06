import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PowerUp extends GameObject {

	public PowerUp(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);
	}

	@Override
	public void tick() {
		if (y > Game.HEIGHT) {
			handler.removeObject(this);
		}
		
		y += 1;
		
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObj = handler.object.get(i);
			
			if (tempObj.getID() == ID.Player) {
				if (getBounds().intersects(tempObj.getBounds())) {
					handler.removeObject(this);
					Ball newBall = new Ball(x + 2, y + 2, ID.Ball, handler);
					newBall.velY *= -1;
					handler.addObject(newBall);
					Handler.numBalls++;
					
				}
			}
		}
		
	}
	

	@Override
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int) x, (int) y, 10, 10);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 10, 10);
	}

}
