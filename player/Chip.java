

public class Chip{
	public static final int RED = 10;
	public static final int BLACK = 11;
	public static final int EMPTY = 12;
	public static final int GREY = 13;
	private int Color;
	private boolean flagged = false;
	
	public Chip()
	{                                                           
	Color = EMPTY;	
	}
	public Chip(int color){
		
		Color = color;
	}
	public void flag()
	{
		flagged = true;
	}
	public void unflag()
	{
		if( flagged = false)
			System.out.println("This isn't flagged");
		flagged = false;	
	}
	
	public boolean isFlagged()
	{
		if(flagged ==true)
			return true;
		else return false;
			
	}
	
	public int returnColor()
	{
		return Color;	
	}
	
	public void changeColor( int color)
	{
		Color = color;	
		
	}
}
