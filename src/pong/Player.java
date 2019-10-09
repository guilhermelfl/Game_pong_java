package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	private int barWidth;
	private int barHeight;
	public int getBarWidth() {
		return barWidth;
	}

	public int getBarHeight() {
		return barHeight;
	}

	public int getPlayerXPosition() {
		return playerXPosition;
	}

	public int getPlayerYPosition() {
		return playerYPosition;
	}

	private int playerXPosition;
	private int playerYPosition;
	
	private boolean right = false;
	private boolean left = false;
	
	
	
	public Player (int w, int h) {
		barWidth = w;
		barHeight = h;
		playerXPosition = (Game.width/2)-(barWidth/2);
		playerYPosition = Game.height-h;
	}
	
	public void tick() {
		if(right) {
			if(playerXPosition + barWidth < Game.width)
				playerXPosition ++;
		}
		if(left) {
			if(playerXPosition>0)
				playerXPosition --;
		}
	}
	
	public void render(Graphics g) {
		if(g==null) {
			return;
		}
		g.setColor(Color.blue);
		g.fillRect(playerXPosition, playerYPosition,
				   barWidth, barHeight);
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public void resetPlayer() {
		playerXPosition = (Game.width/2)-(barWidth/2);
	}

}
