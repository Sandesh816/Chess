import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

public class Draw extends JPanel {
    private int selectedX = -1;
    private int selectedY = -1;

    Board board;

    public Draw(Board board){
        this.board = board;
    }

    public void setSelectedSquare(int x, int y) {
        selectedX = x;
        selectedY = y;
        repaint();
    }
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
                if (row == selectedY && col == selectedX) {
                    g.setColor(Color.YELLOW);
                }
                g.fillRect(s, t, squareSize, squareSize);
            }
        }
        //Board board = new Board();
        for (int i = 0; i < 8; i ++){
            for (int j = 0; j < 8; j++){
                int s = 80 + i * squareSize + 20;
                int t = 80 + j * squareSize + 40;
                g.setColor(Color.BLACK);
                g.drawString(board.getPiece(i,j).toString(),s,t);
            }
        }
    }
}