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
  private int searchDepth;

  /**
   * Constructs a machine player with the given color.  Color is either 0 (black)
   * or 1 (white).  (White has the first move.)
   * @param color an integer representing color.
   **/
  public MachinePlayer(int color) {
    bd = new Board();
    myColor = color;

    if(myColor == Chip.WHITE)
      oppColor = Chip.BLACK;
    else
      oppColor = Chip.WHITE;

    int searchDepth = 3;
  }

  /**
   * Constructs a machine player with the given color and search depth.  Color is
   * either 0 (black) or 1 (white).  (White has the first move.)
   * @param color an integer representing color.
   * @param searchDepth an integer of how far down the game tree to recurse.
   **/
  public MachinePlayer(int color, int searchDepth) {
    this(color);
    this.searchDepth = searchDepth;
  }

  /**
   * chooseMove() returns a new move by "this" player.  Internally records the move (updates
   * the internal game board) as a move by "this" player.
   * @return new move by "this" player.
   **/
  public Move chooseMove() {    
    if(makeFirstMoves() != null){
      Move firstMove = makeFirstMoves();
      bd.addChip(firstMove, myColor);
      return firstMove;
    }

    return AI.smartMove(bd, myColor, oppColor, searchDepth);   
  }
   
  /**
   * oppnentMove() If the Move m is legal, records the move as a move by the opponent
   * (updates the internal game board) and returns true.  If the move is
   * illegal, returns false without modifying the internal state of "this"
   * player.  This method allows your opponents to inform you of their moves.
   * @param m is the opponents move.
   * @return true if move is legal, false if move is illegal.
   **/
  public boolean opponentMove(Move m) {
    if(LegalMoves.isLegal(bd, m, oppColor)){
      bd.addChip(m, oppColor);
      return true;
    } 
    else{
        return false;      
    }     
  }

  /**
   * forceMove()  If the Move m is legal, records the move as a move by "this" player
   * (updates the internal game board) and returns true.  If the move is
   * illegal, returns false without modifying the internal state of "this"
   * player.  This method is used to help set up "Network problems" for your
   * player to solve.
   * @param m is the move to be forced
   * @return true if move is legal, false if move is illegal.
   **/
  public boolean forceMove(Move m){
    if(LegalMoves.isLegal(bd, m, myColor)){
      bd.addChip(m, myColor);
      return true;
    }
    else
      return false;
  }

 /**
  * makeFirstMoves() is used to take the center is the beginning of the game.
  * @return a move that is in the center.
  **/
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

  // Gets the board.
  public Board getBoard(){
    return bd;
  }

  // Gets MachineColor's color.
  public int getMyColor(){
    return myColor;
  }

  // Gets opponent's color.
  public int getOppColor(){
    return oppColor;
  }

  // Gets the search depth.
  public int getSearchDepth(){
    return searchDepth;
  }
}
