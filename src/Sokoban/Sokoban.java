package Sokoban;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sokoban extends GameObject{
	
	//static int steps;
	private BufferedImage playerImg = null;

	private static final long serialVersionUID = 5068912899134223146L;
	
	public Sokoban(int x, int y, ID id) {
		super(x, y, id);
		this.keyValid = true;

	}
	@Override
	public void tick() {
		if(x!=nextDestX) {
			x+=velX;
			
		}
		if(y!=nextDestY)
			y+=velY;
			
		
		if(x==nextDestX && y==nextDestY) {
			this.setVelX(0);
			this.setVelY(0);
			this.keyValid=true;

		}

	}
	
		
	@Override
	public void render(Graphics g) {
		
		try {
			playerImg = ImageIO.read(new File("Assets/player.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(playerImg, x, y, null);
	}
	
	@Override
	public boolean checkForCollision(Handler handler, Direction dir) {
		moveable = true;
		this.setNextDest(dir);
		
		loop: for(int i=0; i< handler.object.size(); i++) {
			if(handler.object.get(i).getX()==nextDestX && handler.object.get(i).getY()==nextDestY) {
				GameObject tempObject = handler.object.get(i);
				
				switch(tempObject.getId()) {
					case Wall: moveable = false; break;
					case Box:
						boolean temp = tempObject.onStorage;
						if(tempObject.checkForCollision(handler, dir)) {
							moveable = true;

							managePreviousPlayerPlace(handler);
							onStorage = temp;
						}
						else {
							moveable = false;
						}
						break;

					case Storage:
						moveable = true;
						handler.object.remove(tempObject);

						managePreviousPlayerPlace(handler);
						onStorage = true;
						 break;
					case EmptyArea:
						moveable= true;
						managePreviousPlayerPlace(handler);

						onStorage = false;
						break;
					default: break;
				

				}
				break loop;
				
			}
			
		}
		moveTo(dir, handler);
		if(moveable) Game.steps++;
		
		System.out.println(Game.steps);
		return moveable;
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}
	
	public void managePreviousPlayerPlace(Handler handler) {
		if(this.onStorage)
			handler.addObject(new Storage(this.getX(),this.getY(), ID.Storage));
		else
			handler.addObject(new EmptyArea(this.x,this.y, ID.EmptyArea));
	}

}
