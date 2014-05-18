import javax.swing.JFrame;

public class CalculatorViewer {
    public static void main(String[] args) {
        JFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calculator");
        frame.setSize(650, 560);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
