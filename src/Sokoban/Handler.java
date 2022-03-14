package Sokoban;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	//tick
	public void tick() {
		for(int i=0; i< object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		//sortList();
		for(int i=0; i< object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	@SuppressWarnings("unlikely-arg-type")
	public void sortList() {
		for(int i=0; i< object.size(); i++) {
			GameObject tempObject = object.get(i);
			if(!tempObject.equals(ID.EmptyArea)) {
				GameObject temp = tempObject;
				object.remove(tempObject);
				object.add(temp);
			}
		}
	}
	
	public boolean isWon() {
		int numSt = 0;
		for(int i=0; i<this.object.size();i++) {
			GameObject tempObject = this.object.get(i);
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
}