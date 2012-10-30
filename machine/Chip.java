/* Chip.java */

package machine; 

import player.*;

class Chip{
	static final int BLACK = 0;
	static final int WHITE = 1;
	
	int color;
	boolean visited = false;
	int x;
	int y;
	                                                      

	Chip(int color){		
		this.color = color;
	}
	
	Chip(int x, int y, int color){		
		this.color = color;
		this.x = x;
		this.y = y;
	}
	void flag(){
		visited = true;
	}

	void unflag(){
		if(visited == false)
			System.out.println("This isn't visited");
		visited = false;	
	}
	
	boolean isFlagged(){
		if(visited == true)
			return true;
		else return false;			
	}
	
	int returnColor(){
		return color;	
	}
	
	void changeColor( int color){
		this.color = color;			
	}
}
