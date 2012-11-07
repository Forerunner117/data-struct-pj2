/*java*/
package machine; 
/*
	This is a container for direction values used in the explore method. 
	Since this is not an ADT, all fields are public.

*/
import player.*;

public enum Direction{
        N, NE, E, SE, S ,SW, W, NW;
	/*public final static int N = 1;
	public final static int NE = 2;
	public final static int E = 3;
	public final static int SE = 4;
	public final static int S = 5;
	public final static int SW = 6;
	public final static int W = 7;
	public final static int NW = 8;*/
// public Direction()
public static Direction getCurrDir(int i, int j){
	if( i == -1 && j == -1){
                        return SW;
                }	
                if( i == -1 && j == 0){
                        return W;
                }
                if( i == -1 && j == 1){
                        return NW;
                }
                if ( i == 0 && j == 1){
                        return N;
                }
                if (i == 1 && j == 1){
                        return NE;
                }
                if( i == 1 && j == 0){
                        return E;
                }
                if( i == 1 && j == -1){
                        return SE;
                }
                if ( i == 0 && j == -1){
                        return S;
                }
	return N;//some number to satisfy the compiler
	}
}