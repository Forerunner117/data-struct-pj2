/*Direction.java*/
package machine; 
/*
	This is a container for direction values used in the explore method. 
	Since this is not an ADT, all fields are public.

*/
import player.*;

public enum Direction{
        N, NE, E, SE, S ,SW, W, NW, X;
	
        /**
         * getCurrDir() returns an Direction enum based on what 
         * i and j value are inputted. Used in explore(). 
         * @param i is the i value from a for loop.
         * @param j is the j value from a for loop.
         * @return an enum representing a cardinal Direction.
         **/
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
        	return X; //some enum to satisfy the compiler
        	}
}