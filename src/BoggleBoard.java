/**
 * This class represents a boggleboard which is a rectangular grid
 * of cubes.
 * @author Craig Panek
 * @author Peter Nguyen
 * Date: 6-19-2014
 */

public class BoggleBoard {
	private int width;
	private int height;
	private Cube[][] arrayOfCubes;
	
	/**
	 * Initialize boggleboard to a given size based on the
	 * arguments passed into the constructor.
	 * @param width
	 * @param height
	 */
	BoggleBoard(int width, int height) {
		this.width = width;
		this.height = height;
		arrayOfCubes = new Cube[width][height];
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				arrayOfCubes[row][col] = new Cube();
			}
		}

		randomize();
	}
	
	/**
	 * @param row
	 * @param col
	 * @return the letter associated with the selected row and col
	 */
	public String getLetter(int row, int col) {
		return arrayOfCubes[row][col].getLetter();
	}
	
	/**
	 * Assign a random letter to each cube, and then shuffle all cubes
	 */
	public void randomize() {
		// Randomly assign a letter to each cube
		for (int row = 0; row < height; row++)
			for (int col = 0; col < width; col++)
				arrayOfCubes[row][col].rollCube();
		
		// Shuffle cubes
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int randomCol = (int)(Math.random() * width);
				int randomRow = (int)(Math.random() * height);
				Cube tmpCube = arrayOfCubes[randomRow][randomCol];
				arrayOfCubes[randomRow][randomCol] = arrayOfCubes[row][col];
				arrayOfCubes[row][col] = tmpCube;
			}
		}
	}
	/**
	 * @return the width of the board
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * @return the height of the board
	 */
	public int getHeight() {
		return height;
	}
}
