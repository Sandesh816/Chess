import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

public class draw extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = 640;
        int height = 640;
        int squareSize = width/8;
        g.drawRect(80, 80, width, height);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int s = 80 + col * squareSize;
                int t = 80 + row * squareSize;
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.ORANGE);
                }
                g.fillRect(s, t, squareSize, squareSize);
            }
        }
    }
}
