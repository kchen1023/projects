package main;

//import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private int scoreKeep = 0;
//	private Random random = new Random();
	
	public Spawn (Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		scoreKeep++;
		
		if (hud.getScore() == 100) handler.addObject(new Enemy(70, -100, 2, 0, ID.Enemy, handler));
		if (hud.getScore() == 130) handler.addObject(new Enemy(70, -100, 2, 0, ID.Enemy, handler));
		if (hud.getScore() == 160) handler.addObject(new Enemy(70, -100, 2, 0, ID.Enemy, handler));
		if (hud.getScore() == 190) handler.addObject(new Enemy(70, -100, 2, 0, ID.Enemy, handler));
		if (hud.getScore() == 210) handler.addObject(new Enemy(70, -100, 2, 0, ID.Enemy, handler));
		if (hud.getScore() == 240) handler.addObject(new Enemy(70, -100, 2, 0, ID.Enemy, handler));

		if (scoreKeep >= 100) {
			scoreKeep = 0;
			// adds new enemy every 1000 pts
		}
	}

}
