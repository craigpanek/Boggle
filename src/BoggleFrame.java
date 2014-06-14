import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This frame contains panels that displays components necessary for a
 * Boggle game.  Users play the game by clicking on buttons.
 */
//@SuppressWarnings("serial")
public class BoggleFrame extends JFrame {
    private JTextField display;
    private JTextField message;
    private JPanel centerPanel;
    private JTextArea userWordListPane;
    private JTextArea computerGeneratedWordsPane;
    private BoggleBoard board;
    private final int NUM_CUBES_HIGH = 5;
    private final int NUM_CUBES_WIDE = 5;
    private JButton cubeButtons[][] = new JButton[NUM_CUBES_HIGH][NUM_CUBES_WIDE];
    private BoggleGame game;
    
    public BoggleFrame() throws FileNotFoundException {
        board = new BoggleBoard(NUM_CUBES_HIGH, NUM_CUBES_WIDE);
        game = new BoggleGame(board);
        board.randomize();
    	centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        display = new JTextField("");
        display.setPreferredSize(new Dimension(350, 60));
        display.setMaximumSize(new Dimension(350, 60));
        display.setMinimumSize(new Dimension(350, 60));
        display.setEditable(false);
        centerPanel.add(display);
        add(centerPanel);
        createButtonPanel();
        createLowerButtons();
        createLeftTextField();
        createRightTextField();
        message = new JTextField("Game messages appear here");
        message.setPreferredSize(new Dimension(350, 60));
        message.setMaximumSize(new Dimension(350, 60));
        message.setMinimumSize(new Dimension(350, 60));
        message.setEditable(false);
        centerPanel.add(message);
    }

    /**
     * Creates the button panel.
     */
    private void createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(NUM_CUBES_HIGH, NUM_CUBES_WIDE));
        buttonPanel.setPreferredSize(new Dimension(350, 350));
        buttonPanel.setMaximumSize(new Dimension(350, 350));
        buttonPanel.setMinimumSize(new Dimension(350, 350));
        for(int row = 0; row < 5; row++) {
        	for (int col = 0; col < 5; col++) {
        		cubeButtons[row][col] = new JButton(board.getLetter(row, col)); // attaches a letter to the JButton
        		cubeButtons[row][col].addActionListener(new LetterButtonListener(board.getLetter(row, col))); // attaches listener to the JButton
        		buttonPanel.add(cubeButtons[row][col]);
        	}
        }
        centerPanel.add(buttonPanel);
    }
    
    private void createLeftTextField() {
        JPanel leftPanel = new JPanel();
        userWordListPane = new JTextArea(32, 12);
        userWordListPane.setEditable(false);
        leftPanel.add(userWordListPane);
        add(leftPanel, BorderLayout.WEST);
    }
    
    private void createRightTextField() {
        JPanel rightPanel = new JPanel();
        computerGeneratedWordsPane = new JTextArea(32, 12);
        computerGeneratedWordsPane.setEditable(false);
        rightPanel.add(computerGeneratedWordsPane);
        add(rightPanel, BorderLayout.EAST);
    }
       
    private void createLowerButtons() {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridLayout(1, 4));
        lowerPanel.setPreferredSize(new Dimension(350, 50));
        lowerPanel.setMaximumSize(new Dimension(350, 50));
        lowerPanel.setMinimumSize(new Dimension(350, 50));
        JButton button = new JButton("Reset");
        ActionListener listener = new ResetButtonListener();
        button.addActionListener(listener);
        lowerPanel.add(button);
        button = new JButton("Clear");
        listener = new ClearButtonListener();
        button.addActionListener(listener);
        lowerPanel.add(button);
        button = new JButton("Submit");
        listener = new SubmitButtonListener();
        button.addActionListener(listener);
        lowerPanel.add(button);
        button = new JButton("Auto");
        listener = new AutoButtonListener();
        button.addActionListener(listener);
        lowerPanel.add(button);
        centerPanel.add(lowerPanel);
    }
    
    class LetterButtonListener implements ActionListener {
        Integer row, col;
        Integer prevRow = 0, prevCol = 0;
        /**
         * Constructs a listener whose actionPerformed method adds a digit to
         * the display.
         * 
         * @param aDigit
         *            the digit to add
         */
        public LetterButtonListener(Integer row, Integer col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent event) {
        	if (game.selectCube(row, col)) {
        		cubeButtons[row][col].setBackground(Color.ORANGE);
        		if (game.getPreviousSelection(prevRow, prevCol)) {
        			cubeButtons[prevRow][prevCol].setBackground(Color.BLUE);
        		}
        		String text = display.getText() + cubeButtons[row][col].getText();
        		display.setText(text);
        	}
        }
    }

    class ResetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	board.randomize();
        	displayButtonLetters();
        	clearAll();
        	message.setText("");
        }
    }
    
    class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	clearAll();
        }
    }
    
    class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	String msg = game.getWord();

        	if(game.isWord()) {
        		msg += " is a Word";
        		userWordListPane.setText(game.getWordList());
        	} else {
        		msg += " is NOT a Word";
        	}
        	message.setText(msg);
        	clearAll();
        }
    }
    
    class AutoButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            /*ComputerPlayer computerPlayer(game);
            clearAll();
            string foundWords = computerPlayer.findWords();
            computerGeneratedWordsPane->setPlainText(QString(foundWords.c_str()));
            clearAll();*/
        }
    }
    
	 /**
	  * Resets cube colors.
	  */
	 void clearCubeColor() {
		assert(false);
		/*for (int row = 0; row < NUM_CUBES_HIGH; ++row) {
			for (int col = 0; col < NUM_CUBES_WIDE; ++col) {
				QWidget *button = cubeLayout->itemAtPosition(row, col)->widget();
				button->setStyleSheet("background-color:gainsboro;");
			}
		}*/
	 }
	 
	 /**
	  * Gets GUI ready for a new word
	  */
	 void clearAll() {
		display.setText("");
		game.startWord();
		//clearCubeColor();
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