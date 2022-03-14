package Sokoban;

import java.io.*;
import java.util.LinkedList;


public class MapGenerator {
	//private Handler handler;
	//private String map;
	
	private int x=64, y=64;
	private int SPACE=64;
	protected int width=x, heigth=y;
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public MapGenerator() {
	}
	
	public void save(String map) throws IOException {
		
		FileOutputStream fileOut = new FileOutputStream("map.ser");  
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		
		for(int i=0; i< map.length(); i++) {
			char item = map.charAt(i);
			switch(item) {
				case '@':
					out.writeObject(new Sokoban(x, y, ID.Sokoban));
					x+=SPACE;
					break;
				case '#':
					out.writeObject(new Wall(x, y, ID.Wall));
					x+=SPACE;
					break;
				case 'x':
					out.writeObject(new Storage(x, y, ID.Storage));
					x+=SPACE; break;
				case 'b':
					out.writeObject(new Box(x, y, ID.Box));
					x+=SPACE; break;
				case '-':
					out.writeObject(new EmptyArea(x, y, ID.EmptyArea));
					x+=SPACE; break;
				case '\n':
					y+=SPACE;
					x=width;		
			}
			

		}

		out.close();
		fileOut.close();
		
		width = x- width;
		heigth = y - heigth;
		
	}
	
	public LinkedList<GameObject> load() throws IOException, ClassNotFoundException {

		FileInputStream fileIn = new FileInputStream("map.ser");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		while(true) {
			GameObject newObj=null;
			try {
				newObj = (GameObject) in.readObject();
			}
			catch(IOException e){
				
			}
			
			if(newObj==null)break;
			
			object.add(newObj);
		}
		
		in.close();
		fileIn.close();
		
		return object;

	}
}
