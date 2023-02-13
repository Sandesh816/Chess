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

        Piece[][] array = new Piece[8][8];
        board board = new board(array);
        board.initialize();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int s = 80 + col * squareSize + 20;
                int t = 80 + row * squareSize + 40;
                Piece piece = board.current()[row][col];
                int rank = piece.getRank();
                int side = piece.getSide();
                if (side == 1) {
                    g.setColor(Color.blue);
                    g.drawString(piece.RanktoString(),s,t);
                } else if (side == 2){
                    g.setColor(Color.RED);
                    g.drawString(piece.RanktoString(),s,t);
                }
            }
        }

        Piece piece = board.current()[0][0];
        if (piece != null) {
            System.out.println(piece.getRank());
        } else {
            System.out.println("No piece at position 0, 0");
        }
    }
}