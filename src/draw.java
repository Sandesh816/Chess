import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class draw extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = 640;
        int height = 640;
        int x = (getWidth() - width) / 2;
        int y = (getHeight() - height) / 2;
        g.drawRect(x, y, width, height);
    }
}
