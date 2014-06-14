import java.io.FileNotFoundException;
import java.util.ArrayList;

// BoggleGame class
// Peter Nguyen and Craig Panek
// Friday, June 13, 2014

public class BoggleGame {

	private BoggleBoard boggleBoard;
	private Lexicon lexicon;
	private int currentRow;
	private int currentCol;
	private OrderedList wordList;

	ArrayList<MyPair> selections = new ArrayList<MyPair>();

	// Keeps track of which cubes have been selected
	boolean[][] cubeSelected;
	//bool cubeSelected[5][5];

	private String word;

	public BoggleGame(BoggleBoard boggleBoard) throws FileNotFoundException {
		this.boggleBoard = boggleBoard;
		lexicon = new Lexicon("Enhanced North American Benchmark Lexicon.txt");
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
	 * Returns the accumulated word
	 * @return the accumulated word
	*/
	public String getWord() {
		return word;
	}

	/**
	 * Returns true if the accumulated word is legal, false otherwise.
     * (BY CRAIG) - Also inserts the word into the list if it is legal.
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
	 * Returns the row and column of the previous selection
	 * (not the current selection).
	 * Also returns true if there was a previous selection for the
	 * current word, false otherwise.
	 * @return true if there was a previous selection for the
	 *         current word, false otherwise.
	*/
	boolean getPreviousSelection(Integer row, Integer col) {
		if (selections.size() <= 1)
			return false;
		row = selections.get(selections.size()-2).first;
		col = selections.get(selections.size()-2).second;
		return true;
	}
	
	/**
	 * Checks to see if cube at (row,col) is selectable:i.e. has not previously been
	 * selected and is a neighbor of the last cube selected (or the first selection).
	 * If selectable, appends the letter on the cube at (row,col) to the word that
	 * board is accumulating. If not selectable, returns false.
	 *
	 * @param row the row for the cube to select
	 * @param col the column for the cube to select
	 * @return true if cube at (row,col) has not previously been selected, false otherwise
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
			selections.add(new MyPair(row, col));
			return true;
		} else
			return false;
	}

    /**
     * Returns the number of rows in the current game board.
     *
     * @return the number of rows in the current game board.
     */
    public int getNumRows() {
        return boggleBoard.getHeight();
    }

    /**
     * Returns the number of columns in the current game board.
     *
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
    	assert(false);
    	/*  assert(word.length() > 0);
        word = word.substr(0, word.length() - 1);
        int row = selections[selections.size()-1].first;
        int col = selections[selections.size()-1].second;
        cubeSelected[row][col] = false;
        selections.pop_back();
        if(word.length() > 0) {
            currentRow = selections[selections.size()-1].first;
            currentCol = selections[selections.size()-1].second;
        }
    */
    }

    public String getWordList() {
    	return wordList.toString();
    }
}

