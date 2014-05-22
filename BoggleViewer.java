import javax.swing.JFrame;

public class BoggleViewer {
    public static void main(String[] args) {
        JFrame frame = new BoggleFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Boggle");
        frame.setSize(650, 560);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
