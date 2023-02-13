import javax.swing.*;
import java.awt.*;
public class king extends JPanel {
    public int x;
    public int y;
    public king (int x, int y){
        this.x = x;
        this.y = y;
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 80, 80);
    }
}
