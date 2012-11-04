/*Direction.java*/
package machine; 
/*
	This is a container for direction values used in the explore method. 
	Since this is not an ADT, all fields are public.

*/
import player.*;

public class Direction{
	public final static int N = 1;
	public final static int NE = 2;
	public final static int E = 3;
	public final static int SE = 4;
	public final static int S = 5;
	public final static int SW = 6;
	public final static int W = 7;
	public final static int NW = 8;

public static int getCurrDir(int i, int j){
	if( i == -1 && j == -1){
                        return Direction.SW;
                }	
                if( i == -1 && j == 0){
                        return Direction.W;
                }
                if( i == -1 && j == 1){
                        return Direction.NW;
                }
                if ( i == 0 && j == 1){
                        return Direction.N;
                }
                if (i == 1 && j == 1){
                        return Direction.NE;
                }
                if( i == 1 && j == 0){
                        return Direction.E;
                }
                if( i == 1 && j == -1){
                        return Direction.SE;
                }
                if ( i == 0 && j == -1){
                        return Direction.S;
                }
	return -1;//some number to satisfy the compiler
	}
}