package Sokoban;

import java.awt.event.*;


public class KeyHandler extends KeyAdapter{
	private Handler handler;
	private Game game;


	public KeyHandler(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(game.gameState==STATE.Game) {
			loop: for(int i=0; i< handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Sokoban) {
					//tempObject.setVelX(0);
					//tempObject.setVelY(0);
					if(tempObject.keyValid) {
						switch(key) {
							case KeyEvent.VK_W:
								if(tempObject.checkForCollision(handler, Direction.UP)) {
									//tempObject.setY(tempObject.nextDestY);
									//tempObject.setVelY(-4);
									//return;
								} break;
								
							case KeyEvent.VK_S:
								if(tempObject.checkForCollision(handler, Direction.DOWN)) {
									//tempObject.setY(tempObject.nextDestY);
									//tempObject.setVelY(4); 
									//return;
								} break;
								
							case KeyEvent.VK_D:
								if(tempObject.checkForCollision(handler, Direction.RIGHT)) {
									//tempObject.setX(tempObject.nextDestX);
									//tempObject.setVelX(4);
									//return;
								} break;
								
							case KeyEvent.VK_A:
								if(tempObject.checkForCollision(handler, Direction.LEFT)) {
									//tempObject.setX(tempObject.nextDestX);
									//tempObject.setVelX(-4);
									//return;
								} break;
							default: break;
						}
						tempObject.keyValid = false; 
						if(game.isWon(handler)) { 
							game.gameState = STATE.End; 
							//game.restart(); 
							return;
						}
						break loop;
					}
				}
			}
			if(key==KeyEvent.VK_R) {
				game.restart();
				game.steps=0;
			}
		}
		if(key==KeyEvent.VK_P) {
			if(game.gameState == STATE.Menu) game.gameState=STATE.Game; 
			else if(game.gameState == STATE.Game) game.gameState=STATE.Menu; 
		}
	}
	

	

	
	
}
