/**
 * This class represents a boggleboard game Frame
 * of cubes.
 * @author Craig Panek
 * @author Peter Nguyen
 * Date: 6-19-2014
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This frame contains panels that displays components necessary for a
 * Boggle game.  Users play the game by clicking on buttons.
 */
public class BoggleFrame extends JFrame {
    private JTextField display;
    private JTextField message;
    private JTextArea userGeneratedWords;
    private JTextArea computerGeneratedWords;
    private BoggleBoard board;
    private final int NUM_CUBES_HIGH = 5;
    private final int NUM_CUBES_WIDE = 5;
    private JButton cubeButtons[][] = new JButton[NUM_CUBES_HIGH][NUM_CUBES_WIDE];
    private BoggleGame game;
    private Font font1;
    private Font font2;
    
    public BoggleFrame() throws FileNotFoundException {
    	board = new BoggleBoard(NUM_CUBES_HIGH, NUM_CUBES_WIDE);
        game = new BoggleGame(board);
    	font1 = new Font("plain", Font.PLAIN, 32);
    	font2 = new Font("plain", Font.PLAIN, 24);
        display = new JTextField(100);
        display.setFont(font1);
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        JPanel buttonPanel = createButtonPanel();
        message = new JTextField(100);
        message.setFont(font1);
        message.setEditable(false);
        message.setBackground(Color.WHITE);
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout(5, 5));
        middlePanel.add(display, BorderLayout.NORTH);
        middlePanel.add(buttonPanel, BorderLayout.CENTER);
        middlePanel.add(message, BorderLayout.SOUTH);
        JPanel leftPanel = createLeftTextPanel();
        JPanel rightPanel = createRightTextPanel();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        add(mainPanel);
    }

    /**
     * Creates the button panel.
     */
    private JPanel createButtonPanel() {
    	JPanel buttonPanel = new JPanel();
    	buttonPanel.setLayout(new GridLayout(NUM_CUBES_HIGH + 1, 1, 5, 5));
        for(int row = 0; row < NUM_CUBES_HIGH; row++) {
        	JPanel rowPanel = new JPanel();
        	rowPanel.setLayout(new GridLayout(1, NUM_CUBES_WIDE, 5, 5));
        	for (int col = 0; col < NUM_CUBES_WIDE; col++) {
        		JButton button = new JButton(board.getLetter(row, col)); // attaches a letter to the JButton 
        		button.addActionListener(new LetterButtonListener(row, col)); // attaches listener to the JButton
        		button.setFont(font1);
        		rowPanel.add(button);
        		cubeButtons[row][col] = button;
        	}
        	buttonPanel.add(rowPanel);
        }
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridLayout(1, 4, 5, 5));
        JButton button = new JButton("Reset");
        button.setFont(font2);
        ActionListener listener = new ResetButtonListener();
        button.addActionListener(listener);
        lowerPanel.add(button);
        button = new JButton("Clear");
		button.setFont(font2);
        listener = new ClearButtonListener();
        button.addActionListener(listener);
        lowerPanel.add(button);
        button = new JButton("Submit");
		button.setFont(font2);
        listener = new SubmitButtonListener();
        button.addActionListener(listener);
        lowerPanel.add(button);
        button = new JButton("Auto");
		button.setFont(font2);
        listener = new AutoButtonListener();
        button.addActionListener(listener);
        lowerPanel.add(button);
        buttonPanel.add(lowerPanel);
        return buttonPanel;
    }
    
    /** 
     * @return a panel containing a text area for user generated words
     */
    private JPanel createLeftTextPanel() {
        JPanel leftPanel = new JPanel();
        userGeneratedWords = new JTextArea(14, 9);
        userGeneratedWords.setFont(font1);
        userGeneratedWords.setEditable(false);
        JScrollPane userWordListPane = new JScrollPane(userGeneratedWords);
        leftPanel.add(userWordListPane);
        return leftPanel;
    }
    
    /**
     * @return a panel containing a text area for computer generated words
     */
    private JPanel createRightTextPanel() {
        JPanel rightPanel = new JPanel();
        computerGeneratedWords = new JTextArea(14, 9);
        computerGeneratedWords.setEditable(false);
        computerGeneratedWords.setFont(font1);
        JScrollPane computerGeneratedWordsPane = new JScrollPane(computerGeneratedWords);
        rightPanel.add(computerGeneratedWordsPane);
        return rightPanel;
    }
    
    /**
     * Constructs a listener whose actionPerformed method selects the
     * cube clicked on
     */
	class LetterButtonListener implements ActionListener {
        private int row;
        private int col;
        
        public LetterButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        /**
         * Action for clicking on a letter on the board
         */
        public void actionPerformed(ActionEvent event) {
        	if (game.selectCube(row, col)) {
        		cubeButtons[row][col].setBackground(Color.ORANGE);
        		MyPair pair = game.getPreviousSelection();
        		if (pair.flag == true)
        			cubeButtons[pair.first][pair.second].setBackground(Color.CYAN);
        		display.setText(display.getText() + cubeButtons[row][col].getText());
        	}
        }
	}

	/**
	 *  Action for reset button
	 */
	class ResetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	board.randomize();
        	displayButtonLetters();
        	clearAll();
        	message.setText("");
        }
    }
    
    /**
     * Action for clear button
     */
	class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	clearAll();
        }
    }
    
	/**
	 * Action for submit button
	 */
	class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	String msg = "\"" + game.getWord() + "\"";

        	if(game.isWord()) {
        		msg += " is a word";
        		userGeneratedWords.setText(game.getWordList());
        	} else
        		msg += " is NOT a word";
        	message.setText(msg);
        	clearAll();
        }
    }
    
    /**
     * Action for Auto (computer player) button.
     * Creates and displays a list of all words possible for the given board
     */
    class AutoButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            clearAll();
            ComputerPlayer computerPlayer;
			try {
				computerPlayer = new ComputerPlayer(board);
	            String foundWords = computerPlayer.findWords();
	            computerGeneratedWords.setText(foundWords);
	            clearAll();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
	 /**
	  * Resets cube colors.
	  */
	 void clearCubeColor() {
		for (int row = 0; row < NUM_CUBES_HIGH; ++row)
			for (int col = 0; col < NUM_CUBES_WIDE; ++col)
				cubeButtons[row][col].setBackground(null);
	 }
	 
	 /**
	  * Gets GUI ready for a new word
	  */
	 void clearAll() {
		display.setText("");
		game.startWord();
		clearCubeColor();
	 }

	 /**
	  * Displays the letters on the cubes
	  */
	 void displayButtonLetters() {
		for (int row = 0; row < NUM_CUBES_HIGH; ++row) 
			for (int col = 0; col < NUM_CUBES_WIDE; ++col) 
				cubeButtons[row][col].setText(board.getLetter(row, col));
	 }
}