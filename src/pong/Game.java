package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{

	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;

	private static final long serialVersionUID = 1L;
	public static final int width = 160;
	public static final int height = 120;
	public static final int scale = 3;
	public BufferedImage layer = new BufferedImage(width,height,
												   BufferedImage.TYPE_INT_RGB);
	
	public Player player;
	public Enemy enemy;
	public Ball ball;
	
	public Game() {
		int ballSize = 4;
		int barwid = 40;
		int barhei = 5;
		this.setPreferredSize(new Dimension(width*scale,height*scale));
		this.addKeyListener(this);
		player = new Player(barwid,barhei);
		enemy = new Enemy(barwid,barhei);
		ball = new Ball(ballSize,ballSize);
		enemy.getBall(ball);
		ball.getPlayer(player);
		ball.getEnemy(enemy);
	}
	
	public static void main(String[] args) {
		System.out.println("Iniciando Jogo Pong");
		Game game = new Game();
		initFrame(game);
		game.start();
	}
	
	private static void initFrame(Game game) {
		frame = new JFrame("pong");
		frame.setResizable(false);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0.0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while(isRunning) {
			long now = System.nanoTime();
			delta+= (now-lastTime)/ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() -timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer = System.currentTimeMillis();
			}
			
		}
		stop();
	}
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		
		player.render(g);
		enemy.render(g);
		ball.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(layer,0,0, width*scale, height*scale,null);
		
		bs.show();
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.setRight(true);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.setLeft(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.setRight(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.setLeft(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	
}
