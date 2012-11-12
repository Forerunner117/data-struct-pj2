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
	
	public Move SmartMove(Board bd, int col)
	{
	Board board = bd;
	int color = col;
	Move bestMove = null;
	int score = 0;
	int tempScore = 0;
	Move[] possibleMoves = LegalMoves.possibleMoves( board, color);

	for(int i = 0; i < possibleMoves.length; i++)
	{
		
		tempScore = evaluate(bd, possibleMoves[i], col);
		if(tempScore > score||i==0)
		{
			score=tempScore;
			bestMove = possibleMoves[i];
			
		}
		
		
		
		
		
		
	}
		
		
		
	return	bestMove;
	}
	
	
}