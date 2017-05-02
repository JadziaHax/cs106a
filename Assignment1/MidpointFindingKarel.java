/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {
	
	/*
	 * Preconditions: Karel is facing east, standing on 1,1
	 * 
	 * Sets the initial endpoint beepers on the intersections that
	 * immediately come after the intersections next to the east and
	 * and west walls.
	 * 
	 * Postconditions: Karel is facing west, standing on the center 
	 * intersection of street 1
	 */
	public void run() {
		findEndpoints();
		shrinkToCenter();
	}
	
	/*
	 * Preconditions: Karel is standing at 1,1 and is facing east
	 * 
	 * Karel moves to 1,2, places the western endpoint beeper,
	 * then moves to 1,max, turns around, moves once and places the
	 * eastern endpoint beeper.
	 * 
	 * Postconditions: Karel is standing at 1,(max - 1) and is facing west
	 */
	private void findEndpoints() {
		moveIfClear();
		putBeeperIfNone();
		
		while (frontIsClear()) {
			move();
		}
		
		turnAround();
		moveIfClear();
		putBeeperIfNone();
	}
	
	/*
	 * Preconditions: Karel is standing on either the east or west endpoint
	 * facing the opposite endpoint
	 * 
	 * Karel moves twice and checks for a beeper.
	 * 
	 * -If Karel finds a beeper here, we know that the next beeper placement
	 *  will be the last one at the center and this represents our base case, 
	 *  so we call a method that is designed to finish out execution of the 
	 *  task this extension covers.
	 *  
	 * -Otherwise, If Karel doesn't find a beeper here we know that we are
	 *  proceeding as usual. We call a method designed to send Karel to the
	 *  opposite end point proceeding under the assumption that we will not be
	 *  done afterwards.
	 *  
	 *  Post-conditions: Karel is standing at an intersection two beyond where
	 *  he started in the direct he was facing.   
	 */
	private void shrinkToCenter() {
		moveIfClear();
		moveIfClear();
		
		if (beepersPresent()) {
			endBeeperPlacement();
		} else {
			normalBeeperPlacement();	
			shrinkToCenter();
		}

	}
	
	/*
	 * Preconditions: Karel is facing either east or west, opposite of the most
	 * recently updated endpoint, and two intersections from it.
	 * 
	 * Karel moves until he finds a beeper. This is the stale endpoint that
	 * needs to be updated. The we call a method that clears the stale endpoint
	 * and then put a new one down.
	 * 
	 * Postconditions: Karel is standing on the endpoint he just updated facing
	 * the opposite endpoint (facing opposite of the direction he was facing in
	 * preconditions.
	 */
	private void normalBeeperPlacement() {
		while (noBeepersPresent()) {
			move();
		}
		clearOldEndpoint();
		putBeeperIfNone();
	}
	
	/*
	 * Preconditions: Karel is facing either east or west, opposite of the most
	 * recently updated endpoint, ontop of the stale endpoint. The endpoints
	 * only have one intersection between them.
	 * 
	 * Karel clears the endpoint he is standing on, then clears the other and
	 * drops a beeper on the intersection that lied between them. This is the
	 * center intersection of the street.
	 * 
	 * Postconditions: Karel is facing west, standing on the center intersection
	 * with a beeper underneath his feet.
	 */
	private void endBeeperPlacement() {
		clearOldEndpoint();
		moveIfClear();
		clearOldEndpoint();
		putBeeperIfNone();
	}
	
	/*
	 * Preconditions: Karel is standing on a stale endpoint, facing away from
	 * the opposite endpoint.
	 * 
	 * Karel picks up the stale endpoint, turns around (to face the opposite
	 * endpoint) and moves once if he's not blocked.
	 * 
	 * Postconditions, Karel is facing the end point opposite of the one he
	 * just picked up and has moved one space closer in most cases, though
	 * exceptions for this exist in very narrow maps.
	 */
	private void clearOldEndpoint() {
		pickBeeperIfPresent();
		turnAround();
		moveIfClear();
	}
	
	/*
	 * Preconditions: Karel exists and is on a world grid.
	 * 
	 * Karel moves once if he is not blocked
	 * 
	 * Postconditions: Karel is standing in the direction he was facing when this
	 * method was called. If there was room he is standing one intersection over
	 * in that direction.
	 */
	private void moveIfClear() {
		if (frontIsClear()) {
			move();
		}
	}
	
	/*
	 * Preconditions: Karel exists and is on a world grid.
	 * 
	 * If Karel is standing on a beeper he picks it up
	 * 
	 * Postconditions: Karel has picked up a beeper if it is underneath his
	 * feet. Note that there could be more than one beeper under his feet when
	 * this method is called, and in that case there would still be a beeper
	 * or beepers underneath him, but this is never expected as part of the
	 * problem this extension is designed to solve. Facing and
	 * location have not changed.
	 */
	private void pickBeeperIfPresent() {
		if (beepersPresent()) {
			pickBeeper();
		}
	}
	
	/*
	 * Preconditions: Karel exists and is on a world grid.
	 * 
	 * If Karel is not standing on a beeper he puts one down.
	 * 
	 * Postconditions: Karel is standing on a beeper or beepers. Facing and
	 * location have not changed.
	 */
	private void putBeeperIfNone() {
		if (noBeepersPresent()) {
			putBeeper();
		}
	}
}
