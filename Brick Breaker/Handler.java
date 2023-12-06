import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	public static int numBalls = 1, numBricks = 90;
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObj = object.get(i);
			tempObj.tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObj = object.get(i);
			tempObj.render(g);;
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public int getBalls() {
		return numBalls;
	}
	
	public int getBricks() {
		return numBricks;
	}

//	public void clearEnemies() {
//		for (int i = object.size() - 1; i > -1; i--) {
//			GameObject temp = object.get(i);
//			if (temp.getID() != ID.Player) {
//				object.remove(i);
//			}
//		}
//		
//	}
}
