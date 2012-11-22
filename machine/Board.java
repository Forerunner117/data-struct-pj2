/* Board.java */

package machine;

import player.*;

public class Board{

  private Chip[][] board = new Chip[8][8]; 
  private Move whiteLastMove;
  private Move blackLastMove;   
  private Chip neighbor;
  private int whitePieces;
  private int blackPieces;
  private int connections = 0;
  private int maxExploreLength = 1;


  public Board(){
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        board[i][j] = new Chip(i, j, Chip.EMPTY);
      }
    }

    board[0][0] = new Chip(0, 0, Chip.GREY);
    board[0][7] = new Chip(0, 7, Chip.GREY);
    board[7][0] = new Chip(7 ,0, Chip.GREY);
    board[7][7] = new Chip(7, 7, Chip.GREY);
    neighbor = board[0][0];//arbitrary to satisfy compiler.
  }
  
  public Board copyBoard(){
    Chip tempChip;
    Board newBoard = new Board();
      
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){              
        tempChip = returnChip(i,j);
        newBoard.insertChip(i,j,tempChip);      
      }            
    }

    newBoard.whitePieces = whitePieces;
    newBoard.blackPieces = blackPieces;
    newBoard.connections = connections;
    newBoard.maxExploreLength = maxExploreLength;
      
    return newBoard;         	  
  }

  private void insertChip(int x, int y, Chip chip){
     board[x][y] = chip;  
  }
  
  public Chip returnChip(int x, int y){
    return board[x][y];
  }

  public int chipColor(int x, int y){
    Chip chip = returnChip(x,y);
    int color =  chip.returnColor();
    return color;
  }
    
  public void testNetwork(int color){
    if(hasNetwork(color))
      System.out.println("Found Network");
    else
      System.out.println("Not found Network");
  }

  // addChip takes a move and a color and reflects that move on 'this' board, 
  // and also increments the piece count. STEP moves have their original 
  // locations removes.
  public void addChip(Move m, int color){
    if(m.moveKind == m.ADD){
      board[m.x1][m.y1] = new Chip(m.x1, m.y1, color); 
      this.setPieces(color);     
    }
    else if(m.moveKind == m.STEP){
      board[m.x1][m.y1] = new Chip(m.x1, m.y1, color); 
      removeChip(m.x2, m.y2);   
    }  
    else
      return;  
  }

  public void removeChip(int x, int y){ 
      board[x][y].color = Chip.EMPTY;
  }

  // undoMove sets the slot from move m to EMPTY and decreases the piece count 
  // for ADD moves. Reverts a STEP move.
  public void undoMove(Move m, int color){
  	  if(m.moveKind == m.ADD){
  	  	  board[m.x1][m.y1] = new Chip(m.x1, m.y1, Chip.EMPTY);    	  
          this.decPieces(color);  	 
  	  }
  	  else if(m.moveKind == m.STEP){
  	  	  board[m.x1][m.y1] = new Chip(m.x1, m.y1, Chip.EMPTY); 
  	  	  board[m.x2][m.y2] = new Chip(m.x2, m.y2, color);   
  	  }  
      else
        return;   
  }
  
  //setPieces increments the count of white and black pieces every time addChip 
  //is called.
  public void setPieces(int col){
    if(col == Chip.BLACK)
      ++blackPieces;
    if(col == Chip.WHITE)
      ++whitePieces; 
  }

  //decPieces decrements the count of white or black pieces every time undoMove
  //is called.
  public void decPieces(int col){
    if(col == Chip.BLACK)
      --blackPieces;
    if(col == Chip.WHITE)
      --whitePieces; 
  }

  //getPieces returns the count of white or black pieces. This method is called
  //when we want to determine if we should make an ADD or STEP move.
  public int getPieces(int col){
    if(col == Chip.BLACK)
      return blackPieces;
    else if(col == Chip.WHITE)
      return whitePieces;
    else
      return 0;
  }

  // public method getCurrChips() finds all of the current chips that have been
  // placed on the board by the given color, col. Once found, they are stored in
  // the 1D array currChips and then returned. Implemented so that we may make 
  // smart STEP moves by analyzing all legal STEP moves for all pieces in play.
  public Chip[] getCurrChips(int col){
    Chip[] currChips = new Chip[500];
    int counter = 0;

    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        if(board[i][j].returnColor() == col){
          currChips[counter] = board[i][j];
          ++counter;
        }
      }
    }

    return currChips;
  }
  

  /*public int numPieces(int col){
    int pieces = 0;
    for (int i=0; i<7; i++) {
      for (int j=0; j<7; j++) {
        if(board[i][j].returnColor() == col)
          pieces++;
      }
    }
    return pieces;
  }*/
 
  public void unflagAllChipsOfColor(int col){
    for (int i=0; i<8; i++) {
      for (int j=0; j<8; j++) {
        if(board[i][j].returnColor() == col)
          board[i][j].unflag();
      }
    }
  }

  public boolean hasNetwork(int col){
    maxExploreLength = 1;
    boolean exp = false;
    if(col == Chip.WHITE){
      for (int i=1; i<7 && !exp; i++) {//Check the first goal on the left.
        if(board[0][i].returnColor() == col && !endGoalEmpty(col)){
          unflagAllChipsOfColor(col);
          exp = explore(col, board[0][i].getX(), board[0][i].getY(), 1, Direction.X);//Direction.x is to avoid direction bugz
        }
      }
    }
    if(col == Chip.BLACK){//Check the goal on the top.
      for (int i=1; i<7 && !exp; i++) {
        // System.out.println("color at board (" + i + ", 0) is " + board[i][0].returnColor() + " looking for color: " + Chip.BLACK );
        if(board[i][0].returnColor() == col && !endGoalEmpty(col)){       
          unflagAllChipsOfColor(col);
          exp = explore(col, board[i][0].getX(), board[i][0].getY(), 1, Direction.X);//Direction.x is to avoid direction bugz
        }
      }
    }

    return exp;
  }
    
// replace chip with board[i][j], when you unflag, you're unflagging a local variable which is getting lost.
  //just refer to the instance variable board[][]. 

  boolean explore(int col, int x, int y, int len, Direction dir){
       // System.out.println("\n\n\n####ENTERING EXPLORE###");
       board[x][y].flag();
      
        
        
        Direction curr_dir = Direction.N; // N is arbirary. There is not current direction yet.
        
        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
               
               // Current Point
                if( (i == 0) && (j==0)) 
                  continue;

                //Running off board.
                if( ((x + i) < 0)  ||  ((y + j) < 0) || ((x + i) > 7) || ((y + j) > 7) )
                    continue;

                //Eliminating Corners.
                if( (x + i == 0 && y + j == 0)  ||  (x + i == 7 && y + j == 0)   ||
                    (x + i == 7 && y + j == 7)  ||  (x + i == 0 && y + j == 7) )
                    continue;

                //Loop to explore board
                for(int k=1; k<8; k++){

                    //Ran off the board
                    if(((x + i*k) < 0)  ||  ((y + j*k) < 0) || ((x + i*k) > 7) || ((y + j*k) > 7))
                        break;
                  
                    neighbor = board[x + i*k][y + j*k];
                    curr_dir = Direction.getCurrDir(i, j);

                     if(neighbor.returnColor() == Chip.EMPTY )
                      continue;
                    else{
                      break;
                      }

                    }//end third for
                
                
                if( neighbor.returnColor() != col ){//wrong color
                   
                    continue;
                }
                

                if(curr_dir == dir){ //same direction
                   // System.out.println("The directions are the same: curr_dir is " + curr_dir + " dir is " + dir);
                    continue;
                 }


                if(board[neighbor.getX()][neighbor.getY()].isFlagged()){//already visited
                    //System.out.println("The neighbor already visited at (" + neighbor.getX() +", " + neighbor.getY() + ") ");
                    continue;
                  }
                if( (col == Chip.WHITE && neighbor.getX() == 0) || //NOTE: I'm assuming white start_goal is left column
                    (col == Chip.BLACK && neighbor.getY() == 0)) {  //NOTE: I'm assuming black start_goal is top row

                    continue; 
                  }
                if( (col == Chip.BLACK && neighbor.getY() == 7) || (col == Chip.WHITE && neighbor.getX() == 7)){
                  // System.out.println("Found neighbor in end goal at  (" + neighbor.getX() + ", "  + neighbor.getY() + ") and length is " + len);
                    if (len >= 5) return true;
                
                }else{
                  // System.out.println("Found neighbor at (" + neighbor.getX() + ", " + neighbor.getY() + ") and Recurssing" + "\nlen is " + len);
                    //setMaxExpLength(len+1);
                    if(explore(col, neighbor.getX(), neighbor.getY(), len+1, curr_dir))
                        return true;
                    }
            
        }//end second for
}//end first for
        board[x][y].unflag();//chip has no neighbors...how sad.
        // System.out.println("Returning false. At (" + board[x][y].getX() + ", " + board[x][y].getY() + ") \n");
        // System.out.println("Unflagged a chip at (" + board[x][y].getX() + ", " + board[x][y].getY() + ") ");
        return false;
    }
    
  private void setMaxExpLength(int len){
    if (len > maxExploreLength) {
      maxExploreLength = len;
    }
  }

  public int getMaxExploreLength(){
    return maxExploreLength;
  }

  boolean endGoalEmpty(int col){
    if (col == Chip.WHITE) {
      for (int i = 1; i<7; i++) {
        if(board[7][i].returnColor() == Chip.WHITE)
          return false;
      }
    }
    if (col == Chip.BLACK) {
      for (int i = 1; i<7; i++) {
        if(board[i][7].returnColor() == Chip.BLACK)
          return false;
      }
    }
    return true;
  }
  
  boolean startGoalEmpty(int col){
    if (col == Chip.WHITE) {
      for (int i = 1; i<7; i++) {
        if(board[0][i].returnColor() == Chip.WHITE)
          return false;
      }
    }
    if (col == Chip.BLACK) {
      for (int i = 1; i<7; i++) {
        if(board[i][0].returnColor() == Chip.BLACK)
          return false;
      }
    }
    return true;
  }

  int endGoalCount(int col){
    int count = 0;
    if (col == Chip.WHITE) {
      for (int i = 1; i<7; i++) {
        if(board[7][i].returnColor() == Chip.WHITE)
          count++;
      }
    }
    if (col == Chip.BLACK) {
      for (int i = 1; i<7; i++) {
        if(board[i][7].returnColor() == Chip.BLACK)
          count++;
      }
    }
    return count;
  }
  
  int startGoalCount(int col){
    int count = 0;
    if (col == Chip.WHITE) {
      for (int i = 1; i<7; i++) {
        if(board[0][i].returnColor() == Chip.WHITE)
          count++;;
      }
    }
    if (col == Chip.BLACK) {
      for (int i = 1; i<7; i++) {
        if(board[i][0].returnColor() == Chip.BLACK)
          count++;
      }
    }
    return count;
  }
  
  public void setLastMove(Move m, int color){
      if(color == Chip.BLACK)
        blackLastMove = new Move(m.x1, m.y1);
      else
        whiteLastMove = new Move(m.x1, m.y1);
    }

    public Move getLastMove(int color){
      if(color == Chip.BLACK)
        return blackLastMove;
      else
        return whiteLastMove;
    }
    public void dumpBoard(){
      for( int j = 0; j < 8; j++){                                
        for(int i = 0; i < 8; i++){
          System.out.print("| " + board[i][j].toString() + " | ");

        
        }
        System.out.println();
      }                                                                     
    }

  private void unTouchAllChipsOfColor(int col){
    for (int i=0; i<8; i++) {
        for (int j=0; j<8; j++) {
          if(board[i][j].returnColor() == col)
            board[i][j].untouch();
        }
      }
  }

  // This is potentially a huge performance hit. Should use sparingly.
  public int howManyConnections(int col){
    //exp is the result of the exploration. Used to check more than one chip in start goal.
    connections = 0;
    if(col == Chip.WHITE){
      unTouchAllChipsOfColor(col);
      for (int i=0; i<8; i++) {//Check the first goal on the left.
        for(int j=1; j<7; j++){
        if(board[i][j].returnColor() == col){
          connectionExplore(col, board[i][j].getX(), board[i][j].getY());
          }//correct color if
        }//inner for
      }//outer for
    }// White if
    if(col == Chip.BLACK){//Check the goal on the top.
      unTouchAllChipsOfColor(col);
      for (int j=0; j<8; j++) {
        for(int i=1; i<7; i++){
        if(board[i][j].returnColor() == col){
          connectionExplore(col, board[i][j].getX(), board[i][j].getY());
          }//correct color if
        }//inner for
      }//outer for
    }// White if
    return connections;
  }
    

    private void connectionExplore(int col, int x, int y){
       // System.out.println("\n\n\n####ENTERING connectionExplore###");
       
       board[x][y].touch();

       // System.out.println("board at (" + x + ", " + y + ") isTouched = " + board[x][y].isTouched());
        
      // System.out.println("Looking from (" + x + ", " + y + ") ");
        
        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
               
               // Current Point
                if( (i == 0) && (j==0))
                  continue;

                //Running off board.
                if( ((x + i) < 0) || ((y + j) < 0) || ((x + i) > 7) || ((y + j) > 7) )
                    continue;

                //Eliminating Corners.
                if( (x + i == 0 && y + j == 0) || (x + i == 7 && y + j == 0) ||
                    (x + i == 7 && y + j == 7) || (x + i == 0 && y + j == 7) )
                    continue;

                //Loop to explore board
                for(int k=1; k<8; k++){

                    //Ran off the board
                    if(((x + i*k) < 0) || ((y + j*k) < 0) || ((x + i*k) > 7) || ((y + j*k) > 7))
                        break;
                  
                    neighbor = board[x + i*k][y + j*k];
                    

                     if(neighbor.returnColor() == Chip.EMPTY )
                      continue;
                    else{
                      break;
                      }

                    }//end third for
                
                //wrong color
                if( neighbor.returnColor() != col ){
                    continue;
                }
                          

                 //already visited
                if(board[neighbor.getX()][neighbor.getY()].isTouched()){
                    // System.out.println("The neighbor already touhed at (" + neighbor.getX() +", " + neighbor.getY() + ") ");
                    continue;
                  }

                  // Neighbor is in the start goal
                if( (col == Chip.WHITE && neighbor.getX() == 0 ) || //NOTE: I don't consider a neighbor as a
                    (col == Chip.BLACK && neighbor.getY() == 0)) { //NOTE: I'm assuming black start_goal is top row
                    // System.out.println("Skipping the start goal neighbor at (" + neighbor.getX() +", " + neighbor.getY() + ") ");
                    continue;
                  }
                  // Ignoring end goal neighbors from end goal chips
                if ((col == Chip.WHITE && x == 7 && neighbor.getX() == 7 ) || 
                    (col == Chip.BLACK && y == 7 && neighbor.getY() == 7)) { 
                  // System.out.println("Skipping the end goal neighbor at (" + neighbor.getX() +", " + neighbor.getY() + 
                    // ") from chip at  (" + x + ", " + y + ")");
                  continue;
                }
                  else{
                    // System.out.println("found good neighbor at (" + neighbor.getX() + ", " + neighbor.getY() + ") ");
                  connections++;
                  // System.out.println("From connectionExplore, I count " + connections + " connections.");
                  }
            
          }//end second for
    }//end first for
        
}


private void pause(int milli){
  try{
    Thread.sleep(milli);
  }
  catch (InterruptedException e) {}
}
//------------------------PRINT LINES------------------------------------------

                   // if(neighbor.getX() == 2 && neighbor.getY() == 7)
                      // System.out.println("##Found end goal neighbor## length is " + len);
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
                //if(neighbor.getX() == 3 && neighbor.getY() == 4)
                  // System.out.println("\nFound a black chip in the k loop\ncurr_dir is " + curr_dir + "\ndir is " + dir);

                /*if(neighbor == Color.WHITE)
                    continue;*/
                /*if( i == 1 && j == 0 && k == 2){
                    System.out.println("I got to the logic");
                    System.out.println("neighbor's color is " + neighbor.returnColor() + " and current color is " + col);
                }*/
//  //System.out.println("i is " + i + " j is " + j);
                    //System.out.println("\nk is " + k);
                    //       // System.out.println("Flag status of explore chip is " + chip.isFlagged() + " which should be false");

        // System.out.println("X position of explore chip is " + x);

  // System.out.println("Y position of explore Chip is " + y);
  // if(x == 2 && y == 7)
          // System.out.println("\nEnd Goal neighbor before loops and logic: length is " + len);
        // System.out.println("neighbor is " + neighbor + " which should be null");
// System.out.println("\nneighbor's is at (" + neighbor.getX() + ", " +neighbor.getY()
                         // + ") and the his color is " + neighbor.returnColor() + " and current color is " + col
   // System.out.println("\nNeighbor color is, " + neighbor.returnColor() + " and col is " + col + "...The If thinks these are different");
//                    // System.out.println("Found neighbor in start goal" + "neighbor.getX() is " + neighbor.getX() 
                      // + " and neighbor.getY() is " + neighbor.getY());
                // if(len == 4){
                //   System.out.println("\nlen is 4 right before isFlagged check @ ( "+ neighbor.getX() + ", " + neighbor.getY() + 
                //     ")\nneighbor flagged status is " + neighbor.isFlagged());

                // }
   /* private void flagChip(Chip c){
    int x = c.getX();
    int y = c.getY();
    board[x][y].flag();
  }
  private void unflagChip(Chip c){
    int x = c.getX();
    int y = c.getY();
    board[x][y].unflag();
  }*/
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
      }*/
      //}
}
