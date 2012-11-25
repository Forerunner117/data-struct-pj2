/* EvaluateTestDrive.java */
package machine; 

import player.*;

import java.util.*;
public class EvaluateTestDrive{

static Board bd = new Board();

    

public static void main(String[] args) {
	

		Move black[] = new Move[10];
		Move white[] = new Move[10];
		Move[] wPoss;
		Move[] bPoss;

		 black[0] = new Move(1, 0);
		 black[1] = new Move(3, 0);
		 black[2] = new Move(5, 2);
		 black[3] = new Move(6, 2);
		 black[4] = new Move(3, 3);
		 black[5] = new Move(3, 4);
		 black[6] = new Move(6, 4);
		 black[7] = new Move(1, 6);
		 black[8] = new Move(4, 7);
		 // black[9] = new Move(5, 7);

		 white[0] = new Move(0, 1);
		 white[1] = new Move(7, 1);
		 white[2] = new Move(0, 2);
		 white[3] = new Move(2, 3);
		 white[4] = new Move(4, 3);
		 white[5] = new Move(7, 3);
		 white[6] = new Move(0, 4);
		 white[7] = new Move(7, 4);
		 white[8] = new Move(6, 6);
		 // white[9] = new Move(7, 6);
    

		 for (int i=0; i<9; i++) {
		 	black[i] = randomMove(Chip.BLACK);
		 	
		 	white[i] = randomMove(Chip.WHITE);
		 }
		/*for (int i=0; i<9; i++) {
			bd.addChip(white[i], Chip.WHITE);
			bd.setPieces(Chip.WHITE);
			bd.addChip(black[i], Chip.BLACK);
			bd.setPieces(Chip.BLACK);
		}*/
		
		wPoss = LegalMoves.possibleMoves(bd, Chip.WHITE);
		bPoss = LegalMoves.possibleMoves(bd, Chip.BLACK);
		bd.dumpBoard();
		for (int i=0; wPoss[i]!=null; i++) {
			System.out.println("For White: Score of move at (" + wPoss[i].x1 + ", " + wPoss[i].y1 + ") is " 
				+ AI.evaluate(bd, wPoss[i], Chip.WHITE));
		}
		System.out.println("\n\n");
		for (int i=0; bPoss[i]!=null; i++) {
			System.out.println("For Black: Score of move at (" + bPoss[i].x1 + ", " + bPoss[i].y1 + ") is " 
				+ AI.evaluate(bd, bPoss[i], Chip.BLACK));
		}
		// System.out.println("BLACK has " + bd.howManyConnections(Chip.BLACK) + " connections.");			
		// System.out.println("WHITE has " + bd.howManyConnections(Chip.WHITE) + " connections.");


	}
	private static Move randomMove(int myColor){
    int x;
    int y;
    int prev_x, prev_y;
    Random rn = new Random();
    Move prev; 

    x = rn.nextInt(8);
    y = rn.nextInt(8);

    
    Move random = new Move(x,y);

    while(!LegalMoves.isLegal(bd, random, myColor)){
      prev = new Move(random.x1, random.y1);
      x = rn.nextInt(8);
      y = rn.nextInt(8);

      //System.out.println(x + "" + y);
      //create STEP move if 10 pieces are down
      if(bd.getPieces(myColor) >= 10){
        random = new Move(x, y, prev.x1, prev.y1);
        //System.out.println("Created a STEP move.");
        continue;
      }

      random = new Move(x, y);
    }

    bd.addChip(random, myColor); 
    prev_x = random.x1;
    prev_y = random.y1;
    bd.setPieces(myColor);
    
    
    //Network Testing
    // System.out.print("MachinePlayer has " + bd.howManyConnections(myColor) + " connections. ");
    
    return random; 	  	  	    	    	    	    	

  }
 
}



