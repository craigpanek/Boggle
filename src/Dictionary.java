/**
 * A class that represents a dictionary, and that can be interrogated to
 * determine whether or not a given word exists within.
 * The file that contains the list of words is sent to the constructor.
 * @author Craig Panek
 * Date: 6-19-2014
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Dictionary {

	private Trie trie = new Trie();

	/**
	 * Populates the dictionary with words.
	 * @param wordsFilename the name of file that contains the list of words.
	 * @throws FileNotFoundException
	 */
	public Dictionary(String wordsFilename) throws FileNotFoundException {
	    loadDictionary(wordsFilename);
	}

	/**
	 * @param word the word for which to search
	 * @return true if word exists in dictionary, and false otherwise
	 */
	public boolean isWord(String word) {
	    return (trie.exists(word));
	}

	/**
	 * Populates the dictionary with words.
	 * @param wordsFilename the name of file that contains the list of words.
	 * @throws FileNotFoundException 
	 */
	public void loadDictionary(String wordsFilename) throws FileNotFoundException {
	    File inputFile = new File(wordsFilename);
	    Scanner in = new Scanner(inputFile);
	    while(in.hasNext()) {
	    	String word = in.next();
	    	trie.insert(word);
	    }
	    in.close();
	}
}
