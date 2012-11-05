/* MachinePlayer.java */

package machine;

import player.*;
import java.util.Random;
/**
 *  An implementation of an automatic Network player.  Keeps track of moves
 *  made by both players.  Can select a move for itself.
 */
public class MachinePlayer extends Player {

  Board bd; 
  private int myColor;
  private int oppColor;
  int searchDepth; //package protected

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
    if(makeFirstMoves() != null)
      return makeFirstMoves();
    //call to evaluation function should return a Move object that ranked highest    
      int x;
      int y;
      Random rn = new Random();
      x = rn.nextInt(7);
      y = rn.nextInt(7);
      
      Move random = new Move(x,y);
      if(LegalMoves.isLegal(bd, random, myColor)) 
        return random;
      else 
        chooseMove();        
      return new Move(0, 0); 
  } 

  // If the Move m is legal, records the move as a move by the opponent
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method allows your opponents to inform you of their moves.
  public boolean opponentMove(Move m) {
    if(LegalMoves.isLegal(bd, m, oppColor)){
      bd.addChip(m.x1, m.y1, oppColor);
      return true;
    } 
    else
      return false;
  }

  // If the Move m is legal, records the move as a move by "this" player
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method is used to help set up "Network problems" for your
  // player to solve.
  public boolean forceMove(Move m) {
    return false;
  }
  
  public Move randomMove(int color){
  	  int x;
  	  int y;
  	  Random rn = new Random();
  	  x = rn.nextInt() % 8;
  	  y = rn.nextInt() % 8;
  	  
  	  Move random = new Move(x,y);
  	  if(LegalMoves.isLegal(bd, random, color)) 
        return random;
  	  else 
        randomMove(color);
  	  	
  	  return null;  	  	  	    	    	    	    	
  }

  public Move makeFirstMoves(){
    Move center1 = new Move(3, 3);
    Move center2 = new Move(3, 4);
    Move center3 = new Move(4, 3);
    Move center4 = new Move(4, 4);

    if(LegalMoves.isLegal(bd, center1, myColor))
      return center1;

    if(LegalMoves.isLegal(bd, center2, myColor))
      return center2;

    if(LegalMoves.isLegal(bd, center3, myColor))
      return center3;

    if(LegalMoves.isLegal(bd, center4, myColor))
      return center4;

    return null;
  }

}
