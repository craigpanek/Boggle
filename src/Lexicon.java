// Lexicon
// Peter Nguyen and Craig Panek
// Friday, June 13, 2014

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class that represents a list of words and that can be interrogated to
 * determine whether or not word exists in the list. The file that contains
 * the list of words is sent to the constructor.
 */
public class Lexicon {
	
	private ArrayList<String> lexicon;
		
	/**
	 * Initializes the lexicon, given the file name that contains the list
	 * of words.
	 * @param lexiconFileName the file name that contains the list of words.
	 * @throws FileNotFoundException 
	 * @throws Exception 
	 */
	public Lexicon(String lexiconFileName) throws FileNotFoundException {
		lexicon = new ArrayList<String>();
		loadLexicon(lexiconFileName);
	}
	
	/**
	 * Returns true if word exists in lexicon
	 * @param word the word for which to search
	 * @return true if word exists in lexicon, and false otherwise
	 */
	public boolean isWord(String word) {
		for(int i=0; i < lexicon.size(); i++)
			if(lexicon.get(i).equals(word))
				return true;
		return false;
	}

	/**
	 * Loads lexicon from lexiconFileName, exiting with an error message on
	 * on stderr if lexiconFileName cannot be opened.
	 * @param lexiconFileName the file name that contains the list of words.
	 * @throws FileNotFoundException 
	 */
	private void loadLexicon(String lexiconFileName) throws FileNotFoundException {
		File inputFile = new File(lexiconFileName);
		Scanner in = new Scanner(inputFile);
		while(in.hasNext()) {
			lexicon.add(in.next());
		}
		in.close();
	}
}


