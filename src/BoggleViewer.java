/**
 * This class represents the main boggleviewer program
 * @author Craig Panek
 * @author Peter Nguyen
 * Date: 6-19-2014
 */

import java.io.FileNotFoundException;
import javax.swing.JFrame;

public class BoggleViewer {
    public static void main(String[] args) throws FileNotFoundException {
    	JFrame frame = new BoggleFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Boggle");
        frame.setSize(1062, 650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
