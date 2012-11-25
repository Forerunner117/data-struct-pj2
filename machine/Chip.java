/* Chip.java */

package machine; 

import player.*;

public class Chip{
	public static final int BLACK = 0;
	public static final int WHITE = 1;
	public static final int GREY = 2;
	public static final int EMPTY = 3;

	int color;
	boolean visited = false;
	boolean touched = false;
	int x;
	int y;
	                                                      
    /**
	 * Chip() constructs a chip object with specified color.
	 * @param color is the color of the chip.
	 **/	
	Chip(int color){		
		this.color = color;
		x = 1;
		y = 1;
	}
	
	/**
     * Chip() constructs a chip object with specified (x,y) position and color.
     * @param x is the x coordinate.
     * @param y is the y coordinate.
     * @param color is the color of the chip.
	 **/
	Chip(int x, int y, int color){		
		this.color = color;
		this.x = x;
		this.y = y;
	}

	/**
     * flag() sets the boolean 'visited' to true. Used in network checker.
	 **/
	void flag(){
		visited = true;
	}

	/**
     * unflag() sets the boolean 'visited' to false. Used in network checker.
	 **/
	void unflag(){
		visited = false;	
	}

	/**
     * touch() sets the boolean 'touched' to true. Used in connection counter.
	 **/
	void touch(){
		touched = true;
	}

	/**
     * untouch() sets the boolean 'touched' to false. Used in connection counter.
	 **/
	void untouch(){
		touched = false;
	}

	/**
     * isTouched() returns the status of 'touched'.
	 **/
	boolean isTouched(){
		if (touched == true) 
			return true;
		
		else return false;
	}

	/**
     * isFlagged() returns the status of 'visited'.
	 **/
	boolean isFlagged(){
		if(visited == true)
			return true;
		else return false;			
	}
	
	/**
     * returnColor() returns the color of the chip.
	 **/
	int returnColor(){
		return color;	
	}

	/**
     * getX() returns the x coordinate of the chip.
	 **/
	int getX(){
		return x;
	}

	/**
     * getY() returns the y coordinate of the chip.
	 **/
	int getY(){
		return y;
	}

	/**
     * changeColor() changes the chips color to color.
     * @param color the color replacing the original color.
	 **/
	void changeColor(int color){
		this.color = color;			
	}

	/**
     * toString() returns a string representation of a chip.
     * @return the string representation of the chip.
	 **/
	public String toString(){
		if(color== BLACK)
			return "BK";
		else if(color == WHITE)
			return "WH";
		else if(color == GREY)
			return "--";
		else
			return "__";	
	}
}
	