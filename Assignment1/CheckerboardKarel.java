/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run () {
		if (frontIsBlocked()) {
			if (facingNorth()) {
				if (leftIsBlocked()) {
					return;
				} else {
					distributeBeepers();
				}
			} else {
				distributeBeepers();
			}
		} else {
			distributeBeepers();
		}
	}
	
	private void distributeBeepers() {
		putBeeperIfNone();
		moveAppropriately();
		moveAppropriately();
		run();
	}
	
	private void putBeeperIfNone() {
		if (noBeepersPresent()) {
			putBeeper();
		}
	}
	
	private void faceNorth() {
		while (notFacingNorth()) {
			turnLeft();
		}
	}
	
	private void conditionalRotate() {
		if (rightIsBlocked()) {
			turnLeft();
		}
		
		if (leftIsBlocked()) {
			turnRight();
		}
	}
	
	private void conditionalAlignment() {
		if (facingNorth()) {
			conditionalRotate();
		}
	}
	
	private void moveIfClear() {
		if (frontIsClear()) {
			move();
		}
	}
	
	private void ifBlockedFaceNorth() {
		if (frontIsBlocked()) {
			faceNorthIfNot();
		} 
	}
	
	private void faceNorthIfNot() {
		if (facingNorth()) {
			return;
		} else {
			faceNorth();
		}
	}
	
	private void moveAppropriately() {
		ifBlockedFaceNorth();
		moveIfClear();
		if (frontIsBlocked()) {
			if (facingNorth()) {
				if (leftIsBlocked()) {
					return;
				} else {
					conditionalAlignment();
				}
			} else {
				conditionalAlignment();
			}
		} else {
			conditionalAlignment();
		}
	}
 }
