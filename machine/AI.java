/* AI.java */

package machine;

import player.*;

/**
 *	A package protected class that contains static methods that can evaluate a 
 *  given board and return the best possible moves.
 */

class AI{

	
	public int evaluate(Board bd, Move mv, int color)
	{
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