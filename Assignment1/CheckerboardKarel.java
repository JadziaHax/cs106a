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

	public void run() {
		fillRow();
		if (frontIsBlocked()) {
			faceNorth();
			if (frontIsClear()) {
				moveUpRow();
				run();
			}
		}
	}
	
	private void fillRow() {
		faceSouth();
		if (frontIsClear()) {
			move();
			if (beepersPresent()) {
				moveUpRow();
				move();
				placeBeepers();
			} else {
				moveUpRow();
				placeBeepers();
			}
		} else {
			faceNorth();
			conditionalRotate();
			placeBeepers();
		}
	}
	
	private void faceSouth() {
		while (notFacingSouth()) {
			turnLeft();
		}
	}
	
	private void placeBeepers() {
		while (frontIsClear()) {
			putBeeperIfNone();
			moveIfClear();
			moveIfClear();
		}
		turnAround();
		move();
		if (beepersPresent()) {
			turnAround();
			move();
		} else {
			turnAround();
			move();
			putBeeper();
		}
	}
	
	private void moveUpRow() {
		faceNorth();
		moveIfClear();
		conditionalRotate();
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
	
	private void moveIfClear() {
		if (frontIsClear()) {
			move();
		}
	}

 }
