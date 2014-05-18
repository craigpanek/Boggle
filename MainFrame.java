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
public class MainFrame extends JFrame {
    private final JTextField upperDisplay;
    private final JTextField lowerDisplay;
    private final JPanel centerPanel;

    public MainFrame() {
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
        setSize(600, 600);
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
        buttonPanel.add(makeDigitButton("1"));
        buttonPanel.add(makeDigitButton("2"));
        buttonPanel.add(makeDigitButton("3"));
        buttonPanel.add(makeDigitButton("4"));
        buttonPanel.add(makeDigitButton("5"));
        buttonPanel.add(makeDigitButton("6"));
        buttonPanel.add(makeDigitButton("7"));
        buttonPanel.add(makeDigitButton("8"));
        buttonPanel.add(makeDigitButton("9"));
        buttonPanel.add(makeDigitButton("10"));
        buttonPanel.add(makeDigitButton("11"));
        buttonPanel.add(makeDigitButton("12"));
        buttonPanel.add(makeDigitButton("13"));
        buttonPanel.add(makeDigitButton("14"));
        buttonPanel.add(makeDigitButton("15"));
        buttonPanel.add(makeDigitButton("16"));
        buttonPanel.add(makeDigitButton("17"));
        buttonPanel.add(makeDigitButton("18"));
        buttonPanel.add(makeDigitButton("19"));
        buttonPanel.add(makeDigitButton("20"));
        buttonPanel.add(makeDigitButton("21"));
        buttonPanel.add(makeDigitButton("22"));
        buttonPanel.add(makeDigitButton("23"));
        buttonPanel.add(makeDigitButton("24"));
        buttonPanel.add(makeDigitButton("25"));
        centerPanel.add(buttonPanel);
    }
    
    private void createLeftTextField() {
        JPanel leftPanel = new JPanel();
        JTextArea leftWordList = new JTextArea(32, 12);
        leftPanel.add(leftWordList);
        add(leftPanel, BorderLayout.WEST);
    }
    
    private void createRightTextField() {
        JPanel rightPanel = new JPanel();
        JTextArea rightWordList = new JTextArea(32, 12);
        rightPanel.add(rightWordList);
        add(rightPanel, BorderLayout.EAST);
    }
       
    private void createLowerButtons() {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridLayout(1, 4));
        lowerPanel.setPreferredSize(new Dimension(350, 50));
        lowerPanel.setMaximumSize(new Dimension(350, 50));
        lowerPanel.setMinimumSize(new Dimension(350, 50));
        lowerPanel.add(makeDigitButton("Reset"));
        lowerPanel.add(makeDigitButton("Clear"));
        lowerPanel.add(makeDigitButton("Submit"));
        lowerPanel.add(makeDigitButton("Auto"));
        centerPanel.add(lowerPanel);
    }
    
    class DigitButtonListener implements ActionListener {
        private final String digit;

        /**
         * Constructs a listener whose actionPerformed method adds a digit to
         * the display.
         * 
         * @param aDigit
         *            the digit to add
         */
        public DigitButtonListener(String aDigit) {
            digit = aDigit;
        }

        public void actionPerformed(ActionEvent event) {
            upperDisplay.setText(upperDisplay.getText() + digit);
            lowerDisplay.setText(lowerDisplay.getText() + digit);
        }
    }

    /**
     * Makes a button representing a digit of a calculator.
     * 
     * @param digit
     *            the digit of the calculator
     * @return the button of the calculator
     */
    public JButton makeDigitButton(String digit) {
        JButton button = new JButton(digit);
        
        ActionListener listener = new DigitButtonListener(digit);
        button.addActionListener(listener);
        return button;
    }

    /**
     * Makes a button representing an operator of a calculator.
     * 
     * @param op
     *            the operator of the calculator
     * @return button the button of the calcalator
     */
    public JButton makeOperatorButton(String op) {
        JButton button = new JButton(op);
        return button;
    }
}
