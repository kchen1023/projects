package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 6224761727844878306L;
	public static final int WIDTH = 720, HEIGHT = 480;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Menu menu;
	private HUD hud;
	private Spawn spawn;
	public STATE gameState;
	public static boolean paused;

	public Game() {
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		new Window(WIDTH, HEIGHT, "Space Fighter", this);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		spawn = new Spawn(handler, hud);
		gameState = STATE.Menu;
		
	}
	
	public void setState(STATE state) {
		gameState = state;
	}
	
	public STATE getState() {
		return gameState;
	}
	
	public synchronized void start() {
		thread  = new Thread(this);
		thread.start();
		running = true;
		
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	// popular game loop
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
		stop();
		
	}
	
	private void tick() {
		if (gameState == STATE.Game) { 
			if (!paused) {
				hud.tick();
				spawn.tick();
				handler.tick();
			}
			handler.tick();
		} else if (gameState == STATE.Menu || gameState == STATE.Dead) {
			menu.tick();
			handler.tick();
		}
		
	}
	
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	
		handler.render(g);
		
		if (paused) {
			g.setColor(Color.red);
			g.drawString("PAUSED", 645, 30);
		}
		
		if (gameState == STATE.Game) {
			hud.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Dead || gameState == STATE.End) {
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
		
	}
	
	// makes sure our x and y values doesnt go past min and max
	public static float clamp(float var, float min, float max) {
		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}
	}
	
	public static void main(String[] args) {
		new Game();
		
	}

}
