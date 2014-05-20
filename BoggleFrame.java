import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class BoggleFrame extends JFrame {
    private JTextField upperDisplay;
    private JTextField lowerDisplay;
    private JPanel centerPanel;
    private JTextArea leftWordList;
    private JTextArea rightWordList;
    private BoggleBoard board;

    public BoggleFrame() {
        board = new BoggleBoard();
    	centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        upperDisplay = new JTextField("Peter and Craig's first practice project");
        upperDisplay.setPreferredSize(new Dimension(350, 60));
        upperDisplay.setMaximumSize(new Dimension(350, 60));
        upperDisplay.setMinimumSize(new Dimension(350, 60));
        upperDisplay.setEditable(false);
        centerPanel.add(upperDisplay);
        add(centerPanel);
        createButtonPanel();
        createLowerButtons();
        createLeftTextField();
        createRightTextField();
        lowerDisplay = new JTextField("Game messages appear here");
        lowerDisplay.setPreferredSize(new Dimension(350, 60));
        lowerDisplay.setMaximumSize(new Dimension(350, 60));
        lowerDisplay.setMinimumSize(new Dimension(350, 60));
        lowerDisplay.setEditable(false);
        centerPanel.add(lowerDisplay);
        
        // HERE IS HOW TO MAKE AND DISPLAY CUBES
        for(int i=0; i < 20; i++) {
        	Cube cube = new Cube();
        	String letter = cube.rollCube();
        	upperDisplay.setText(upperDisplay.getText() + letter);
        }
    }

    /**
     * Creates the button panel.
     */
    private void createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 5));
        buttonPanel.setPreferredSize(new Dimension(350, 350));
        buttonPanel.setMaximumSize(new Dimension(350, 350));
        buttonPanel.setMinimumSize(new Dimension(350, 350));
        for(int i=1; i < 26; i++) {
        	buttonPanel.add(makeLetterButton("" + i));
        }
        centerPanel.add(buttonPanel);
    }
    
    private void createLeftTextField() {
        JPanel leftPanel = new JPanel();
        leftWordList = new JTextArea(32, 12);
        leftPanel.add(leftWordList);
        add(leftPanel, BorderLayout.WEST);
    }
    
    private void createRightTextField() {
        JPanel rightPanel = new JPanel();
        rightWordList = new JTextArea(32, 12);
        rightPanel.add(rightWordList);
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
        private String letter;

        /**
         * Constructs a listener whose actionPerformed method adds a digit to
         * the display.
         * 
         * @param aDigit
         *            the digit to add
         */
        public LetterButtonListener(String aLetter) {
            letter = aLetter;
        }

        public void actionPerformed(ActionEvent event) {
            upperDisplay.setText(upperDisplay.getText() + letter);
            lowerDisplay.setText(lowerDisplay.getText() + letter);
        }
    }

    /**
     * Makes a button representing a digit of a calculator.
     * 
     * @param digit
     *            the digit of the calculator
     * @return the button of the calculator
     */
    private JButton makeLetterButton(String digit) {
        JButton button = new JButton(digit);
        
        ActionListener listener = new LetterButtonListener(digit);
        button.addActionListener(listener);
        return button;
    }

    class ResetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            upperDisplay.setText(upperDisplay.getText() + " Reset key pressed");
            lowerDisplay.setText(lowerDisplay.getText() + " Reset key pressed");
        }
    }
    
    class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            upperDisplay.setText(upperDisplay.getText() + " Clear key pressed");
            lowerDisplay.setText(lowerDisplay.getText() + " Clear key pressed");
        }
    }
    
    class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            upperDisplay.setText(upperDisplay.getText() + " Submit key pressed");
            lowerDisplay.setText(lowerDisplay.getText() + " Submit key pressed");
        }
    }
    
    class AutoButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            upperDisplay.setText(upperDisplay.getText() + " Auto key pressed");
            lowerDisplay.setText(lowerDisplay.getText() + " Auto key pressed");
        }
    }
}
