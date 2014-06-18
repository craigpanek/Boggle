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
	
	public Cube() {
		cubeID = numCubesCreated % numBigBoggleCubes;
		numCubesCreated++;
	}
	
	public String rollCube() {
		int index = (int) (Math.random() * bigBoggleCubes[cubeID].length());
		letter = bigBoggleCubes[cubeID].substring(index, index + 1);
		if(letter.equals("q"))
			letter = "qu";
		return letter;
	}
	
	public String getLetter() {
		return letter;
	}
	
	public int getID() {
		return cubeID;
	}
}
