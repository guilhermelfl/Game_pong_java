package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	
	private int ballWidth;
	private int ballHeight;
	private double ballXPosition;
	
	private double ballYPosition;
	
	private Player player;
	private Enemy enemy;
	
	private double dx,dy;
	private double speed = 1.7;
	
	public Ball (int w, int h) {
		ballWidth = w;
		ballHeight = h;
		ballXPosition = Game.width/2;
		ballYPosition = Game.height/2;
		
		int angle = new Random().nextInt(120 - 45) + 45 + 1;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {
		if(ballXPosition + (dx * speed) + ballWidth >= Game.width)
			dx*=-1;
		if(ballXPosition + (dx * speed) <= 0)
			dx*=-1;
		
		
		if(ballYPosition >= Game.height) {
			//Enemy Point
			System.out.println("Enemy Score");
			resetBall();
//			new Game();
			return;
		}else if(ballYPosition <= 0) {
			//Player Point
			System.out.println("Player Score");
			resetBall();
//			new Game();
			return;
		}
		
		Rectangle ballBounds = new Rectangle((int)(ballXPosition+(dx*speed)),
							                (int)(ballYPosition+(dy*speed)),
							                ballWidth,ballHeight);

		Rectangle playerBounds = new Rectangle(player.getPlayerXPosition(),player.getPlayerYPosition(),
                							   player.getBarWidth(),player.getBarHeight());

		Rectangle enemyBounds = new Rectangle((int)enemy.getEnemyXPosition(),
											  (int)enemy.getEnemyYPosition(),
				   							  enemy.getBarWidth(),enemy.getBarHeight());

		if(ballBounds.intersects(playerBounds)) {
			int angle = new Random().nextInt(120 - 45) + 45 + 1;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy > 0)
				dy*=-1;
		} else if(ballBounds.intersects(enemyBounds)) {
			int angle = new Random().nextInt(120 - 45) + 45 + 1;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy < 0)
				dy*=-1;
		}
		
		
		ballXPosition+= dx*speed;
		ballYPosition+= dy*speed;
	}
	
	public void render(Graphics g) {
		if(g==null) {
			return;
		}
		g.setColor(Color.white);
		g.fillRect((int)ballXPosition, (int)ballYPosition,
				   ballWidth, ballHeight);
	}
	
	public void resetBall() {
		int angle = new Random().nextInt(120-45) + 45 + 1;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
		ballXPosition = Game.width/2;
		ballYPosition = Game.height/2;
		player.resetPlayer();
		enemy.resetEnemy();
	}
	
	public double getBallXPosition() {
		return ballXPosition;
	}
	
	public int getBallWidth() {
		return ballWidth;
	}
	
	public void getPlayer(Player p) {
		player = p;
	}
	
	public void getEnemy(Enemy e) {
		enemy = e;
	}

}
