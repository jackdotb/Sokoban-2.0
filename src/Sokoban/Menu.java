package Sokoban;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	
	public Menu(Game game,Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if(game.gameState==STATE.End || game.gameState==STATE.Menu && !game.paused) {
			if(mouseOver(mouseX, mouseY, 300, 150, 200, 64)) {
				game.gameState = STATE.Game;
				if(game.isWon(handler)) game.restart();
			}
			if(mouseOver(mouseX, mouseY, 300, 450, 200, 64)) {
				System.exit(1);
				//game.gameState = STATE.Game;
			}
			if(mouseOver(mouseX, mouseY,300, 250, 200, 64)) {
				game.gameState = STATE.Options;
			}
			if(mouseOver(mouseX, mouseY,300, 350, 200, 64)) {
				game.gameState = STATE.Options;
			}
		}
		//game.gameState==STATE.End 

	}
	
	
	private boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
		if(mouseX > x && mouseX < x+width) {
			if(mouseY > y && mouseY < y+height){
				return true;
			}else return false;
		}else return false;
	}

	
	public void tick() {
		
	}
	public void render(Graphics g) {
		//System.out.print("menu");
		Font font = new Font("arial", 1, 40);
		Font font2 = new Font("arial", 1, 30);
		
		
		if(game.gameState==STATE.End) {
			g.setFont(font);
			g.setColor(Color.orange);
			g.drawString("You Won   Total Steps:"+ game.steps, 200, 70);
		}
		
		g.setColor(Color.white);
		g.setFont(font2);
		
		g.drawRect(300, 150, 200, 64);
		g.drawString("Play", 360, 190);
		
		g.drawRect(300, 250, 200, 64);
		g.drawString("Options", 360, 290);
		
		g.drawRect(300, 350, 200, 64);
		g.drawString("Help", 360, 390);
		
		g.drawRect(300, 450, 200, 64);
		g.drawString("Exit", 360, 490);
		
		
	}
}
