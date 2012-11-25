/* LegalMoves.java */

package machine; 

import player.*;

/**
 *	A package protected class that contains static methods that can return
 *	all possible moves for a certain player, and also whether a move is valid or not. 
 */

public class LegalMoves{	

	/**
	 * isLegal() is a package protected static method that takes a move and a color as 
     * parameters and returns true for a legal move and false for an illegal
	 * move.
	 * @param bd is the board isLegal looks at.
	 * @param m a Move object representing the proposed move.
	 * @param col is the color of team we're considering.
	 * @return true if a move is legal, false in not legal.
	 **/
	public static boolean isLegal(Board bd, Move m, int col){
		if(m.moveKind == m.QUIT)
			return true;

		//checks if the move is on the board
		if(!isOnBoard(m.x1, m.y1)){
			//System.out.println("Not Legal: not on board!");
			return false;
		}

		//checks for the corners and excludes them
		if((m.x1 == 0 && m.y1 == 0) || (m.x1 == 0 && m.y1 == 7) || (m.x1 == 7 && 
		   m.y1 == 0) || (m.x1 == 7 && m.y1 == 7)){
		   	//System.out.println("Not Legal: corner move!");
			return false;
		}

		//checks if a color is in the opponent's goal
		if((m.x1 == 0 || m.x1 == 7) && col == Chip.BLACK){
			//System.out.println("Not Legal: opponent's goal!");
			return false;
		}
		if((m.y1 == 0 || m.y1 == 7) && col == Chip.WHITE){
			//System.out.println("Not Legal: opponent's goal!");
			return false;
		}

		//checks if the slot is filled
		if(bd.chipColor(m.x1, m.y1) != Chip.EMPTY){
			 //System.out.println("Not Legal: slot is filled at " + m.x1 + ", " + m.y1 +
			 //	 " with color: " + bd.chipColor(m.x1, m.y1));
			return false;
		}

		//checks if there are 10 BLACK or WHITE pieces and determines if the
		//moveKind is accurate
		//System.out.println("Pieces in play by " + col + ": " + bd.getPieces(col));
		if(bd.getPieces(col) > 9 && m.moveKind == m.ADD){
			//System.out.println("Not Legal: Must make a STEP move!");
			return false;
		}
		if(bd.getPieces(col) < 10 && m.moveKind == m.STEP){
			//System.out.println("Not Legal: Must make an ADD move!");
			//System.out.println("You only have " + bd.getPieces(col) + " pieces down.");
			return false;
		}

		//checks if the move would make a cluster
		if(makesCluster(bd, m, col)){
			//System.out.println("Not Legal: this makes a cluster!");		
			return false;
		}
		
		if(m.moveKind == Move.STEP){
			if(m.x1 == m.x2 && m.y1 == m.y2){
				//System.out.println("Not Lega: STEP move must move piece!");
				return false;
			}

		}
					

		return true;
	}

	/**
	 * isOnBoard() is a static boolean method that returns true if the move is on the board and
	 * false if not.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return true if the move is on the board false if not on board.
	 **/
	static boolean isOnBoard(int x, int y){
		return(x >= 0 && x <= 7 && y >= 0 && y <= 7);
	}

	/**
	 * makesCluster() is a package protected static method that checks if a move makes a 
	 * cluster on the board. A cluster is a group of three chips and one of them is
	 * adjacent to the other two
	 * @param bd the board makesCluster looks at.
	 * @param m is the move to be considered.
	 * @param col is the color of the team we're looking at.
	 * @return true if m makes a cluster, false if it does not make a cluster.
	 **/
	static boolean makesCluster(Board bd, Move m, int col){
		int x = m.x1;
		int y = m.y1;
		Chip src = null;
		Chip neighbor = null;
		int numNeighbors = 0;

		if(m.moveKind == m.STEP)
			src = bd.returnChip(m.x2, m.y2);

		//nested for loop that checks all 8 adjacent neighboring cells
		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
				//skips the center position
				if(i == 0 && j == 0)
					continue;
				//skips any consideration of a spot that is not on the board
				if(i == -1 && x == 0 || i == 1 && x == 7 || j == -1 && y == 0 ||
				   j == 1 && y == 7){
					continue;
				}
				if(bd.returnChip(x+i, y+j) != null && bd.returnChip(x+i, y+j).color
				   == col){
					if(src != null && bd.returnChip(x+i, y+j) == src)
						continue;
					//we found a neighbor
					neighbor = bd.returnChip(x+i, y+j);
					++numNeighbors;
				}
			}			
		}

		//if we already found 2 or more neighbors, the move is invalid
		if(numNeighbors >= 2)
			return true;
		//if there were no neighbors, it is a valid move
		if(numNeighbors == 0)
			return false;
		//if we found 1 neighbor, we need to see if it has a neighbor
		if(numNeighbors == 1){		
			x = neighbor.x;
			// System.out.println(neighbor.x);			
			y = neighbor.y;	
			// System.out.println(neighbor.y);
			for(int i = -1; i <= 1; i++){
				for(int j = -1; j <= 1; j++){
					//skips the center position
					if(i == 0 && j == 0)
						continue;
					//skips any consideration of a spot that is not on the board
					if(i == -1 && x == 0 || i == 1 && x == 7 || j == -1 && y == 0 ||
				   	   j == 1 && y == 7){
						continue;
					}
					if(bd.returnChip(x+i, y+j) != null && bd.returnChip(x+i, y+j).color
				   	   == col){
						if(src != null && bd.returnChip(x+i, y+j) == src)
							continue;
						//we found a neighbor
						neighbor = bd.returnChip(x+i, y+j);
						++numNeighbors;
						return true;
					}
				}			
			}
		}
		return false;
	}
	/**
 	 * possibleMoves() is a package protected static method that takes the color of the current player
 	 * and returns an array of valid Move objects. Calls the isLegal method.
 	 * @param bd the board the method looks at.
 	 * @param col the color of the team to consider.
 	 * @return an array of Move objects legal to put down.
 	 **/
	public static Move[] possibleMoves(Board bd, int col){
		Move[] possibleMoves = new Move[500]; 
		Chip[] currentChips = bd.getCurrChips(col);
		
		/*
		int blarg = 0;
		while(currentChips[blarg] != null){
			System.out.println(currentChips[blarg].x + ", " + currentChips[blarg].y);
			++blarg;
		}
		*/

		int counter = 0;
		int x = 0;
		Move currMove;

		//ADD moves
		if(bd.getPieces(col) < 10){
			// System.out.println("Generating possible ADD moves:");
			for(int i = 0; i < 8; i++){
				for(int j = 0; j < 8; j++){
					currMove = new Move(i, j);
					if(isLegal(bd, currMove, col)){
						possibleMoves[counter] = currMove;
						++counter;
					}
				}
			}
		}

		//STEP moves
		else{
			// System.out.println("Generating possible STEP moves:");
			while(currentChips[x] != null){
				for(int i = 0; i < 8; i++){
					for(int j = 0; j < 8; j++){
						currMove = new Move(i, j, currentChips[x].x, currentChips[x].y);
						if(isLegal(bd, currMove, col)){
							possibleMoves[counter] = currMove;
							++counter;
						}
					}
				}
				++x;
			}
			//System.out.println("PRINTING STEP MOVE possibleMoves.*****************************");
			
			/*
			int s = 0;
			while(possibleMoves[s] != null){
				System.out.println(possibleMoves[s].x1 + ", " + possibleMoves[s].y1
				 + " FROM: " + possibleMoves[s].x2 + ", " + possibleMoves[s].y2);
				++s;
			}
			*/
		}
		return possibleMoves;
	}
	
}