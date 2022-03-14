package Sokoban;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Box extends GameObject{

	
	private BufferedImage boxImg =null;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3989164645666862105L;

	public Box(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(x!=nextDestX) {
			x+=velX;
			
		}
		if(y!=nextDestY)
			y+=velY;
		
		if(x==nextDestX && y==nextDestY) {
			this.setVelX(0);
			this.setVelY(0);
		}
		
	}

	@Override
	public void render(Graphics g) {
		String path = "";
		if(this.onStorage)
			path = "Assets/brownBox.png";
		else
			path = "Assets/box.png";
		
		try {
			boxImg = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(boxImg, x, y, null);
	}
	
	@Override
	public boolean checkForCollision(Handler handler, Direction dir) {

		this.setNextDest(dir);
		for(int i=0; i< handler.object.size(); i++) {
			if(handler.object.get(i).getX()==nextDestX && handler.object.get(i).getY()==nextDestY) {
				GameObject tempObject = handler.object.get(i);
				
				switch(tempObject.getId()) {
					case Wall: moveable = false; break;
					case Box:
						moveable = false; break;
					case Storage:
						moveable = true;
						handler.object.remove(tempObject);
						
						onStorage=true;
						break;
					case EmptyArea:
						handler.object.remove(tempObject);
						moveable = true;
						onStorage=false;
						break;
					default:
						
						break;

				}
			}
		}

		moveTo(dir, handler);
		
		return moveable;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}

}
