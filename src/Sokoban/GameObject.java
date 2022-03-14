package Sokoban;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

public abstract class GameObject implements Serializable{
	/**
	 * 
	 */
	protected boolean moveable;
	protected boolean onStorage = false;
	private static final long serialVersionUID = 131658779995581492L;
	protected int x, y, nextDestX, nextDestY, velX, velY;
	protected ID id;
	public boolean keyValid;
	//protected static int steps;
	
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setId(ID id) {
		this.id = id;
	}
	
	public ID getId() {
		return id; 
	}
	
	public boolean checkForCollision(Handler handler, Direction dir) {
		return false;}
	
	public void setNextDest(Direction dir) {
		switch(dir) {
			case UP: nextDestY = y-64; nextDestX = x; break;
			case DOWN: nextDestY = y+64; nextDestX = x; break;
			case LEFT: nextDestX = x-64; nextDestY = y; break;
			case RIGHT: nextDestX = x+64; nextDestY = y; break;
			case NONE: nextDestX = x; nextDestY = y; break;
			default: 
				break;
		}
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public int getVelX() {
		return velX;
	}
	
	public int getVelY() {
		return velY;
	}
	
	public abstract Rectangle getBounds();
	
	public void moveTo(Direction dir, Handler handler) {
		if(moveable) {
			switch(dir) {
			case UP: this.setVelY(-4); break;
			case DOWN: this.setVelY(4); break;
			case LEFT: this.setVelX(-4); break;
			case RIGHT: this.setVelX(+4); break;
			default: break;
			}

		}
		else {
			this.setNextDest(Direction.NONE);
		}
	}
		
	
}
