/* AI.java */

package machine;

import player.*;

/**
 *	A package protected class that contains static methods that can evaluate a 
 *  given board and return the best possible moves.
 */

class AI{

	public Move smartMove(Board bd, int myColor, int oppColor int searchDepth){
	    int maxScore = -1000;
	    int currScore;
	    Move maxMove = null;
	    Move currMove = null;

	    it = new MoveIterator(bd, color);

	    while((currMove = it.getNext()) != null){
	      currScore = scoreMove(bd, currMove, color, 1, searchDepth, maxScore);
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
	      retVal = evaluate(myColor);   // eval is higher if pos is good for this color
	      bd.removeChip(m, myColor);
	      return retval;
	    }

	    if (bd.hasNetwork(myColor)  &&  !bd.hasNetwork(oppColor)) {
	      bd.removeChip(m, player);
	      return 1000;
	    }

	    retVal = -tryAll(bd, m, myColor, oppColor, currDepth, maxDepth, cutoff);
	    bd.removeChip(m, myColor);
	    return retVal;
  	}

	private int tryAll(Board bd, Move m, int myColor, int oppColor, int currDepth, int maxDepth, int cutoff){
	    int currVal, maxVal = -1000;
	    it = new moveIterator(bd, oppColor);
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
			
			
		bd.addChip(mv, color);	
				
		if(bd.hasNetwork(color)){
			i= 20;
			return i;
		}

		return i;			
	}			
}