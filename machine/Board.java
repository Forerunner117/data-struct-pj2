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
				return explore(col, board[0][i], 1, Direction.E);
			}

		}
		if(col == Chip.BLACK){//Check the goal on the top.
		for (int i=0; i<7; i++) {
			if(board[i][0].returnColor() == col)						
				unflagAllChipsOfColor(col);
				return explore(col, board[i][0], 1, Direction.S);
			}
		}
		return false;
	}
	boolean explore(int col, Chip chip, int len, int dir){
		chip.flag();
		int x = chip.getX();
		int y = chip.getY();
		Chip neighbor = board[0][0];
		neighbor = null;  //Haven't found a neighbor yet.
		int curr_dir = -1; //There is not current direction yet.
		for (int i=-1; i<=1; i++) {
			for (int j=-1; j<=1; j++) {

				//Running off board.
				if( (i == 0) && (j==0)) continue;
				if( ((x + i ) < 0)  ||  ((y + j) < 0) || ((x + i) > 7) || ((y + j) > 7) ) 
					continue; 

				//Eliminating Corners.
				if( (x + i == 0 && y + j == 0)  ||  (x + i == 7 && y + j == 0)   ||  
				    (x + i == 7 && y + j == 7)  ||  (x + i == 0 && y + j == 7) )
					continue;
				
				/**Finding a neighbor.
				 * The looking functions make sure
				 * that I don't run off the board. 
				 */
				if( i == -1 && j == -1){
					//look SW
					neighbor = lookSW(x, y);
					if(neighbor != null)
						curr_dir = Direction.SW;
				}
				if( i == -1 && j == 0){
					//look W
					neighbor = lookW(x, y);
					if(neighbor != null)
						curr_dir = Direction.W;
				}
				if( i == -1 && j == 1){
					//look NW
					neighbor = lookNW(x, y);
					if(neighbor != null)
						curr_dir = Direction.NW;
				}
				if ( i == 0 && j == 1){
					//look N
					neighbor = lookN(x, y);
					if(neighbor != null)
						curr_dir = Direction.N;
				}
				if (i == 1 && j == 1){
					//look NE
					neighbor = lookNE(x, y);
					if(neighbor != null)
						curr_dir = Direction.NE;
				}
				if( i == 1 && j == 0){
					//look E
					neighbor = lookE(x, y);
					if(neighbor != null)
						curr_dir = Direction.E;
				}
				if( i == 1 && j == -1){
					//look SE
					neighbor = lookSE(x ,y);
					if(neighbor != null)
						curr_dir = Direction.SE;
				}
				if ( i == 0 && j == -1){
					//look S
					neighbor = lookS(x, y);
					if(neighbor != null)
						curr_dir = Direction.S;
				}
		
				//Applying logic to neighbors

				if(neighbor == null)//If you have no neighbors, there must not be a network.
					continue;
				if(neighbor.returnColor() != col)//wrong color
					continue;
				if(curr_dir == dir)//same direction
					continue;

				if(neighbor.isFlagged())//already visited
					continue;
				if( (col == Chip.WHITE && neighbor.getX() == 0) || //NOTE: I'm assuming white start_goal is left column
				    (col == Chip.BLACK && neighbor.getY() == 0))  //NOTE: I'm assuming black start_goal is top row
					continue;
				
				if( (col == Chip.BLACK && neighbor.getY() == 7) || (col == Chip.WHITE && neighbor.getX() == 7)){
					if (len >= 5) return true;
				}
				else
					if(explore(col, neighbor, len+1, curr_dir))
						return true;
				}//end inner for
			}//end outer for
		
		chip.unflag();//chip has no neighbors...how sad.
		return false;
	}



      /*if no neighbor (because you ran off board), continue;
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
  }*/


	Chip lookN(int i, int j){
		int x = i;
		int y = j;
		Chip neighbor = board[x][y];
		while(neighbor != null && y>8){
			y--;
			neighbor = board[x][y];
		}
		if(neighbor == null)
			return null;
		else
			return neighbor;
	}
	Chip lookNE(int i, int j){
		int x = i;
		int y = j;
		Chip neighbor = board[x][y];
		while(neighbor != null && x<8 && y>0){
			x++;
			y--;
			neighbor = board[x][y];
		}
		if(neighbor == null)
			return null;
		else
			return neighbor;
	}
	Chip lookE(int i, int j){
		int x = i;
		int y = j;
		Chip neighbor = board[x][y];
		while(neighbor != null && x<8){
			x++;
			neighbor = board[x][y];
		}
		if(neighbor == null)
			return null;
		else
			return neighbor;
	}
	Chip lookSE(int i, int j){
		int x = i;
		int y = j;
		Chip neighbor = board[x][y];
		while(neighbor != null && x<8 && y<8){
			x++;
			y++;
			neighbor = board[x][y];
		}
		if(neighbor == null)
			return null;
		else
			return neighbor;
	}
	Chip lookS(int i, int j){
		int x = i;
		int y = j;
		Chip neighbor = board[x][y];
		while(neighbor != null && y<8){
			y++;
			neighbor = board[x][y];
		}
		if(neighbor == null)
			return null;
		else
			return neighbor;
	}
	Chip lookSW(int i, int j){
		int x = i;
		int y = j;
		Chip neighbor = board[x][y];
		while(neighbor != null && x>0 && y<8){
			x--;
			y++;
			neighbor = board[x][y];
		}
		if(neighbor == null)
			return null;
		else
			return neighbor;
	}
	Chip lookW(int i, int j){
		int x = i;
		int y = j;
		Chip neighbor = board[x][y];
		while(neighbor != null && x>0){
			x--;
			neighbor = board[x][y];
		}
		if(neighbor == null)
			return null;
		else
			return neighbor;
	}
	Chip lookNW(int i, int j){
		int x = i;
		int y = j;
		Chip neighbor = board[x][y];
		while(neighbor != null && x>0 && y>0){
			x--;
			y--;
			neighbor = board[x][y];
		}
		if(neighbor == null)
			return null;
		else
			return neighbor;
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
