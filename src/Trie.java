/**
 * This class represents a trie data structure.
 * It can hold words such that quick word searches are possible.
 * @author Craig Panek
 * @author Peter Nguyen
 * Date: 6-19-2014
 */

/** 
 * The TrieNode class is used only by the Trie class.
 * A TrieNode represents one node in the trie structure.
 */
class TrieNode {
    
	public char character;
    public TrieNode sibling;
    public TrieNode children;
    public boolean isWord;
	
    TrieNode() {
        character = '\0';
        sibling = null;
        children = null;
        isWord = false;
    }
};

/**
 * The Trie class is a data structure which contains multiple TrieNodes.
 * A Trie holds words within the structure, and allows fast word searches.
 */
public class Trie {
	
	private TrieNode root;
	
	/**
	 * Create a root node to initialize the trie. 
	 */
	public Trie() {
		root = new TrieNode();
	}
	
	/**
	 * Insert a given word into the trie (duplicates are not possible).
	 */
	public void insert(String word) {
	    TrieNode currentNode = root;

	    while (word.length() > 0) {
	        char currentChar = word.charAt(0);
	        word = word.substring(1, word.length()); // this works even if size() is 1
	        boolean endWord = (word.length() == 0);
	        currentNode = insertAtLevel(currentChar, currentNode, endWord);

	        if (!endWord) { // go down to next level
	            if (currentNode.children == null) {
	                currentNode.children = new TrieNode();
	                currentNode = currentNode.children;
	            } else
	                currentNode = currentNode.children;
	        }
	    }
	}
	
	/**
	 * @param word
	 * @return true if the given words exists in trie, false otherwise
	 */
	public boolean exists(String word) {
		TrieNode currentNode = root;
		boolean[] exists = { false };

		while (word.length() > 0 && !exists[0]) {
			char currentChar = word.charAt(0);
			word = word.substring(1, word.length());
			boolean endWord = (word.length() == 0);
			currentNode = existsAtLevel(currentChar, currentNode, endWord, exists);
			if (exists[0] == true) // return true if word exists
			    return true;
			if(currentNode == null) // return false if letter didn't exist
			    return false;
			if (!endWord) { // go down to next level
				currentNode = currentNode.children;
				if (currentNode == null) // return false if we can't go down a level
				    return false;
			}
		}
		return false;
	}
	
	/**
	 * Insert a letter at a given 'level' in the trie data structure.
	 * @param ch the letter to be inserted into the trie
	 * @param currentNode the node from which we start
	 * @param endWord is true if this is the final letter of the word
	 * @return the current node
	 */
	private TrieNode insertAtLevel(char ch, TrieNode currentNode, boolean endWord) {

		if(currentNode.character == '\0') { // at a newly created empty node
			currentNode.character = ch;
		    if (endWord)
		        currentNode.isWord = true;
		    return currentNode;
		}

	    boolean found = false;
	    TrieNode previousNode = null;
//	    assert(currentNode != NULL);
		while (currentNode != null && !found) {
			if (currentNode.character == ch)
				found = true;
			else {
			    previousNode = currentNode;
			    currentNode = currentNode.sibling;
			}
		}
		if (!found) {
		    currentNode = new TrieNode();
			previousNode.sibling = currentNode;
		    currentNode.character = ch;
		}
		if(endWord)
		    currentNode.isWord = true;
		return currentNode;
	}

	/**
	 * Returns the node we will use for the new letter at the present level
	 * @param ch the letter we are looking for
	 * @param currentNode the starting node
	 * @param endWord tells us if we are looking for the last letter in the word
	 * @param exists a modifiable boolean array value - will be set to true if the word exists
	 * @return a trienode which holds the letter we seek (if there was such a node), null otherwise
	 */
	private TrieNode existsAtLevel(char ch, TrieNode currentNode, boolean endWord, boolean[] exists) {
		
		while (currentNode != null) {
			if (currentNode.character == ch) {
			    if (endWord && currentNode.isWord)
			        exists[0] = true;
			    return currentNode;
			}
			else
			    currentNode = currentNode.sibling;
		}
		return currentNode;
	}
};
