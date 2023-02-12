import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class board extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = 640;
        int height = 640;
        int x = (getWidth() - width) / 2;
        int y = (getHeight() - height) / 2;
        g.drawRect(x, y, width, height);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Square Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new board());
        int width = 800;
        int height = 800;
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
