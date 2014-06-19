/**
 * This class represents an individual cube used in bogglegame.
 * A cube is able to generate a letter for itself randomly.
 * @author Craig Panek
 * Date: 6-19-2014
 */

public class Cube {
	private static String[] bigBoggleCubes = {
		"aaafrs", "aaeeee", "aafirs", "adennn", "aeeeem",
		"aeegmu", "aegmnn", "afirsy", "bjkqxz", "ccnstw",
		"ceiilt", "ceilpt", "ceipst", "ddlnor", "ddhnot",
		"dhhlor", "dhlnor", "eiiitt", "emottt", "ensssu",
		"fiprsy", "gorrvw", "hiprry", "nootuw", "ooottu"		
	};
	private static int numBigBoggleCubes = bigBoggleCubes.length;
	private static int numCubesCreated = 0;
	private String letter;
	private int cubeID;
	
	/**
	 * Construct a cube, assigning it an ID number.
	 */
	public Cube() {
		cubeID = numCubesCreated % numBigBoggleCubes;
		numCubesCreated++;
	}
	
	/**
	 * 'Roll' the cube, which will generate a random letter for itself.
	 * @return
	 */
	public String rollCube() {
		int index = (int) (Math.random() * bigBoggleCubes[cubeID].length());
		letter = bigBoggleCubes[cubeID].substring(index, index + 1);
		if(letter.equals("q"))
			letter = "qu";
		return letter;
	}
	
	/**
	 * @return the letter for this cube
	 */
	public String getLetter() {
		return letter;
	}
	
	/**
	 * @return the ID for this cube
	 */
	public int getID() {
		return cubeID;
	}
}
