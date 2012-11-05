/* MachinePlayer.java */

package machine;

import player.*;
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
    //call to evaluation function should return a Move object that ranked highest    
    return new Move();
  } 

  // If the Move m is legal, records the move as a move by the opponent
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method allows your opponents to inform you of their moves.
  public boolean opponentMove(Move m) {
    //if(LegalMoves.isLegal(m, oppColor))
     // bd.board[m.x1][m.x2] = new Chip(m.x1, m.y1, oppColor); //should be replaced with a bd.makeMove(Move m) call
    //else
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

}
