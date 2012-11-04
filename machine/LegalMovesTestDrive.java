package machine;
import player.*;

public class LegalMovesTestDrive{

	public static void main(String[] args){
		MachinePlayer white = new MachinePlayer(Chip.WHITE);
		MachinePlayer black = new MachinePlayer(Chip.BLACK);

		// constructs perfectly legal center moves
		Move center1 = new Move(3, 3);
		Move center2 = new Move(3, 4);
		Move center3 = new Move(4, 3);
		Move center4 = new Move(4, 4);

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






		/**Test moves for legality
		**************************************************************************************************/
		
		//Center empty moves
		System.out.println("Center move should be true: " + LegalMoves.isLegal(white, center1, Chip.WHITE));
		System.out.println("Center move should be true: " + LegalMoves.isLegal(white, center2, Chip.WHITE));
		System.out.println("Center move should be true: " + LegalMoves.isLegal(black, center3, Chip.BLACK));
		System.out.println("Center move should be true: " + LegalMoves.isLegal(black, center4, Chip.BLACK));

		//Fill spaces with chips and check for legality (same 4 spaces as above)
		white.bd.addChip(3, 3, Chip.WHITE);
		white.bd.addChip(3, 4, Chip.BLACK);
		//white.bd.addChip(4, 3, Chip.BLACK);
		//white.bd.addChip(4, 4, Chip.WHITE);

	    System.out.println("Center move should be false: " + LegalMoves.isLegal(white, center1, Chip.WHITE));
		System.out.println("Center move should be true: " + LegalMoves.isLegal(white, center2, Chip.WHITE));
		System.out.println("Center move should be true: " + LegalMoves.isLegal(black, center3, Chip.BLACK));
		System.out.println("Center move should be true: " + LegalMoves.isLegal(black, center4, Chip.BLACK));

		//Center filled moves

		//Corners (false)
		System.out.println("Corner move should be false: " + LegalMoves.isLegal(white, corner1, Chip.WHITE));
		System.out.println("Corner move should be false: " + LegalMoves.isLegal(white, corner2, Chip.WHITE));
		System.out.println("Corner move should be false: " + LegalMoves.isLegal(white, corner3, Chip.WHITE));
		System.out.println("Corner move should be false: " + LegalMoves.isLegal(white, corner4, Chip.WHITE));

		//Opponent's goal
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(black, whiteGoal1, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(black, whiteGoal2, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(black, whiteGoal3, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(black, whiteGoal4, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(black, whiteGoal5, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(black, whiteGoal6, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(black, whiteGoal7, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(black, whiteGoal8, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(black, whiteGoal9, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(black, whiteGoal10, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(black, whiteGoal11, Chip.BLACK));
		System.out.println("Black move into white goal should be false: " + LegalMoves.isLegal(black, whiteGoal12, Chip.BLACK));
		


	}


}//END CLASS