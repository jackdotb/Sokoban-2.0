package junittest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

//package src.Sokoban;
import org.junit.Before;
import org.junit.Test;

import Sokoban.*;

public class SokobanTest {
	//Game game;
	//Handler handler;
	
	/*@Before
	public void setUp () throws Exception{
		//this.game = new Game();
		handler = new Handler();
		
		
	}*/
	//game = new Game();
	
	@Test
	void testGameStart() {
		//game = new Game();
		Handler handler = new Handler();
		handler.addObject(new Box(100,100, ID.Box));
		assertTrue(handler.isWon());
		//assertEquals(STATE.Menu, game.gameState);
	}
	
}
