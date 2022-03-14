package Sokoban;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall extends GameObject {
	private BufferedImage wallImg = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = -415110791585039269L;

	public Wall(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

		try {
			wallImg = ImageIO.read(new File("Assets/wall.png"));
		} catch (IOException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(wallImg, x, y, null);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}

}
