/* LegalMoves.java */

package machine; 

import player.*;

/**
 *	A package protected class that contains static methods that can return
 *	all possible moves for a certain player, and also whether a move is valid or not. 
 */

class LegalMoves{	

	// A package protected static method that takes a move and a color as 
	// parameters and returns true for a legal move and false for an illegal
	// move.
	static boolean isLegal(Move m, int col){
		return false;
	}

	
 	// A package protected static method that takes the color of the current player
 	// and returns a Board object consisting of null pointers (invalid moves) and
 	// Chip objects (valid moves). Calls the isLegal method.	
	static Board possibleMoves(int col){
		Board possibleMoves = new Board();
		
		return possibleMoves;
	}
	
}