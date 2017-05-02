import stanford.karel.SuperKarel;

public class DefendDemocracyKarel extends SuperKarel {
	/*
	 * Preconditions: Karel is standing at the opening of the first chad facing
	 * east.
	 * 
	 * Karel moves into the chad and checks to see if it has been punched at all
	 * by checking to see if a beeper is in the central position of the chad.
	 * -If a beeper is present this means that the chad was not punched and
	 *  Karel will move again to exit the chad.
	 * -Otherwise (a beeper is not present) Karel will execute a method for
	 *  clearing the chad, and then move out of the chad.
	 *  
	 * Postconditions: Karel is standing on the other side of the chad he started
	 * infront of, still facing east.
	 */
	public void run() {
		while(frontIsClear()) {
			move();
			if (beepersPresent()) {
				move();
			} else {
				clearChad();
				move();
			}
		}
	}
	
	/*
	 * Preconditions: Karel is standing in the central opening of a chad
	 * facing east
	 * 
	 * Karel will execute a method that makes face north, move once into
	 * the upper portion of the chad, execute a method that for clearing beepers
	 * remaining in that area of the chad, execute a method to face south,
	 * move twice to enter the lower portion of the chad, execute the beeper
	 * clearing method again, face north and move once to position himself in
	 * the center of the chad with the opening, and face east.
	 * 
	 * Postconditions: Karel is standing in the same location he started in
	 * and facing the same direction.
	 */
	private void clearChad() {
		faceNorth();
		move();
		removeChadRemnants();
		faceSouth();
		move();
		move();
		removeChadRemnants();
		faceNorth();
		move();
		faceEast();
	}
	
	/*
	 * Preconditions: Karel exists on a world grid.
	 * 
	 * If Karel is not already facing north, he will rotate until he is.
	 * 
	 * Postconditions: Karel is standing in the same place before the method
	 * was executed, facing north
	 */
	private void faceNorth() {
		while (notFacingNorth()) {
			turnLeft();
		}
	}
	
	/*
	 * Preconditions: Karel exists on a world grid.
	 * 
	 * If Karel is not already facing south, he will rotate until he is.
	 * 
	 * Postconditions: Karel is standing in the same place before the method
	 * was executed, facing south
	 */
	private void faceSouth() {
		while (notFacingSouth()) {
			turnLeft();
		}
	}
	
	/*
	 * Preconditions: Karel exists on a world grid.
	 * 
	 * If Karel is not already facing east, he will rotate until he is.
	 * 
	 * Postconditions: Karel is standing in the same place before the method
	 * was executed, facing east
	 */
	private void faceEast() {
		while (notFacingEast()) {
			turnLeft();
		}
	}
	/*
	 * Preconditions: Karel exists on a world grid.
	 * 
	 * If Karel is standing on a beeper or beepers, he will pick them up until
	 * the position is clear.
	 * 
	 * Postconditions: Karel is in the same position and facing that he started
	 * in. There are no beepers under his feet.
	 */
	private void removeChadRemnants() {
		while(beepersPresent()) {
			pickBeeper();
		}
	}
	
}