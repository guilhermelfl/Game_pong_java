package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	
	private int barWidth;
	private int barHeight;
	private double enemyXPosition;
	private double enemyYPosition;
	public int getBarWidth() {
		return barWidth;
	}

	public int getBarHeight() {
		return barHeight;
	}

	public double getEnemyXPosition() {
		return enemyXPosition;
	}

	public double getEnemyYPosition() {
		return enemyYPosition;
	}

	private Ball ball;
	
	public Enemy (int w, int h) {
		barWidth = w;
		barHeight = h;
		enemyXPosition = (Game.width/2)-(barWidth/2);
		enemyYPosition = 0;
	}
	
	public void tick() {
		enemyXPosition += ((ball.getBallXPosition()+(ball.getBallWidth()/2)) 
						- (enemyXPosition+(barWidth/2))) *0.03 ;
		
		if(enemyXPosition <= 0)
			enemyXPosition = 0;
		if(enemyXPosition + barWidth >= Game.width)
			enemyXPosition = Game.width-barWidth;
	}
	
	public void render(Graphics g) {
		if(g==null) {
			return;
		}
		g.setColor(Color.red);
		g.fillRect((int)enemyXPosition, (int)enemyYPosition,
				   barWidth, barHeight);
	}
	
	public void getBall(Ball b) {
		ball = b;
	}
	
	public void resetEnemy() {
		enemyXPosition = (Game.width/2)-(barWidth/2);
	}

}
