package Sokoban;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Storage extends GameObject {

	/**
	 * 
	 */
	
	private BufferedImage storageImg = null;
	
	private static final long serialVersionUID = 1L;

	public Storage(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {

			
		try {
			storageImg = ImageIO.read(new File("Assets/storage.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(storageImg, x, y, null);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}

}
