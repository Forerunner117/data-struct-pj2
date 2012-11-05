/* Board.java */

package machine;

import player.*;

class Board{

    private Chip[][] board = new Chip[8][8];
    int whitePieces;
    int blackPieces;


    public Board(){
        for(int i = 0; i < 8; i++){
          for(int j = 0; j < 8; j++){
          board[i][j] = new Chip(i, j, Chip.EMPTY);
          }
          }
          
          /*I don't think we should set each cell to have a color equal to empty,
          I think we should leave empty cells as just null references.*/

        board[0][0] = new Chip (0, 0, Chip.GREY);
        board[0][7] = new Chip (0, 7, Chip.GREY);
        board[7][0] = new Chip (7 ,0, Chip.GREY);
        board[7][7] = new Chip (7, 7, Chip.GREY);
    }

    Chip returnChip(int x, int y){
        return board[x][y];
    }
    
    public static void main (String[] args){
        Board c = new Board();
        c.addChip(0, 2, Chip.WHITE);
        c.addChip(2, 2, Chip.WHITE);
        c.addChip(2, 5, Chip.WHITE);
        c.addChip(5, 5, Chip.WHITE);
        c.addChip(5, 2, Chip.WHITE);
        c.addChip(7, 2, Chip.WHITE);
        if(c.hasNetwork(Chip.WHITE))
            System.out.println("Found Network!");
        else
            System.out.println("Didn't find network");
        /*Chip d = c.returnChip(0,0);
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
      if(board[0][i].returnColor() == col){
        unflagAllChipsOfColor(col);
        return explore(col, board[0][i], 1, Direction.E);
      }
      }
    }
    if(col == Chip.BLACK){//Check the goal on the top.
    for (int i=0; i<7; i++) {
      if(board[i][0].returnColor() == col){       
        unflagAllChipsOfColor(col);
        return explore(col, board[i][0], 1, Direction.S);
      }
      }
    }
    return false;
  }
    boolean explore(int col, Chip chip, int len, int dir){
       System.out.println("\n\n\n####ENTERING EXPLORE###");
       System.out.println("Flag status of explore chip is " + chip.isFlagged() + " which should be false");
        chip.flag();

        int x = chip.getX();
        System.out.println("X position of explore chip is " + x);

        int y = chip.getY();
        System.out.println("Y position of explore Chip is " + y);        

        Chip neighbor = board[x][y];
        // System.out.println("neighbor is " + neighbor + " which should be null");
        int curr_dir = -1; //There is not current direction yet.
        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {

                if( (i == 0) && (j==0)) continue;

                //Running off board.
                if( ((x + i ) < 0)  ||  ((y + j) < 0) || ((x + i) > 7) || ((y + j) > 7) )
                    continue;

                //Eliminating Corners.
                if( (x + i == 0 && y + j == 0)  ||  (x + i == 7 && y + j == 0)   ||
                    (x + i == 7 && y + j == 7)  ||  (x + i == 0 && y + j == 7) )
                    continue;

                
                for(int k=0; k<8; k++){
                    if(((x + i*k) < 0)  ||  ((y + j*k) < 0) || ((x + i*k) > 7) || ((y + j*k) > 7))//Ran off the board
                        continue;
                    
                    neighbor = board[x + i*k][y + j*k];
                    curr_dir = Direction.getCurrDir(i, j);


                    /*System.out.println("At i = " + i + " , j = " + j + " and k = " + k +
                        " curr_dir is " + curr_dir);                
                    System.out.println("\nneighbor's color at (" + neighbor.getX() + ", " +neighbor.getY()
                        + ") is " + neighbor.returnColor() + " and current color is " + col);
                    System.out.println("\n\nAt i = " + i + " , j = " + j + " and k = " + k +
                        " len is " + len);*/
                //Applying logic to neighbors
                    /*if (neighbor.getX() == 2 && neighbor.getY() == 2) {
                      System.out.println(" x and y are 2");
                    }*/

                /*if(neighbor == Color.WHITE)
                    continue;*/
                /*if( i == 1 && j == 0 && k == 2){
                    System.out.println("I got to the logic");
                    System.out.println("neighbor's color is " + neighbor.returnColor() + " and current color is " + col);
                }*/
                
                if( neighbor.returnColor() != col){//wrong color
                     // System.out.println("\nNeighbor color is, " + neighbor.returnColor() + " and col is " + col + "...The If thinks these are different");
                    continue;
                }
                if(curr_dir == dir){ //same direction
                   System.out.println("The directions are the same\ncurr_dir is " + curr_dir + " dir is " + dir);
                    continue;
                  }
                if(neighbor.isFlagged()){//already visited
                   System.out.println("The neighbor already visited");
                    continue;
                  }
                if( (col == Chip.WHITE && neighbor.getX() == 0) || //NOTE: I'm assuming white start_goal is left column
                    (col == Chip.BLACK && neighbor.getY() == 0)){  //NOTE: I'm assuming black start_goal is top row
                    System.out.println("Found neighbor in start goal" + "neighbor.getX() is " + neighbor.getX() 
                      + " and neighbor.getY() is " + neighbor.getY());
                    continue;
                  }

                if( (col == Chip.BLACK && neighbor.getY() == 7) || (col == Chip.WHITE && neighbor.getX() == 7)){
                  System.out.println("Found neighbor in end goal. " + "neighbor.getY() is " + neighbor.getY() + " neighbor.getX() is "
                    + neighbor.getX());
                    if (len >= 5) return true;
                }
                else{
                  System.out.println("Recurssing");
                    if(explore(col, neighbor, len+1, curr_dir))
                        return true;
                    }
            }//end third for
        }//end second for
}//end first for
        chip.unflag();//chip has no neighbors...how sad.
        return false;
    }

      /*if no neighbor (because you ran off board), continue;
>>>>>>> fc425eb3256acc60c1ee48933ff493abae415105
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
      public void dumpBoard(){
        for( int j = 0; j < 8; j++){      	         	      	      
          for(int i = 0; i < 8; i++){
            System.out.print("| ");
            if(board[i][j].color == Chip.BLACK)
              System.out.print( "B | ");
            else if(board[i][j].color == Chip.WHITE)
              System.out.print( "W | ");
            else if(board[i][j].color == Chip.EMPTY)
              System.out.print( "_ | ");
            else
              System.out.print( "* | ");
      	  }
      	  System.out.println();
        }      	            	            	            	            	      
      }

}
