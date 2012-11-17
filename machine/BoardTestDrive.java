package machine;

import player.*;

public class BoardTestDrive{
	public static void main(String[] args) {
		Board bd = new Board();
		Move black[] = new Move[10];
		Move white[] = new Move[10];
		


		 /*
		 for (int i = 0; i < 3; i++) {
		 	bd.addChip(black[i], Chip.BLACK);
		 	bd.setPieces(Chip.BLACK);
		 	bd.addChip(white[i], Chip.WHITE);
		 	bd.setPieces(Chip.WHITE);
		 }*/

		 black[0] = new Move(2, 2);
		 black[1] = new Move(5, 3);
		 black[2] = new Move(2, 4);
		 black[3] = new Move(6, 5);
		 black[4] = new Move(3, 7);
		 black[5] = new Move(1, 1);
		 /*black[6] = new Move(6, 4);
		 black[7] = new Move(1, 6);
		 black[8] = new Move(4, 7);
		 black[9] = new Move(5, 7);*/

		 white[0] = new Move(0, 4);
		 white[1] = new Move(1, 3);
		 white[2] = new Move(3, 3);
		 white[3] = new Move(4, 4);
		 white[4] = new Move(6, 4);
		 white[5] = new Move(7, 3);
		 /*white[6] = new Move(0, 4);
		 white[7] = new Move(7, 4);
		 white[8] = new Move(6, 6);
		 white[9] = new Move(7, 6);*/

		for (int i=0; i<6; i++) {
			bd.addChip(white[i], Chip.WHITE);
			bd.setPieces(Chip.WHITE);
			bd.addChip(black[i], Chip.BLACK);
			bd.setPieces(Chip.BLACK);
		}
		bd.dumpBoard();
		System.out.println("Does white have network? " + bd.hasNetwork(Chip.WHITE));
		/*System.out.println("BLACK has " + bd.howManyConnections(Chip.BLACK) + " connections.");			
		System.out.println("WHITE has " + bd.howManyConnections(Chip.WHITE) + " connections.");
		System.out.println("BLACK has network " + bd.hasNetwork(Chip.BLACK));
		System.out.println("BLACK has " + bd.getMaxExploreLength() + " explore length.");
		System.out.println("WHITE has network " + bd.hasNetwork(Chip.WHITE));
		System.out.println("WHITE has " + bd.getMaxExploreLength() + " explore length.");/*

		bd.dumpBoard();
		// if (bd.hasNetwork(Chip.BLACK)) {
			// System.out.println(bd.hasNetwork(Chip.BLACK));
		//}
		// bd.dumpBoard();


		//*****************************************************************************************
		//*****************************************************************************************
		//                        Austin's Board testing starts here
		//*****************************************************************************************
		//*****************************************************************************************


		/*TESTING getCurrChips METHOD
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


