import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Draw extends JPanel {
    private int selectedX = -1;
    private int selectedY = -1;

    Possibilities bestMove;

    LinkedList<Pair> moves;

    Board board;
    int goodness;
    List<Piece> takenPieceBlack = new ArrayList<>();
    List<Piece> takenPieceWhite = new ArrayList<>();
    public Draw(Board board){
        this.board = board;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = 640;
        int height = 640;
        int squareSize = width/8;

        Color bg = new Color(122, 121, 118);
        g.setColor(bg);
        g.fillRect(0,0,1200,800);

        g.setColor(Color.white);
        g.fillRect(1050,80,60,640);
        g.setColor(new Color(230, 115, 126));
        int goodnessRatio = (goodness + 5000) * 100 / 10000;
        if (goodnessRatio > 100){
            goodnessRatio = 100;
        } else if (goodnessRatio < 0){
            goodnessRatio = 0;
        }
        System.out.println(goodness);
        System.out.print(goodnessRatio);
        int goodnessLength = 640 * goodnessRatio / 100;
        g.fillRect(1050,720-goodnessLength,60,goodnessLength);

        if (bestMove!=null){
            g.setColor(Color.WHITE);
            g.fillRect(780,360,200,50);
            g.setColor(Color.BLACK);
            g.drawString("Hint: ",800,380);
            if (board.board[bestMove.originalPair.x][bestMove.originalPair.y].getPiece() != null){
                g.drawString("Move " + board.board[bestMove.originalPair.x][bestMove.originalPair.y].getPiece() + " at " + (char) (bestMove.originalPair.x + 'a') + (8 - bestMove.originalPair.y) + " to " + (char) (bestMove.newPair.x + 'a') + (8 - bestMove.newPair.y)  ,800,400);
            }
        }
        // Adding hints
        g.setColor(Color.white);
        g.fillRect(600, 200, 50, 50);
        g.fillRect(600, 600, 50, 50);

        // show resign button
        try {
            BufferedImage resignImage = ImageIO.read(getClass().getResource("resign.png"));
            // calculate the new width and height to fit inside the square while maintaining the aspect ratio
            g.drawImage(resignImage, 960, 650,70,70, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedImage resignImage = ImageIO.read(getClass().getResource("resign.png"));
            // calculate the new width and height to fit inside the square while maintaining the aspect ratio
            g.drawImage(resignImage, 960, 80,70,70, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedImage hintImage = ImageIO.read(getClass().getResource("Hint.jpg"));
            // calculate the new width and height to fit inside the square while maintaining the aspect ratio
            g.drawImage(hintImage, 850, 600,70,70, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedImage hintImage = ImageIO.read(getClass().getResource("Hint.jpg"));
            // calculate the new width and height to fit inside the square while maintaining the aspect ratio
            g.drawImage(hintImage, 850, 80,70,70, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // draw taken pieces
        int blackPawnCount = 0;
        int blackKnightCount = 0;
        int blackBishopCount = 0;
        int blackRookCount = 0;
        int blackQueenCount = 0;
        int whitePawnCount = 0;
        int whiteKnightCount = 0;
        int whiteBishopCount = 0;
        int whiteRookCount = 0;
        int whiteQueenCount = 0;

        for (int i = 0; i < takenPieceBlack.size(); i++){
            if (takenPieceBlack.get(i).toString() == "Pawn"){
                blackPawnCount += 1;
            } else if (takenPieceBlack.get(i).toString() == "Knight") {
                blackKnightCount += 1;
            } else if (takenPieceBlack.get(i).toString() == "Bishop") {
                blackBishopCount += 1;
            } else if (takenPieceBlack.get(i).toString() == "Rook") {
                blackRookCount += 1;
            } else if (takenPieceBlack.get(i).toString() == "Queen") {
                blackQueenCount += 1;
            }
        }

        for (int i = 0; i < takenPieceWhite.size(); i++){
            if (takenPieceWhite.get(i).toString() == "Pawn"){
                whitePawnCount += 1;
            } else if (takenPieceWhite.get(i).toString() == "Knight") {
                whiteKnightCount += 1;
            } else if (takenPieceWhite.get(i).toString() == "Bishop") {
                whiteBishopCount += 1;
            } else if (takenPieceWhite.get(i).toString() == "Rook") {
                whiteRookCount += 1;
            } else if (takenPieceWhite.get(i).toString() == "Queen") {
                whiteQueenCount += 1;
            }
        }
        try {
            BufferedImage blackPawnImage = ImageIO.read(getClass().getResource("chess-pawn.png"));
            BufferedImage blackKnightImage = ImageIO.read(getClass().getResource("chess-knight.png"));
            BufferedImage blackBishopImage = ImageIO.read(getClass().getResource("chess-bishop.png"));
            BufferedImage blackRookImage = ImageIO.read(getClass().getResource("chess-rook.png"));
            BufferedImage blackQueenImage = ImageIO.read(getClass().getResource("chess-queen.png"));
            BufferedImage whitePawnImage = ImageIO.read(getClass().getResource("chess-pawn-white.png"));
            BufferedImage whiteKnightImage = ImageIO.read(getClass().getResource("chess-knight-white.png"));
            BufferedImage whiteBishopImage = ImageIO.read(getClass().getResource("chess-bishop-white.png"));
            BufferedImage whiteRookImage = ImageIO.read(getClass().getResource("chess-rook-white.png"));
            BufferedImage whiteQueenImage = ImageIO.read(getClass().getResource("chess-queen-white.png"));
            // blackPawn
            int x = 800;
            int y = 640;

            for(int i = 0; i < blackPawnCount; i++) {
                if (i == 4){
                    x = 800;
                }
                if (i <= 3){
                    g.drawImage(blackPawnImage, x, y, 20, 30, null);
                    x += 40;
                } else {
                    g.drawImage(blackPawnImage, x, 680, 20, 30, null);
                    x += 40;
                }
            }

            x = 800;
            y = 600;

            for(int i = 0; i < blackKnightCount; i++) {
                g.drawImage(blackKnightImage, x, y, 20, 30, null);
                x += 40;
            }

            x = 800;
            y = 560;

            for(int i = 0; i < blackBishopCount; i++) {
                g.drawImage(blackBishopImage, x, y, 20, 30, null);
                x += 40;
            }

            x = 800;
            y = 520;

            for(int i = 0; i < blackRookCount; i++) {
                g.drawImage(blackRookImage, x, y, 20, 30, null);
                x += 40;
            }

            x = 800;
            y = 480;

            for(int i = 0; i < blackQueenCount; i++) {
                g.drawImage(blackQueenImage, x, y, 20, 30, null);
                x += 40;
            }

            x = 800;
            y = 240;

            for(int i = 0; i < whitePawnCount; i++) {
                if (i == 4){
                    x = 800;
                }
                if (i <= 3){
                    g.drawImage(whitePawnImage, x, y, 20, 30, null);
                    x += 40;
                } else {
                    g.drawImage(whitePawnImage, x, 280, 20, 30, null);
                    x += 40;
                }
            }

            x = 800;
            y = 200;

            for(int i = 0; i < whiteKnightCount; i++) {
                g.drawImage(whiteKnightImage, x, y, 20, 30, null);
                x += 40;
            }

            x = 800;
            y = 160;

            for(int i = 0; i < whiteBishopCount; i++) {
                g.drawImage(whiteBishopImage, x, y, 20, 30, null);
                x += 40;
            }

            x = 800;
            y = 120;

            for(int i = 0; i < whiteRookCount; i++) {
                g.drawImage(whiteRookImage, x, y, 20, 30, null);
                x += 40;
            }

            x = 800;
            y = 80;

            for(int i = 0; i < whiteQueenCount; i++) {
                g.drawImage(whiteQueenImage, x, y, 20, 30, null);
                x += 40;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // give numbers (1-8) and alphabets (a-h)

        g.setColor(Color.white);

        g.drawString("a", 160 - 40, 750);
        g.drawString("b", 240 - 40, 750);
        g.drawString("c", 320 - 40, 750);
        g.drawString("d", 400 - 40, 750);
        g.drawString("e", 480 - 40, 750);
        g.drawString("f", 560 - 40, 750);
        g.drawString("g", 640 - 40, 750);
        g.drawString("h", 720 - 40, 750);

        g.drawString("1", 50, 720 - 40);
        g.drawString("2", 50, 640 - 40);
        g.drawString("3", 50, 560 - 40);
        g.drawString("4", 50, 480 - 40);
        g.drawString("5", 50, 400 - 40);
        g.drawString("6", 50, 320 - 40);
        g.drawString("7", 50, 240 - 40);
        g.drawString("8", 50, 160 - 40);

        // draw a little bit bigger board

        Color custom = new Color(255, 189, 89);
        Color custom2 = new Color(186, 136, 60);

        g.setColor(custom2);
        g.fillRect(70,70,660,660);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int s = 80 + col * squareSize;
                int t = 80 + row * squareSize;
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(custom);
                }
                if (row == selectedY && col == selectedX) {
                    g.setColor(Color.YELLOW);
                }
                if (this.moves != null){
                    for (int i = 0; i < moves.size(); i++){
                        if (row == moves.get(i).y && col == moves.get(i).x) {
                            g.setColor(Color.GRAY);
                        }
                    }
                }
                g.fillRect(s, t, squareSize, squareSize);
            }
        }
        //Board board = new Board();
        drawBoard(0,squareSize,g);
    }

    public void drawBoard(int mode, int squareSize, Graphics g){
        if (mode == 0){
            for (int i = 0; i < 8; i ++){
                for (int j = 0; j < 8; j++){
                    int s = 80 + i * squareSize + 10;
                    int t = 80 + j * squareSize + 10;
                    if (board.board[i][j].getPiece() != null){
                        BufferedImage image = null;
                        try {
                            if (board.board[i][j].getPiece().color == 1) {
                                if (board.board[i][j].getPiece().toString() == "Rook"){
                                    image = ImageIO.read(getClass().getResource("chess-rook.png"));
                                } else if (board.board[i][j].getPiece().toString() == "Knight"){
                                    image = ImageIO.read(getClass().getResource("chess-knight.png"));
                                } else if (board.board[i][j].getPiece().toString() == "Bishop"){
                                    image = ImageIO.read(getClass().getResource("chess-bishop.png"));
                                } else if (board.board[i][j].getPiece().toString() == "Queen"){
                                    image = ImageIO.read(getClass().getResource("chess-queen.png"));
                                } else if (board.board[i][j].getPiece().toString() == "King"){
                                    image = ImageIO.read(getClass().getResource("chess-king.png"));
                                } else if (board.board[i][j].getPiece().toString() == "Pawn"){
                                    image = ImageIO.read(getClass().getResource("chess-pawn.png"));
                                }
                            } else if (board.board[i][j].getPiece().color == -1) {
                                if (board.board[i][j].getPiece().toString() == "Rook"){
                                    image = ImageIO.read(getClass().getResource("chess-rook-white.png"));
                                } else if (board.board[i][j].getPiece().toString() == "Knight"){
                                    image = ImageIO.read(getClass().getResource("chess-knight-white.png"));
                                } else if (board.board[i][j].getPiece().toString() == "Bishop"){
                                    image = ImageIO.read(getClass().getResource("chess-bishop-white.png"));
                                } else if (board.board[i][j].getPiece().toString() == "Queen"){
                                    image = ImageIO.read(getClass().getResource("chess-queen-white.png"));
                                } else if (board.board[i][j].getPiece().toString() == "King"){
                                    image = ImageIO.read(getClass().getResource("chess-king-white.png"));
                                } else if (board.board[i][j].getPiece().toString() == "Pawn"){
                                    image = ImageIO.read(getClass().getResource("chess-pawn-white.png"));
                                }
                            }
                            // calculate the new width and height to fit inside the square while maintaining the aspect ratio
                            g.drawImage(image, s + 10, t,40,60, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    g.setColor(Color.BLACK);
                }
            }
        } else if (mode == 1){
            for (int i = 0; i < 8; i ++){
                for (int j = 0; j < 8; j++){
                    int s = 80 + i * squareSize + 20;
                    int t = 80 + j * squareSize + 40;
                    if (board.board[i][j].getPiece() != null){
                        if (board.board[i][j].getPiece().color == 1){
                            g.setColor(Color.RED);
                            g.drawString(board.board[i][j].getPiece().toString(),s,t);
                        } else if(board.board[i][j].getPiece().color == -1){
                            g.setColor(Color.BLUE);
                            g.drawString(board.board[i][j].getPiece().toString(),s,t);
                        }
                    }
                    g.setColor(Color.BLACK);
                }
            }
        } else if (mode == 2){
            for (int i = 0; i < 8; i ++){
                for (int j = 0; j < 8; j++){
                    int s = 80 + i * squareSize + 10;
                    int t = 80 + j * squareSize + 10;
                    if (board.board[i][j].getPiece() != null){
                        BufferedImage image = null;
                        try {
                            if (board.board[i][j].getPiece().color == 1) {
                                if (board.board[i][j].getPiece().toString() == "Rook"){
                                    image = ImageIO.read(getClass().getResource("rook-black.png"));
                                } else if (board.board[i][j].getPiece().toString() == "Knight"){
                                    image = ImageIO.read(getClass().getResource("knight-black.png"));
                                } else if (board.board[i][j].getPiece().toString() == "Bishop"){
                                    image = ImageIO.read(getClass().getResource("bishop-black.png"));
                                } else if (board.board[i][j].getPiece().toString() == "Queen"){
                                    image = ImageIO.read(getClass().getResource("queen-black.png"));
                                } else if (board.board[i][j].getPiece().toString() == "King"){
                                    image = ImageIO.read(getClass().getResource("king-black.png"));
                                } else if (board.board[i][j].getPiece().toString() == "Pawn"){
                                    image = ImageIO.read(getClass().getResource("pawn-black.png"));
                                }
                            } else if (board.board[i][j].getPiece().color == -1) {
                                if (board.board[i][j].getPiece().toString() == "Rook"){
                                    image = ImageIO.read(getClass().getResource("rook-white.png"));
                                } else if (board.board[i][j].getPiece().toString() == "Knight"){
                                    image = ImageIO.read(getClass().getResource("knight-white.png"));
                                } else if (board.board[i][j].getPiece().toString() == "Bishop"){
                                    image = ImageIO.read(getClass().getResource("bishop-white.png"));
                                } else if (board.board[i][j].getPiece().toString() == "Queen"){
                                    image = ImageIO.read(getClass().getResource("queen-white.png"));
                                } else if (board.board[i][j].getPiece().toString() == "King"){
                                    image = ImageIO.read(getClass().getResource("king-white.png"));
                                } else if (board.board[i][j].getPiece().toString() == "Pawn"){
                                    image = ImageIO.read(getClass().getResource("pawn-white.png"));
                                }
                            }
                            g.drawImage(image, s, t,65, 65,null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    g.setColor(Color.BLACK);
                }
            }
        }
    }

    public void setPieceToHighlight(int selectedX, int selectedY){
        this.selectedX = selectedX;
        this.selectedY = selectedY;
    }

    public void setBestMove(Possibilities bestMove){
        this.bestMove = bestMove;
    }
    public void setMovesToHighlight(LinkedList<Pair> moves){
        this.moves = moves;
    }

    public boolean squareIsHilighted(Pair pair){
        for (int i = 0; i < moves.size(); i++){
            if (pair.x == moves.get(i).x && pair.y == moves.get(i).y){
                return true;
            }
        }
        return false;
    }

    public void setGoodness(int goodness){this.goodness = goodness;}
}