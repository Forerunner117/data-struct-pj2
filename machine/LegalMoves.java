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
		if(m.moveKind == m.QUIT)
			return true;

		//checks if the move is on the board
		if(!isOnBoard(m.x1, m.y1))
			return false;

		//checks for the corners
		if((m.x1 == 0 && m.y1 == 0) || (m.x1 == 0 && m.y1 == 7) || (m.x1 == 7 && 
		   m.y1 == 0) || (m.x1 == 7 && m.y1 == 7)){
			return false;
		}

		//checks if a color is in the opponent's goal
		if((m.x1 == 0 || m.x1 == 7) && col == Chip.BLACK)
			return false;
		if((m.y1 == 0 || m.y1 == 7) && col == Chip.WHITE)
			return false;

		//checks if the slot is filled
		if(Board.board[m.x1][m.y1] != Board.EMPTY)
			return false;

		//checks if there are 10 BLACK or WHITE pieces and determines if the
		//moveKind is accurate
		if(Board.numPieces[col] > 9 && m.moveKind == m.ADD)
			return false;
		if(Board.numPieces[col] < 10 && m.moveKind == m.STEP)
			return false;
		
		if(m.moveKind == m.STEP)
					

		return false;
	}

	// A static boolean class that returns true if the move is on the board and
	// false if not.
	static boolean isOnBoard(int x, int y){
		return(x >= 0 && x <= 7 && y >= 0 && y <= 7);
	}
	
 	// A package protected static method that takes the color of the current player
 	// and returns a Board object consisting of null pointers (invalid moves) and
 	// Chip objects (valid moves). Calls the isLegal method.	
	static Board possibleMoves(int col){
		Board possibleMoves = new Board();
		
		return possibleMoves;
	}
	
}