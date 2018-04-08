package ie.gmit.sw.ai;


// Class to track the position in table
public class Position {

	private int one;
	private int two;
	
	// Creating constructor and passing variables
	private Position(int one, int two) {
		this.one = one;
		this.two = two;		
	}
	
	// Get the position method
	public static Position getPosition(char target, char[][] cipTable) {
		
		// For i and j are less than 5
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				// If the target is equal to this position return new position
				if(target == cipTable[i][j]) {
					return new Position(i, j);
				}
			}
		}
		return null;
	}

	public int getOne() {
		return this.one;
	}

	public int getTwo() {
		return this.two;
	}
}
