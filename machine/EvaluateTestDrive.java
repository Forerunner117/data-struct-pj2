/* EvaluateTestDrive.java */

package machine; 

import player.*;

public class EvaluateTestDrive{

public static void main(String[] args) {
	

Board bd = new Board();
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
			bd.addChip(white[i], Chip.WHITE);
			bd.setPieces(Chip.WHITE);
			bd.addChip(black[i], Chip.BLACK);
			bd.setPieces(Chip.BLACK);
		}
		
		wPoss = LegalMoves.possibleMoves(bd, Chip.WHITE);
		bPoss = LegalMoves.possibleMoves(bd, Chip.BLACK);

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

}



