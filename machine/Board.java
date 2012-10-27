/* Board.java */

package machine;

import player.*;

class Board{

	private Chip[][] board = new Chip[8][8];
		
	public Board(){
		/*for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = new Chip(i, j, Chip.EMPTY);						
			}	
		} I don't think we should set each cell to have a color equal to empty,
		I think we should leave empty cells as just null references.*/
		board[0][0].changeColor(Chip.GREY);
		board[0][7].changeColor(Chip.GREY);
		board[7][0].changeColor(Chip.GREY);
		board[7][7].changeColor(Chip.GREY);
	}

	static Chip returnChip(int x, int y){		
		return board[x][y];
	}
	static void main (String[] args){
		/*Board c = new Board();
		Chip d = c.returnChip(0,0);
		int color = d.returnColor();
		System.out.println(color);*/
	}
	static void addChip(int x, int y, int color){
		board[x][y].changeColor(color);					
	}		
}
