/* MoveIterator.java  */

package player;

import machine.*;

/**
 *  A public class for holding all the fields in a move.  This class is a
 *  container for data, not an ADT; hence, all fields are public.
 *
 *  The moveKind field stores the type of move.  The x-coordinates index the
 *  horizontal direction (left to right) and the y-coordinates index the
 *  vertical direction (top to bottom).  x- and y-coordinates start at zero.
 */

public class MoveIterator extends Move{

	private Move[] possibleMoves = new Move[500];
	private int color;
	private int pointer = -1;

	// Constructs a move iterator
	public MoveIterator(Board bd, int col){
		possibleMoves = LegalMoves.possibleMoves(bd, col);
		color = col;
	}

	// Gets the next possible move.
	public Move getNext(){
		if(possibleMoves[pointer+1] != null){
			++pointer;
			return possibleMoves[pointer];			
		}
		else
			return null;		    
	}

}