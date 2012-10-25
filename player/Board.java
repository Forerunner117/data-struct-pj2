

public class Board{

	private Chip[][] board = new Chip[8][8];
		
	public Board()
	{
	for(int i = 0; i < 8; i++)
	{
		for(int j = 0; j < 8; j++)
		{
		board[i][j] = new Chip(Chip.EMPTY);	
			
			
		}
		
	}
	board[0][0].changeColor(Chip.GREY);
	board[0][7].changeColor(Chip.GREY);
	board[7][0].changeColor(Chip.GREY);
	board[7][7].changeColor(Chip.GREY);
	
		}
	public Chip returnChip(int x, int y)
	{
		
		return board[x][y];
	}
	public static void main (String[] args)
	{
		/*Board c = new Board();
		Chip d = c.returnChip(0,0);
		int color = d.returnColor();
		System.out.println(color);*/
	}
	public void addChip(int x, int y, int color)
	{
	board[x][y].changeColor(color);	
		
		
	}
	
	
	
}
