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
	      	if(currDepth % 2 == 0)
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
		int i = 0;
		Board board = bd;
	        Move move = mv;	
		int pieces= bd.getPieces(color);
		Board temp = bd;
		board.addChip(mv,color);
		if(pieces >=4 && bd.endGoalEmpty(color))
		{
			
			
			
			if(!board.endGoalEmpty(color)) 
				i = i+100;
			
			
		}
		if(CriticalThreat(bd, color))
		{
			temp.addChip(mv,color);
			if(!CriticalThreat(temp, color))
				{	
					i = i+500;
					if(i>=1000)
						i = 999;
				}
				
				temp = bd;
		}
		
		if(CriticalThreat(board, color))
			{
				
				i = -1000;
				
			}
		
		
		
		
			
				
		if(board.hasNetwork(color)){
			i= 1000;
			return i;
		}

		return i;			
	}
	boolean CriticalThreat(Board bd, int color)
	{
		
		Board board = bd;
			
		int enemyColor;
		boolean enemyHasNetwork;
		
		if(color==Chip.BLACK)
		{
			
			enemyColor = Chip.WHITE;
			
			
		}
		else
			enemyColor=Chip.BLACK;	
		
		
		int score = 0;
		int tempScore = 0;
		Move[] possibleMoves = LegalMoves.possibleMoves( board, enemyColor);
		
		
		for(int i = 0; i < possibleMoves.length; i++)
		{
			
			tempScore = evaluate(board, possibleMoves[i], enemyColor);
			if(tempScore > score||i==0)
			{
				score=tempScore;
				
			}
			
		}
		
		if(score == 1000)
			return true;
		else
			return false;
		
		
		
		
	}
	
	
}
