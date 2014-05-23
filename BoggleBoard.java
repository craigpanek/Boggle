public class BoggleBoard {
	private int width;
	private int height;
	private Cube[][] arrayOfCubes;
	
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
	
	public String getLetter(int row, int col) {
		return arrayOfCubes[row][col].getLetter();
	}
	
	public void randomize() {
		// Randomly assign letter to cube
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				arrayOfCubes[row][col].rollCube();
			}
		}
		
		// Shuffle cubes
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int randomCol = (int)(Math.random() * width);
				int randomRow = (int)(Math.random() * height);
				assert(randomCol >= 0 && randomCol <= 4);
				assert(randomRow >= 0 && randomRow <= 4);
				Cube tmpCube = arrayOfCubes[randomRow][randomCol];
				arrayOfCubes[randomRow][randomCol] = arrayOfCubes[row][col];
				arrayOfCubes[row][col] = tmpCube;
			}
		}
	}
}
