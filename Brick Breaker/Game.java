import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -8921419424614180143L;
	public static final int WIDTH = 720, HEIGHT = 480;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private HUD hud;
	private Menu menu;
	public STATE gameState;
	public static boolean paused;
	
	public void setState(STATE state) {
		gameState = state;
	}
	
	public STATE getState() {
		return gameState;
	}
	
	public Game() {
		handler = new Handler();
		hud = new HUD(handler);
		menu = new Menu(this, handler);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		new Window(WIDTH, HEIGHT, "Brick Breaker", this);
		gameState = STATE.Menu;
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

	@Override
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
			if (running) render();
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
		stop();
	}
	
	private void tick() {
		if (gameState == STATE.Game) { 
			if (!paused) {
				handler.tick();
			}
			
			if (Handler.numBalls == 0) {
				gameState = STATE.Dead;
				menu.tick();
				handler.tick();
				handler.object.clear();
				Handler.numBalls = 1;
			}
			
			if (Handler.numBricks == 0) {
				gameState = STATE.End;
				menu.tick();
				handler.tick();
				handler.object.clear();
				Handler.numBricks = 90;
			}
			
		} else if (gameState == STATE.Menu) {
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
		
		Graphics g =  bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if (paused) {
			g.setColor(Color.red);
			g.drawString("PAUSED", 620, 420);
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
	public static int clamp(int var, int min, int max) {
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
