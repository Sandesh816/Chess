import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    static int staticMouseStatus;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Square Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Board board = new Board();
        Draw draw = new Draw(board);
        //game.test();
        frame.add(draw);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Add mouse listener to the Draw object
        draw.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Mouse mouse = new Mouse(x,y, board.getBoard(), draw);
                // it is initialized to select mode
                // if staticMouseStatus is 1 (move mode) then inherit that here (change to move mode)
                updateStaticMouseStatus(mouse);
                if (staticMouseStatus != 1){
                    // select mode
                    mouse.printMouseStatus();
                    System.out.println(mouse.checkInside(x,y));
                    mouse.select(x,y);
                    mouse.printMouseStatus();
                    staticMouseStatus = 1;
                } else {
                    mouse.printMouseStatus();
                    mouse.move(x,y);
                    Piece[][] updatedBoardArray = mouse.currentBoard.getBoard();
                    // System.out.println(mouse.currentBoard.)
                    board.setBoard(updatedBoardArray);
                    draw.repaint();
                }
                System.out.println("The end of a loop");
            }
            public void updateStaticMouseStatus(Mouse mouse){
                if (staticMouseStatus == 1){
                    mouse.changeToMoveMode();
                }
            }
        });
    }
}