import java.io.FileNotFoundException;

import javax.swing.JFrame;

public class BoggleViewer {
    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new BoggleFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Boggle");
        frame.setSize(650, 560);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        Lexicon lexicon = new Lexicon("Enhanced North American Benchmark Lexicon.txt");
        OrderedList list = new OrderedList();
        list.insert("hello");
        list.insert("goodbye");
        list.insert("hello");
        System.out.println(list.toString());
    }
}
