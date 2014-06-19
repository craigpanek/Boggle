/**
 * This class represents a bogglegame
 * @author Craig Panek
 * Date: 6-19-2014
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BoggleGame {

	private BoggleBoard boggleBoard;
	private Dictionary lexicon;
	private int currentRow;
	private int currentCol;
	private OrderedList wordList = new OrderedList();
	private ArrayList<MyPair> selections = new ArrayList<MyPair>();
	private boolean[][] cubeSelected; // Keeps track of which cubes have been selected
	private String word;

	public BoggleGame(BoggleBoard boggleBoard) throws FileNotFoundException {
		this.boggleBoard = boggleBoard;
		lexicon = new Dictionary("Enhanced North American Benchmark Lexicon.txt");
		int height = boggleBoard.getHeight();
		int width = boggleBoard.getWidth();
		cubeSelected = new boolean[height][width];
		for(int i=0; i < height; i++)
			for(int j=0; j < width; j++)
				cubeSelected[i][j] = false;
		startWord();
	}

	/**
	 * Reintializes this board to forget all information about previous words and
	 * selections.
	*/
	void startWord() {
		for (int row = 0; row < boggleBoard.getHeight(); ++row)
			for (int col = 0; col < boggleBoard.getWidth(); ++col)
				cubeSelected[row][col] = false;
		selections.clear();
		word = "";
	}

	/**
	 * @return the accumulated word
	*/
	public String getWord() {
		return word;
	}

	/**
     * Inserts the word into the discovered words list if it is legal.
	 * @return true if the accumulated word is legal
	*/
	public boolean isWord() {
		if (lexicon.isWord(word)) {
			wordList.insert(word);
			return true;
		}
		return false;
	}

	/**
	 * @return true if there was a previous selection for the
	 *         current word (plus the coordinates), false otherwise.
	*/
	public MyPair getPreviousSelection() {
		MyPair myPair = new MyPair(0, 0, false);
		if (selections.size() > 1) {
			myPair.first = selections.get(selections.size()-2).first;
			myPair.second = selections.get(selections.size()-2).second;
			myPair.flag = true;
		}
		return myPair;
	}
	
	/**
	 * Checks if cube at (row,col) is selectable. If so, appends the selected
	 * letter to the accumulated word.If not, returns false.
	 * @param row the row for the cube
	 * @param col the column for the cube
	 * @return true if cube is selectable, false otherwise
	 */
	public boolean selectCube(int row, int col) {
        // Craig added line below
        if(row < 0 || row >= getNumRows() || col < 0 || col >= getNumCols())
            return false;
        if (cubeSelected[row][col] == false &&
			  (selections.size() == 0 ||
			  (Math.abs(currentRow - row) <= 1 && Math.abs(currentCol - col) <= 1))) {
			cubeSelected[row][col] = true;
			currentRow = row;
			currentCol = col;
			word += boggleBoard.getLetter(row, col);
			selections.add(new MyPair(row, col, false));
			return true;
		} else
			return false;
	}

    /**
     * @return the number of rows in the current game board.
     */
    public int getNumRows() {
        return boggleBoard.getHeight();
    }

    /**
     * @return the number of columns in the current game board.
     */
    public int getNumCols() {
    	return boggleBoard.getWidth();
    }

    /**
     * Causes the board to revert to previous state, before the most recent
     * cube selection.  The last character is removed from the ongoing word
     * and the last cube selected is forgotten.
     */
    void stepBack() {
    	if(word.length() >= 2
    		&& word.substring(word.length() - 2, word.length()).equals("qu"))
    		word = word.substring(0, word.length() - 2);
    	else
    		word = word.substring(0, word.length() - 1);
        MyPair pair = selections.remove(selections.size() - 1);
        cubeSelected[pair.first][pair.second] = false;
        if(word.length() > 0) {
            currentRow = selections.get(selections.size()-1).first;
            currentCol = selections.get(selections.size()-1).second;
        }
    }

    /**
     * @return one String containing all words in list of discovered words
     */
    public String getWordList() {
    	return wordList.toString();
    }
}
