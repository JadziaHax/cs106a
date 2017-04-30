/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {

	public void run() {
		while (leftIsBlocked()) {
			findDoor();
		}
		
		goOutside();
		
		if (beepersPresent()) {
			pickBeeper();
		} else {
			goInside();
			move();
			run();
		}
	}
	
	private void findDoor() {
		if (frontIsClear()) {
			checkCurrentWall();			
		}
		
		if (frontIsBlocked()) {
			turnRight();
		}
	}
	
	private void checkCurrentWall() {
		if (leftIsBlocked()) {
			move(); 
		}
	}
	
	private void goOutside() {
		turnLeft();
		move();
	}
	
	private void goInside() {
		turnAround();
		move();
		turnLeft();
	}
}
