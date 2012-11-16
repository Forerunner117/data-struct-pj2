/* AI.java */

package machine;

import player.*;

/**
 *	A package protected class that contains static methods that can evaluate a 
 *  given board and return the best possible moves.
 */

class AI{

	public Move smartMove(Board bd, int myColor, int oppColor, int searchDepth){
	    int maxScore = -1000;
	    int currScore;
	    Move maxMove = null;
	    Move currMove = null;

	    MoveIterator it = new MoveIterator(bd, myColor);

	    while((currMove = it.getNext()) != null){
	      currScore = scoreMove(bd, currMove, myColor, oppColor, 1, searchDepth, maxScore);
	      if (currScore > maxScore){
	        maxScore = currScore;
	        maxMove = currMove;
	      }
    	}
    
    	return maxMove;
  	}

	private int scoreMove(Board bd, Move m, int myColor, int oppColor, int currDepth, int maxDepth, int cutoff){
		int retVal;
	    bd.addChip(m, myColor);

	    if (currDepth >= maxDepth) {
	      	if(currDepth % 2 == 0) //this means we are at a depth where we must eval for an opponent Move
	      		retVal = evaluate(bd, m, oppColor);   // eval is higher if pos is good for this color
	      	else
	      		retVal = evaluate(bd, m, myColor);
	      	bd.removeChip(m.x1, m.y1);
	      	return retVal;
	    }

	    if (bd.hasNetwork(myColor)  &&  !bd.hasNetwork(oppColor)) {
	      bd.removeChip(m.x1, m.y1);
	      return 1000;
	    }

	    retVal = -tryAll(bd, m, myColor, oppColor, currDepth, maxDepth, cutoff);
	    bd.removeChip(m.x1, m.y1);
	    return retVal;
  	}

	private int tryAll(Board bd, Move m, int myColor, int oppColor, int currDepth, int maxDepth, int cutoff){
	    int currVal, maxVal = -1000;
	    MoveIterator it = new MoveIterator(bd, oppColor);
	    Move currMove;

	    while((currMove = it.getNext()) != null) {
	      currVal = scoreMove(bd, currMove, myColor, oppColor, currDepth+1, maxDepth, maxVal);
	      if (currVal > maxVal) {
			maxVal = currVal;
			if (maxVal >= -cutoff)
		  		break;
	      	}
	    }
	    return maxVal;
  	}
	
	public int evaluate(Board bd, Move mv, int color){
		int score = 0;
		Board board = bd;
	    Move move = mv;	
		int pieces = bd.getPieces(color);
		Board temp = bd;
		
		//some useful values to have. 
		int enemyColor = getEnemyColor(color);
		int prevConnections = board.howManyConnections(color);
		int preMoveEnemyConnections = board.howManyConnections(enemyColor);

		board.addChip(mv,color);
		
		// If proposed move gives us a network.					
		if(board.hasNetwork(color)){
			score = 1000;
			return score;
		}

		//Difference in enemy connections
		int diffConnections = bd.howManyConnections(color) - prevConnections;
		if (diffConnections <= 0)
			score -= (int) Math.pow(5, -diffConnections);;
		if (diffConnections > 0)
			score += (int) Math.pow(5, diffConnections);//creating more connections is good. 
		
		// Difference in enemy connections
		int diffEnemyConnections = bd.howManyConnections(enemyColor) - preMoveEnemyConnections;
		if (diffEnemyConnections <= 0)
			score += (int) Math.pow(5, -diffEnemyConnections);
		if (diffEnemyConnections > 0 )
			score -= (int) Math.pow(5, diffEnemyConnections);

		//If we are into the game and still don't have a chip in the endgoal. 
		if(pieces >=4 && bd.endGoalEmpty(color))
		{				
			if(!board.endGoalEmpty(color)) 
				score += 100; //Ian, this is an unreachable assignment						
		}

		// If proposed move neutralizes a critical threat
		if(criticalThreat(bd, color))
		{
			temp.addChip(mv, color);
			if(!criticalThreat(temp, color))
				{	
					score += 500;
					if(score >= 1000)
						score = 999;
				}
				
				temp = bd;
		}
		// If proposed move causes a critical threat.
		if(criticalThreat(board, color))
			{
				score = -1000;				
			}						

		return 117; //THIS IS A TEST VALUE... also the coolest number ever			
	}

	private int getEnemyColor(int myColor){
		int enemyColor; 

		if(myColor==Chip.BLACK)			
			enemyColor = Chip.WHITE;
		else
			enemyColor=Chip.BLACK;

		return  enemyColor;
	}



	// I don't really get what this is.
	boolean criticalThreat(Board bd, int color)
	{	
		Board board = bd;
			
		int enemyColor;
		boolean enemyHasNetwork;
		
		if(color == Chip.BLACK)			
			enemyColor = Chip.WHITE;
		else
			enemyColor = Chip.BLACK;	
		
		
		int score = 0;
		int tempScore = 0;
		Move[] possibleMoves = LegalMoves.possibleMoves( board, enemyColor);
		
		
		for(int i = 0; i < possibleMoves.length; i++){			
			tempScore = evaluate(board, possibleMoves[i], enemyColor);
			if(tempScore > score || i == 0){
				score = tempScore;				
			}			
		}
		
		if(score == 1000)
			return true;
		else
			return false;								
	}
		
}
