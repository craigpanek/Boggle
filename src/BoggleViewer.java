import java.io.FileNotFoundException;

import javax.swing.JFrame;

public class BoggleViewer {
    public static void main(String[] args) throws FileNotFoundException {
    	JFrame frame = new BoggleFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Boggle");
        frame.setSize(740, 560);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
