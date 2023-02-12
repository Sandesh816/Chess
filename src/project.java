import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

// comment

public class project {
    public static void main(String[] args){
        JFrame frame = new JFrame("Square Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new draw());
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
