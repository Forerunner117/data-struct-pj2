/* Chip.java */

package machine; 

import player.*;

class Chip{
	static final int WHITE = 10;
	static final int BLACK = 11;
	static final int EMPTY = 12;
	static final int GREY = 13;
	static private int color;
	boolean visited = false;
	int x;
	int y;
	
	Chip(){                                                           
		color = EMPTY;	
	}

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
