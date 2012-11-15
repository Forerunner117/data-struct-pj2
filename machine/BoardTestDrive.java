package machine;

import player.*;

public class BoardTestDrive{
	public static void main(String[] args) {
		Board bd = new Board();
		Move black[] = new Move[10];
		Move white[] = new Move[10];
		
		 black[0] = new Move(6, 0);
		 black[1] = new Move(2, 1);
		 black[2] = new Move(4, 1);
		 black[3] = new Move(6, 1);
		 black[4] = new Move(3, 3);
		 black[5] = new Move(3, 4);
		 black[6] = new Move(5, 4);
		 black[7] = new Move(3, 6);
		 black[8] = new Move(1, 7);
		 black[9] = new Move(4, 7);

		 white[0] = new Move(1, 1);
		 white[1] = new Move(1, 7);
		 white[2] = new Move(1, 2);
		 white[3] = new Move(4, 3);
		 white[4] = new Move(0, 4);
		 white[5] = new Move(2, 4);
		 white[6] = new Move(7, 4);
		 white[7] = new Move(2, 5);
		 white[8] = new Move(5, 6);
		 white[9] = new Move(6, 6);

		for (int i=0; i<10; i++) {
			bd.addChip(white[i], Chip.WHITE);
			bd.setPieces(Chip.WHITE);
			bd.addChip(black[i], Chip.BLACK);
			bd.setPieces(Chip.BLACK);
		}
		// if (bd.hasNetwork(Chip.BLACK)) {
			// System.out.println(bd.hasNetwork(Chip.BLACK));
		System.out.println("This board has " + bd.howManyConnections(Chip.BLACK) + " connections.");			
		//}
		bd.dumpBoard();


		//*****************************************************************************************
		//*****************************************************************************************
		//                        Austin's Board testing starts here
		//*****************************************************************************************
		//*****************************************************************************************


		/*//TESTING getCurrChips METHOD
		Chip[] blarg = bd.getCurrChips(Chip.WHITE);
		int i = 0;

		System.out.println("Getting the current chip placements:");	
		while(blarg[i] != null){			
			System.out.println(blarg[i].x + ", " + blarg[i].y);
			++i;
		}
		//It works... Woot!

		//TESTING possibleMoves METHOD

		//construct an array of possible moves
		Move[] possibleMoves = LegalMoves.possibleMoves(bd, Chip.BLACK);

		//possibleMoves()
		System.out.println("Printing possibleMoves for BLACK:");
		int k = 0;
		while(possibleMoves[k] != null){
			System.out.println(possibleMoves[k].x1 + ", " + possibleMoves[k].y1
			 + " from " + possibleMoves[k].x2 + ", " + possibleMoves[k].y2);
			++k;
		}
		//Also works

		bd.dumpBoard();*/
	}
}


