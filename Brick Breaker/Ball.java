import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends GameObject {
	private Random r = new Random();

	public Ball(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);
		velX = 2.5f;
		velY = 2.5f;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1; 
		if (y <= 0) velY *= -1; 
		if (y > Game.HEIGHT) {
			handler.removeObject(this);
			Handler.numBalls--;
		}
		
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObj = handler.object.get(i);
			
			if (tempObj.getID() == ID.Player && getBounds().intersects(tempObj.getBounds())) {
				if (this.velX <= 2.5) {
					velX += r.nextDouble() + 1;
				} else {
					velX *= r.nextDouble() * 2 - 1;
				}
				
				velY *= -1;
			} else if (tempObj.getID() == ID.Brick) {
				if (getBounds().intersects(tempObj.getBounds())) {
					// checks for left and right sides of a brick rectangle
					velY *= -1;
					
				}
				if (this.getX() == tempObj.x) {
					System.out.println((int) this.getX() + "-------" + (int) tempObj.x + 100);
					System.out.println();
					velX *= -1;
					velY *= -1;
				}
			}
			
		}
		
	}
	
	

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
//		g.drawRect((int) x, (int) y, 10, 10);
		g.drawOval((int) x, (int) y, 10, 10);
		g.fillOval((int) x, (int) y, 10, 10);

	}
	
	

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 10, 10);
	}

}

