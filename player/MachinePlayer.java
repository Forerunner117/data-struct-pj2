/* MachinePlayer.java */

package player;

import machine.*;
import java.util.Random;
/**
 *  An implementation of an automatic Network player.  Keeps track of moves
 *  made by both players.  Can select a move for itself.
 */
public class MachinePlayer extends Player {

  private Board bd; 
  private int myColor;
  private int oppColor;
  private int searchDepth; //package protected

  // Creates a machine player with the given color.  Color is either 0 (black)
  // or 1 (white).  (White has the first move.)
  public MachinePlayer(int color) {
    bd = new Board();
    myColor = color;

    if(myColor == Chip.WHITE)
      oppColor = Chip.BLACK;
    else
      oppColor = Chip.WHITE;
  }


  // Creates a machine player with the given color and search depth.  Color is
  // either 0 (black) or 1 (white).  (White has the first move.)
  public MachinePlayer(int color, int searchDepth) {
    this(color);
    this.searchDepth = searchDepth;
  }

  // Returns a new move by "this" player.  Internally records the move (updates
  // the internal game board) as a move by "this" player.
  public Move chooseMove() {    
    System.out.println("chooseMove called. And it got passed " + myColor + " for a color");

    if(makeFirstMoves() != null){
      Move firstMove = makeFirstMoves();
      bd.addChip(firstMove, myColor);
      bd.setPieces(myColor);
      bd.setLastMove(firstMove, myColor);
      return firstMove;
    }
    //call to evaluation function should return a Move object that ranked highest    

    return randomMove();
        
  } 

  // If the Move m is legal, records the move as a move by the opponent
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method allows your opponents to inform you of their moves.
  public boolean opponentMove(Move m) {
    System.out.println("oppMove called.");
    if(LegalMoves.isLegal(bd, m, oppColor)){
      bd.addChip(m, oppColor);
      bd.setPieces(oppColor);
      bd.setLastMove(m, oppColor);
      return true;
    } 
    else{
        System.out.println("Cheater!");
        return false;      
    }     
  }

  // If the Move m is legal, records the move as a move by "this" player
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method is used to help set up "Network problems" for your
  // player to solve.
  public boolean forceMove(Move m) {
    return false;
  }
  
  private Move smartMove(int color, int searchDepth)

  private Move randomMove(){
    int x;
    int y;
    int prev_x, prev_y;
    Random rn = new Random();
    Move prev; 

    x = rn.nextInt(8);
    y = rn.nextInt(8);

    
    Move random = new Move(x,y);
    

    while(!LegalMoves.isLegal(bd, random, myColor)){
      prev = bd.getLastMove(myColor);
      //prev = new Move(random.x1, random.y1);
      x = rn.nextInt(8);
      y = rn.nextInt(8);

      System.out.println(x + "" + y);
      //create STEP move if 10 pieces are down
      if(bd.getPieces(myColor) >= 10){
        random = new Move(x, y, prev.x1, prev.y1);
        System.out.println("Created a STEP move.");
        continue;
      }

      random = new Move(x, y);
    }

    bd.addChip(random, myColor); 
    prev_x = random.x1;
    prev_y = random.y1;
    bd.setPieces(myColor);
    bd.setLastMove(random, myColor);
    
    //Network Testing
    bd.testNetwork(myColor);
    
    return random; 	  	  	    	    	    	    	
  }

  private Move makeFirstMoves(){
    Move center1 = new Move(3, 3);
    Move center2 = new Move(3, 4);
    Move center3 = new Move(4, 3);
    Move center4 = new Move(4, 4);

    if(LegalMoves.isLegal(bd, center1, myColor)){
      return center1;
    }

    if(LegalMoves.isLegal(bd, center2, myColor)){
      return center2;
    }

    if(LegalMoves.isLegal(bd, center3, myColor)){
      return center3;
    }

    if(LegalMoves.isLegal(bd, center4, myColor)){
      return center4;
    }

    return null;
  }

  public Board getBoard(){
    return bd;
  }

}
