/* AI.java */

package machine;

import player.*;

/**
 *	A package protected class that contains static methods that can evaluate a 
 *  given board and return the best possible move.
 */

public class AI{

	public static Move smartMove(Board bd, int myColor, int oppColor, int searchDepth){
	    int maxScore = -1000;
	    int currScore;
	    Move maxMove = null;
	    Move currMove = null;

	    MoveIterator it = new MoveIterator(bd, myColor);

	    while((currMove = it.getNext()) != null){
	      System.out.print("scoring move at: " + currMove.x1 + ", " + currMove.y1); 		         	
	      currScore = scoreMove(bd, currMove, myColor, oppColor, 1, searchDepth, maxScore);
	      System.out.println(" SCORE: " + currScore);
	      if (currScore >= maxScore){
	        maxScore = currScore;
	        maxMove = currMove;
	      }
    	}
    	
    	System.out.println("maxMove at: " + maxMove.x1 + ", " + maxMove.y1);
    	bd.addChip(maxMove, myColor);
        bd.setLastMove(maxMove, myColor);
    	return maxMove;
  	}

	private static int scoreMove(Board bd, Move m, int myColor, int oppColor, int currDepth, int maxDepth, int cutoff){
		int retVal;
	    bd.addChip(m, myColor);

	    if (currDepth >= maxDepth) {
	      	if(currDepth % 2 == 0){ //this means we are at a depth where we must eval for an opponent Move
	      		retVal = evaluate(bd, m, oppColor);   // eval is higher if pos is good for this color
	      		bd.undoMove(m, oppColor);
	      	}
	      	else{
	      		retVal = evaluate(bd, m, myColor);
	      		System.out.println("retVal: " + retVal);
	      		bd.undoMove(m, myColor);
	      	}
	      	
	      	return retVal;
	    }

	    if (bd.hasNetwork(myColor)  &&  !bd.hasNetwork(oppColor)) {
	      bd.undoMove(m, myColor);
	      return 1000;
	    }

	    retVal = -tryAll(bd, m, myColor, oppColor, currDepth, maxDepth, cutoff);
	    bd.undoMove(m, myColor);
	    return retVal;
  	}

	private static int tryAll(Board bd, Move m, int myColor, int oppColor, int currDepth, int maxDepth, int cutoff){
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
	
	public static int evaluate(Board bd, Move mv, int color){
		int score = 0;
		Board board = bd.copyBoard();		
		Move move = mv;	
		int pieces = bd.getPieces(color);
		Board oldBoard = bd;
		

		//some useful values to have
		int exploreLength;
		int enemyColor = getEnemyColor(color);
		int prevConnections = oldBoard.howManyConnections(color);
		int preMoveEnemyConnections = oldBoard.howManyConnections(enemyColor);

		board.addChip(mv,color);

		if(board.hasNetwork(color)){
			System.out.println("This makes a Network!");
		}
		// If proposed move gives us a network...without giving the enemy a network.					
		if(board.hasNetwork(color) && !board.hasNetwork(enemyColor)){//and enemy does NOT have network.
			score = 1000;
			return score;
		}
		// So we don't crowd the goals.
		if(board.startGoalCount(color) > 2)
			score -= 550;
		
		// So we don't crowd the goals
		if(board.endGoalCount(color) > 2)
			score -= 300;

		// To spread out the chips a little more
		if(board.getSurroundingEmpties(mv.x1, mv.y1) > 0)
			score += board.getSurroundingEmpties(mv.x1, mv.y1) * 3;
		// If enemy has a network
		if(board.hasNetwork(enemyColor))
			score = -1000;

		exploreLength = bd.getMaxExploreLength(); // must be after a network test.
		// System.out.println("exploreLength is " + exploreLength);
		

		// Take the center in the beginning.
		if (pieces <3){ //love pieces.
			if(mv.x1 > 2 && mv.x1 < 5 && mv.y1 > 2 && mv.y1 < 5)
				score += 250;
		}

		//If we are into the game and still don't have a chip in the endgoal. 
		if(pieces >= 4 && oldBoard.endGoalEmpty(color))
		{				
			if(!board.endGoalEmpty(color) || !board.startGoalEmpty(color)) 
				score += 325; //Fixed it,						
		}


		//Difference in enemy connections
		int diffConnections = board.howManyConnections(color) - prevConnections;
		if (diffConnections < 0)
			score -= diffConnections*115;
		if (diffConnections > 0)
			score += diffConnections*115; //creating more connections is good. 
		if (diffConnections == 0)
			score -= 50;
		
		// Difference in enemy connections. I made offense more valuable then defense. This can change if needbe.
		int diffEnemyConnections = board.howManyConnections(enemyColor) - preMoveEnemyConnections;
		if (diffEnemyConnections < 0)
			score += diffEnemyConnections*75;
		if (diffEnemyConnections > 0 )
			score -= diffEnemyConnections*75;
		if (diffEnemyConnections == 0)
			score += 50;

		if(cluster(oldBoard,color) >= 0.5 && mv.x1 < 4){
			//System.out.println("clusterFuck on right side----------------------------------");
			score += 500;
		}

		if(cluster(oldBoard,color) <= 0.5 && mv.x1 > 4){
			//System.out.println("clusterFuck on left side");
			score += 100;
		}

		// If proposed move neutralizes a critical threat
		if(criticalThreat(oldBoard, color)){
			//System.out.println("CRITICAL THREAT");
			
			if(!criticalThreat(board, color)){	
				//System.out.println("BLOCKED");
				score += 1500;
				if(score >= 1000)
				score = 999;
			}
				
				
		}
		// If proposed move causes a critical threat.
		if(criticalThreat(board, color)){
			//System.out.println("PULL OUT, SITUATION CRITICAL");
			score = -1000;				
		}
				
		System.out.println("\nEvaluation score:" +score);
		return score; 		
	}
		
	private static int getEnemyColor(int myColor){
		int enemyColor; 

		if(myColor==Chip.BLACK)			
			enemyColor = Chip.WHITE;
		else
			enemyColor=Chip.BLACK;

		return  enemyColor;
	}



	// Returns true if the enemy can place a wining move, but doesnt have a network YET
	static boolean criticalThreat(Board bd, int color){	
		Board Newboard = bd.copyBoard();		
		int enemyColor = getEnemyColor(color);
		boolean enemyHasNetwork;
		boolean threat = false;
		
		Move[] possibleMoves = LegalMoves.possibleMoves( Newboard, enemyColor);
		
		
		for(int i = 0; i < possibleMoves.length; i++){			
			if(possibleMoves[i]==null)
				return threat;
		Newboard.addChip(possibleMoves[i], enemyColor);
		if(Newboard.hasNetwork(enemyColor))
			return true;
		
		Newboard = bd.copyBoard();
		
		}
		
		return threat;								
	}
	
	static float cluster(Board bd, int Mycolor){
		int colorCounter = 0;
		int totalColor = 0;
		float ratio = 0;
		Chip temp;
		Total:	for(int i = 0; i < 8; i++)
		{
			
			for(int j = 0; j < 8; j++)
			{
				if(i == 0 && j == 0)
					continue Total;
				if(i == 0 && j == 7)
					continue Total;
				if(i == 7 && j == 0)
					continue Total;
				if(i == 7 && j == 7)
					continue Total;
					
				temp = bd.returnChip(i,j);
				if(temp.returnColor() == Mycolor)
					totalColor ++;	
				
			}
	
		
		}
		Counter:	for(int i = 4; i < 8; i++)
		{
			
			for(int j = 0; j < 8; j++)
			{
				if(i == 0 && j == 0)
					continue Counter;
				if(i == 0 && j == 7)
					continue Counter;
				if(i == 7 && j == 0)
					continue Counter;
				if(i == 7 && j == 7)
					continue Counter;
				temp = bd.returnChip(i,j);
				if(temp.returnColor() == Mycolor)
					colorCounter ++;	
				
			}
	
		
		}
	
		ratio = (float)colorCounter/(float)totalColor;
		return ratio;
		
		
	}
								
	
		
}
