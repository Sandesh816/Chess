import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;

public class Main {
    static Pair selectedSquare;
    static int turn = -1;
    static boolean isSuicide = false;
    static String userStatus;
    /*
    /.............................../.............................../...............................
    /.............................../.............................../...............................
    /.............................../.............................../...............................
    For Hint, please press the hint button before selecting your piece.
    Three-fold repetition is working as well, the game will result in a draw in that case as per the rules of chess
    When there is a checkmate or stalemate, the opponent player will not be able to move any pieces and the game will end.
    /.............................../.............................../...............................
    /.............................../.............................../...............................
    /.............................../.............................../...............................
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess");
        String mode = "";
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Board board = new Board(true);
        Record record = new Record();
        Brain brain = new Brain(board);
        // mode select
        int choice = JOptionPane.showOptionDialog(frame,
                "Welcome to the Chess Game\nChoose game mode:\n1) Classic Mode: Normal chess\n2) Random mode: A variant randomized chess. The initial position of pieces and promotions are random.",
                "Welcome to our chess app!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Random mode", "Classic mode"},
                "Restart");
        if (choice == JOptionPane.YES_OPTION) {
            board.Randomize();
            mode = "Random";
            board.setModeToRandom();
        } else if (choice == JOptionPane.NO_OPTION) {
            board.Classic();
            mode = "Classic";
        }
        // By Default, it is classic
        if (mode != "Classic" && mode != "Random"){
            board.Classic();
        }
        Draw draw = new Draw(board);
        frame.add(draw);
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        draw.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                // if resign button is clicked, end game
                if ((x > 960 && x < 1030 && y > 650 && y < 720)){
                    endGameLogic(1,record,frame);
                } else if ((x > 960 && x < 1030 && y > 80 && y < 150)){
                    endGameLogic(-1,record,frame);
                }
                // if hint button is clicked
                Possibilities bestMove = null;
                if ((x > 850 && x < 920 && y > 600 && y < 670)){
                    bestMove = brain.getMaxCount(board.getBoard(), turn);
                    draw.setBestMove(bestMove);
                } else if ((x > 850 && x < 920 && y > 80 && y < 150)){
                    bestMove = brain.getMaxCount(board.getBoard(), turn);
                    draw.setBestMove(bestMove);
                }

                if (userStatus != "Move" && userStatus != "BlackWon" && userStatus != "WhiteWon"){
                    select(board,draw,x,y);
                } else if (userStatus == "Move"){
                    move(board,draw,record,frame,x,y);
                } else if (userStatus == "WhiteWon"){
                    System.out.println("Congraturations, White!");
                } else if (userStatus == "BlackWon"){
                    System.out.println("Congraturations, Black!");
                }

                int goodness = brain.count(board.getBoard());
                draw.setGoodness(goodness);
            }
        }
        );
    }
    public static void select(Board board, Draw draw, int x, int y){
        int posX = (int)(Math.floor((x-80)/80));
        int posY = (int)(Math.floor((y-80)/80));
        int selectedColor = 0;
        try {
            selectedColor = board.board[posX][posY].getPiece().color;
        } catch(Exception e) {
        }
        if (turn == selectedColor){
            draw.setPieceToHighlight(posX,posY);
            Mouse mouse = new Mouse(posX,posY, board.getBoard(), draw);
            selectedSquare = new Pair(posX,posY);
            userStatus = "Move";
            if (board.board[selectedSquare.x][selectedSquare.y].getPiece()!=null){
                LinkedList<Pair> moves = board.board[selectedSquare.x][selectedSquare.y].getPiece().getMoves();
                Brain newBrain = new Brain(board);
                Board newBoard = newBrain.createArrayCopy(board.board);
                newBrain.visualize(newBoard.board);
                LinkedList<Integer> removeList = new LinkedList<>();
                for (int i = 0; i < moves.size(); i++){
                    System.out.println("Move to " + moves.get(i).x + " & " + moves.get(i).y);
                    Pair elm = moves.get(i);
                    Piece tempPiece = newBoard.board[selectedSquare.x][selectedSquare.y].getPiece();
                    boolean pieceTaken = false;
                    Piece tempPieceTaken = null;
                    if (newBoard.board[elm.x][elm.y].getPiece() != null){
                        String pieceName = newBoard.board[elm.x][elm.y].getPiece().toString();
                        int color = newBoard.board[elm.x][elm.y].getPiece().color;
                        Board tempBoard = newBoard;
                        pieceTaken = true;
                        if (pieceName == "Pawn"){
                            tempPieceTaken = new Pawn(new Pair(elm.x,elm.y),color,tempBoard);
                        } else if (pieceName == "King"){
                            tempPieceTaken = new King(new Pair(elm.x,elm.y),color,tempBoard);
                        } else if (pieceName == "Queen"){
                            tempPieceTaken = new Queen(new Pair(elm.x,elm.y),color,tempBoard);
                        } else if (pieceName == "Rook"){
                            tempPieceTaken = new Rook(new Pair(elm.x,elm.y),color,tempBoard);
                        } else if (pieceName == "Bishop"){
                            tempPieceTaken = new Bishop(new Pair(elm.x,elm.y),color,tempBoard);
                        } else if (pieceName == "Knight"){
                            tempPieceTaken = new Knight(new Pair(elm.x,elm.y),color,tempBoard);
                        }
                    }
                    newBoard.board[elm.x][elm.y].addPiece(tempPiece);
                    newBoard.board[selectedSquare.x][selectedSquare.y].addPiece(null);
                    System.out.println("Imaginary board after move");
                    newBrain.visualize(newBoard.board);
                    if (newBoard.inCheck(turn, newBoard)){
                        System.out.println("That move causes check");
                        removeList.add(i);
                    }
                    newBoard.board[selectedSquare.x][selectedSquare.y].addPiece(tempPiece);
                    newBoard.board[elm.x][elm.y].addPiece(null);
                    if (pieceTaken){
                        newBoard.board[elm.x][elm.y].addPiece(tempPieceTaken);
                    }
                }
                for (int i = removeList.size() - 1; i >= 0; i--) {
                    int toRemove = removeList.get(i);
                    moves.remove(toRemove);
                }
                draw.setMovesToHighlight(moves);
            }
            draw.repaint();
            System.out.println("End loop (1)");
        } else {
            System.out.println("It is not your piece");
            System.out.println("End loop (2)");
        }
    }

    public static void move(Board board, Draw draw, Record record, JFrame frame,int x,int y){
        boolean involveCatch = false;
        // if user status is move
        int posX = (int)(Math.floor((x-80)/80));
        int posY = (int)(Math.floor((y-80)/80));
        Mouse mouse = new Mouse(posX,posY, board.getBoard(), draw);
        int targetColor = 0;
        if (board.board[posX][posY].getPiece() != null){
            targetColor = board.board[posX][posY].getPiece().color;
            if (targetColor == turn){
                select(board,draw,x,y);
                return;
            }
        }
        if (turn==1 && board.board[selectedSquare.x][selectedSquare.y].getPiece()!=null){
            board.lastThreeWhitePieces.add(board.board[selectedSquare.x][selectedSquare.y].getPiece());
        }
        else if (turn==-1 && board.board[selectedSquare.x][selectedSquare.y].getPiece()!=null){
            board.lastThreeBlackPieces.add(board.board[selectedSquare.x][selectedSquare.y].getPiece());
        }
        if (board.board[selectedSquare.x][selectedSquare.y].getPiece().move(new Pair(posX,posY), 1)){
            Piece pieceToAdd = board.board[posX][posY].getPiece();
            if (pieceToAdd != null){
                if (pieceToAdd.color == 1){
                    draw.takenPieceBlack.add(pieceToAdd);
                    involveCatch = true;
                } else {
                    draw.takenPieceWhite.add(pieceToAdd);
                    involveCatch = true;
                }
            }
        }

        boolean canMove = false;

        // check if it is highlighted;
        if (draw.squareIsHilighted(new Pair(posX,posY))){
            canMove = board.board[selectedSquare.x][selectedSquare.y].getPiece().move(new Pair(posX,posY), 0);
        }
        if (canMove == true){
            System.out.println("this here: " + board.board[posX][posY].getPiece());
            userStatus = "Select";
            turn *= -1;
            draw.setBestMove(null);
            board.inCheck(turn,board);
            draw.setPieceToHighlight(-1,-1);
            draw.setMovesToHighlight(null);
            draw.repaint();
            System.out.println("End loop (3)");
            record.addRecord(board.board[posX][posY].getPiece(),turn,involveCatch,new Pair(selectedSquare.x, selectedSquare.y),new Pair(posX,posY),board.didLeftCastling,board.didRightCastling);
            board.didLeftCastling = false;
            board.didRightCastling = false;
        } else {
            if (isSuicide == false){
                userStatus = "Move";
            } else {
                userStatus = "Move";
            }
            System.out.println("End loop (5)");
        }

        // check Three fold
        if (turn==1){
            board.lastThreeWhiteMoves.add(new Pair(posX,posY));
            System.out.println("White move added");
        }
        else{
            board.lastThreeBlackMoves.add(new Pair(posX,posY));
            System.out.println("Black move added");
        }

        if(board.isThreeFoldRepetition()==true){
            System.out.println("Three folddddd");
            endGameLogic(0, record, frame);
        }

        if (board.checkGameEnd() == 1){
            endGameLogic(board.checkGameEnd(),record,frame);
        } else if (board.checkGameEnd() == -1){
            endGameLogic(board.checkGameEnd(),record,frame);
        }

        // at the end update promotion stuff
        board.updatePromotion();
        // check gameEnd
        if (board.checkGameEnd() == 1){
            endGameLogic(board.checkGameEnd(),record,frame);
        } else if (board.checkGameEnd() == -1){
            endGameLogic(board.checkGameEnd(),record,frame);
        };
    }
    public static void endGameLogic(int status, Record record, JFrame frame){
        if (status == 1){
            userStatus = "BlackWon";
            int choice = JOptionPane.showOptionDialog(frame,
                    "You lost! Do you want to download game record?",
                    "Message",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Download", "Exit"},
                    "Restart");
            if (choice == JOptionPane.YES_OPTION) {
                record.createFile();
                System.exit(0);
            } else if (choice == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        } else if (status == -1){
            userStatus = "WhiteWon";
            int choice = JOptionPane.showOptionDialog(frame,
                    "Congratulations, you won! Do you want to download the game record?",
                    "Message",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Download", "Exit"},
                    "Restart");

            if (choice == JOptionPane.YES_OPTION) {
                record.createFile();
                System.exit(0);
            } else if (choice == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        }
        //for draw
        else if (status==0){
            userStatus = "Draw";
            int choice = JOptionPane.showOptionDialog(frame,
                    "The game is a draw due to three fold repetition! Do you want to download the game record?",
                    "Message",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Download", "Exit"},
                    "Restart");

            if (choice == JOptionPane.YES_OPTION) {
                record.createFile();
                System.exit(0);
            } else if (choice == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        }
        // status =2
        else{
            userStatus = "Draw";
            int choice = JOptionPane.showOptionDialog(frame,
                    "The game is a draw due to stalemate! Do you want to download the game record?",
                    "Message",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Download", "Exit"},
                    "Restart");

            if (choice == JOptionPane.YES_OPTION) {
                record.createFile();
                System.exit(0);
            } else if (choice == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        }
    }
}