/* MachinePlayerTestDrive.java */

package player;

import machine.*;
import java.util.Random;

public class MachinePlayerTestDrive{
	public static void main(String[] args){
		MachinePlayer test = new MachinePlayer(Chip.WHITE, 4);
		System.out.println("Should be 1: " + test.getMyColor());
		System.out.println("Should be 4: " + test.getSearchDepth());
	}
}
