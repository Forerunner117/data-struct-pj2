/* AITestDrive.java */

package machine; 

import player.*;

public class AITestDrive{
	public static void main(String[] args){
		MachinePlayer mp = new MachinePlayer(Chip.WHITE);

		//WHITE moves
		Move wm1 = new Move(0,4);
		Move wm2 = new Move(1,3);
		Move wm3 = new Move(3,3);
		Move wm4 = new Move(4,4);
		Move wm5 = new Move(6,4);

		//BLACK moves
		Move bm1 = new Move(2,2);
		Move bm2 = new Move(2,4);
		Move bm3 = new Move(3,7);
		Move bm4 = new Move(5,3);
		Move bm5 = new Move(6,5);

		mp.forceMove(wm1);
		mp.forceMove(wm2);
		mp.forceMove(wm3);
		mp.forceMove(wm4);
		mp.forceMove(wm5);

	}
}