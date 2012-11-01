/* Board.java */

package machine;

import player.*;

class Board{

	private Chip[][] board = new Chip[8][8];
	int whitePieces;
	int blackPieces;

		
	public Board(){
		/*for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new Chip(i, j, Chip.EMPTY);						
			}	
		}
		/*I don't think we should set each cell to have a color equal to empty,
		I think we should leave empty cells as just null references.*/

		board[0][0].changeColor(Chip.GREY);
		board[0][7].changeColor(Chip.GREY);
		board[7][0].changeColor(Chip.GREY);
		board[7][7].changeColor(Chip.GREY);
	}

	Chip returnChip(int x, int y){		
		return board[x][y];
	}
	static void main (String[] args){
		/*Board c = new Board();
		Chip d = c.returnChip(0,0);
		int color = d.returnColor();
		System.out.println(color);*/
	}
	void addChip(int x, int y, int color){
		board[x][y]=new Chip(x,y,color);					
	}	

	int numPieces(int col){
		int pieces = 0;
		for (int i=0; i<7; i++) {
			for (int j=0; j<7; j++) {
				if(board[i][j].returnColor() == col)
					pieces++;
			}
		}
		return pieces;
	}
	void removeChip(int x, int y)
	{
	board[x][y]= null;	
	}
	void unflagAllChipsOfColor(int col){
		for (int i=0; i<7; i++) {
			for (int j=0; j<7; j++) {
				if(board[i][j].returnColor() == col)
					board[i][j].unflag();
			}
		}
	}
	boolean hasNetwork(int col){
		if(col == Chip.WHITE){
		for (int i=1; i<7; i++) {//Check the first goal on the left.
			if(board[0][i].returnColor() == col)						
				unflagAllChipsOfColor(col);
				return explore(col, board[0][i], 1, Direction.W);
			}

		}
		if(col == Chip.BLACK){//Check the goal on the right.
		for (int i=0; i<7; i++) {
			if(board[i][0].returnColor() == col)						
				unflagAllChipsOfColor(col);
				return explore(col, board[0][i], 1, Direction.W);
			}
		}
		return false;
	}
	boolean explore(int col, Chip chip, int len, int dir){
		return false;
	}
  // hasNetwork() code skeleton
  //

  /*hasNework(int color) {

    for each goal_chip in start_goal:
      set visited = false for every chip on the board of our color

      explore(color, goal_chip, int len, int dir)
    }
  }

  explore(int color, Chip chip, int len, int dir) {

    mark chip as visited

    for each direction (there are 8 of them!) look for first chip you
      can see in that direction; call it "neighbor"
    {
      if no neighbor (because you ran off board), continue;
      if neighbor is not your color, continue;
      if neighbor is the same direction you just came from, continue;
      if neighbor was already visited, continue;
      if neighbor is in start_goal, continue;
      if neighbor is in end_goal {
	if (len >= 5) return true;
      }
      else
	if (explore(color, neighbor, len+1, curr_direction)) return true;
    }

    mark chip as unvisited
    return false;
  }
	}*/
}
