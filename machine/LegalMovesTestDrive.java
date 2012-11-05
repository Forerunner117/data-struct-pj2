package machine;
import player.*;

public class LegalMovesTestDrive{

	public static void main(String[] args){
		MachinePlayer whitePlayer = new MachinePlayer(Chip.WHITE);

		// constructs perfectly legal center moves
		Move center1 = new Move(3, 3);
		Move center2 = new Move(3, 4);
		Move center3 = new Move(4, 3);
		Move center4 = new Move(4, 4);

		//constructs moves off the board
		Move off1 = new Move(-1, -1);
		Move off2 = new Move(8, 10);
		Move off3 = new Move(9, -5);
		Move off4 = new Move(-117, 117);

		// constructs moves for the four corners
		Move corner1 = new Move(0, 0);
		Move corner2 = new Move(0, 7);
		Move corner3 = new Move(7, 0);
		Move corner4 = new Move(7, 7);

		// constructs moves for white goals
		Move whiteGoal1 = new Move(0, 1);
		Move whiteGoal2 = new Move(0, 2);
		Move whiteGoal3 = new Move(0, 3);
		Move whiteGoal4 = new Move(0, 4);
		Move whiteGoal5 = new Move(0, 5);
		Move whiteGoal6 = new Move(0, 6);

		Move whiteGoal7 = new Move(7, 1);
		Move whiteGoal8 = new Move(7, 2);
		Move whiteGoal9 = new Move(7, 3);
		Move whiteGoal10 = new Move(7, 4);
		Move whiteGoal11 = new Move(7, 5);
		Move whiteGoal12 = new Move(7, 6);

		//constructs moves for black goals
		Move blackGoal1 = new Move(1, 0);
		Move blackGoal2 = new Move(2, 0);
		Move blackGoal3 = new Move(3, 0);
		Move blackGoal4 = new Move(4, 0);
		Move blackGoal5 = new Move(5, 0);
		Move blackGoal6 = new Move(6, 0);

		Move blackGoal7 = new Move(1, 7);
		Move blackGoal8 = new Move(2, 7);
		Move blackGoal9 = new Move(3, 7);
		Move blackGoal10 = new Move(4, 7);
		Move blackGoal11 = new Move(5, 7);
		Move blackGoal12 = new Move(6, 7);


		// constructs a cluster move
		Move whiteCluster1 = new Move(3, 1);
		Move whiteCluster2 = new Move(4, 3);






		/**Test moves for legality
		**************************************************************************************************/
		
		//Center empty moves
		System.out.println("CHECKING CENTER MOVES*****************************************************************");
		System.out.println("Center move should be true: " + LegalMoves.isLegal(whitePlayer.bd, center1, Chip.WHITE));
		System.out.println("Center move should be true: " + LegalMoves.isLegal(whitePlayer.bd, center2, Chip.WHITE));
		System.out.println("Center move should be true: " + LegalMoves.isLegal(whitePlayer.bd, center3, Chip.BLACK));
		System.out.println("Center move should be true: " + LegalMoves.isLegal(whitePlayer.bd, center4, Chip.BLACK));

		//Moves off the board (false)
		System.out.println("\nCHECKING MOVES OFF BOARD*************************************************************");
		System.out.println("Move off board should be false: " + LegalMoves.isLegal(whitePlayer.bd, off1, Chip.WHITE));
		System.out.println("Move off board should be false: " + LegalMoves.isLegal(whitePlayer.bd, off2, Chip.WHITE));
		System.out.println("Move off board should be false: " + LegalMoves.isLegal(whitePlayer.bd, off3, Chip.WHITE));
		System.out.println("Move off board should be false: " + LegalMoves.isLegal(whitePlayer.bd, off4, Chip.WHITE));

		//Fill spaces with chips and check for legality
		whitePlayer.bd.addChip(3, 2, Chip.WHITE);
		whitePlayer.bd.addChip(3, 3, Chip.WHITE);
		whitePlayer.bd.addChip(3, 4, Chip.BLACK);

		System.out.println("\nCHECKING CENTER MOVES");
		System.out.println("*****************************************************************");
	    System.out.println("Center move should be false: " + LegalMoves.isLegal(whitePlayer.bd, center1, Chip.WHITE));
		System.out.println("Center move should be false: " + LegalMoves.isLegal(whitePlayer.bd, center2, Chip.WHITE));
		System.out.println("Center move should be true: " + LegalMoves.isLegal(whitePlayer.bd, center3, Chip.BLACK));
		System.out.println("Center move should be true: " + LegalMoves.isLegal(whitePlayer.bd, center4, Chip.BLACK));

		//Check for clustering
		System.out.println("\nCHECKING FOR CLUSTERS");
		System.out.println("*****************************************************************");
		System.out.println("Cluster move should be false: " + LegalMoves.isLegal(whitePlayer.bd, whiteCluster1, Chip.WHITE));		
		System.out.println("Cluster move should be false: " + LegalMoves.isLegal(whitePlayer.bd, whiteCluster2, Chip.WHITE));

		//Corners (false)
		System.out.println("Corner move should be false: " + LegalMoves.isLegal(whitePlayer.bd, corner1, Chip.WHITE));
		System.out.println("Corner move should be false: " + LegalMoves.isLegal(whitePlayer.bd, corner2, Chip.WHITE));
		System.out.println("Corner move should be false: " + LegalMoves.isLegal(whitePlayer.bd, corner3, Chip.WHITE));
		System.out.println("Corner move should be false: " + LegalMoves.isLegal(whitePlayer.bd, corner4, Chip.WHITE));

		//Opponent's goal
		System.out.println("\nCHECKING OPPONENT'S GOALS");
		System.out.println("*****************************************************************");
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, whiteGoal1, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, whiteGoal2, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, whiteGoal3, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, whiteGoal4, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, whiteGoal5, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, whiteGoal6, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, whiteGoal7, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, whiteGoal8, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, whiteGoal9, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, whiteGoal10, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, whiteGoal11, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, whiteGoal12, Chip.BLACK));
		
		System.out.println("White move into black goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, blackGoal1, Chip.WHITE));
		System.out.println("White move into black goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, blackGoal2, Chip.WHITE));
		System.out.println("White move into black goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, blackGoal3, Chip.WHITE));
		System.out.println("White move into black goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, blackGoal4, Chip.WHITE));
		System.out.println("White move into black goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, blackGoal5, Chip.WHITE));
		System.out.println("White move into black goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, blackGoal6, Chip.WHITE));
		System.out.println("White move into black goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, blackGoal7, Chip.WHITE));
		System.out.println("White move into black goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, blackGoal8, Chip.WHITE));
		System.out.println("White move into black goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, blackGoal9, Chip.WHITE));
		System.out.println("White move into black goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, blackGoal10, Chip.WHITE));
		System.out.println("White move into black goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, blackGoal11, Chip.WHITE));
		System.out.println("White move into black goal should be false: " + LegalMoves.isLegal(whitePlayer.bd, blackGoal12, Chip.WHITE));


		Move random = whitePlayer.chooseMove();

		System.out.println("x: " + random.x1 + "y: " + random.y1);
		whitePlayer.bd.dumpBoard();


	}


}//END CLASS