package Sokoban;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;


public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -1744897605945161043L;
	
	public static int WIDTH = 640;

	public static int HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	protected static boolean running = false;
	static boolean paused = false;
	Handler handler;
	static MapGenerator board;
	private Menu menu;
	private Options options;
	protected static int steps=0;

	public static STATE gameState = STATE.Menu;
	
	public Game(){
		this.requestFocusInWindow();
		

		String map1 = "--#####\n"
					+"###@-x#\n"
					+"#-x-b-#\n"
					+"#-xb-##\n"
					+"#--#-##\n"
					+"#-bb-x#\n"
					+"#-----#\n"
					+"#######";
		
		handler = new Handler();
		menu = new Menu(this, handler);
		//options = new Options(this);
		
		this.addMouseListener(menu);
		this.addKeyListener(new KeyHandler(handler, this));
		
	
		
		//if(gameState == STATE.Game) {
			board = new MapGenerator();
			System.out.println("map");
			try {
				board.save(map1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			
			try {
				handler.object = board.load();
				WIDTH = board.width + 400;
				HEIGHT = board.heigth + 200;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		new Window(WIDTH, HEIGHT, "Sokoban", this);
		
		
		//System.out.println(handler.object.get(1).x);
		
		this.requestFocusInWindow();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 75.0;
		double ns = 1000000000/ amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		while(running) {
			long now = System.nanoTime();
			delta += (now- lastTime) / ns;
			lastTime = now;

			
			
			while(delta >= 1) {
				tick();
				delta--;
				
			}
			if(running) {
				render();
			}
			
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS:" + frames);
				frames = 0;
			}
		}
		stop();
		
	}
	
	public void tick() {
		if(!paused) {
			if(gameState == STATE.Game) {
				
				handler.tick();
			}
			else if(gameState == STATE.Menu) {
				menu.tick();
			}
			else if(gameState == STATE.Options) {
				Window.frame.setVisible(false);
				paused = true;
				options = new Options(this,handler, 700, 600);
				options.setVisible(true);
			}
		}

		
		
		

	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(gameState == STATE.Game) {
			
			handler.render(g);
			//String step = siteps.t
			Integer i = steps;
			g.setColor(Color.white);
			g.setFont(new Font("arial", 1, 30));
			g.drawString("Number of Steps: "+i.toString(), 400, 50);
		}
		else if(gameState == STATE.Menu) {
			menu.render(g);
		}
		else if(gameState == STATE.End) {
			menu.render(g);
		}
		
		g.dispose();
		bs.show(); 
	}
	

	
	
	public void restart(){
		try {
			this.handler.object.clear();
			this.handler.object = board.load();
			WIDTH = board.width + 400;
			HEIGHT = board.heigth + 200;
			steps = 0;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean isWon(Handler handler) {
		int numSt = 0;
		for(int i=0; i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Storage) {
				numSt++;
			}
			if(tempObject.getId() == ID.Sokoban) {
				if(tempObject.onStorage) {
					numSt++;
				}
			}
			
			
		}
		if(numSt==0)
			return true;
		return false;
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		game.setVisible(true);
	}

}
