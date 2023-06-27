import java.util.ArrayList;
import java.util.LinkedList;

public class Board {
    String mode = "";
    Square [][] board = new Square[8][8];
    ArrayList<Piece> whitePieces = new ArrayList<>();
    ArrayList<Piece> blackPieces = new ArrayList<>();
    boolean pottentialEmpassantWhite = false;
    boolean pottentialEmpassantBlack = false;
    Pair empassantLocation;

    boolean didLeftCastling;
    boolean didRightCastling;
    King whiteKing;
    King blackKing;
    boolean inCheckBlack = false;
    boolean inCheckWhite = false;
    ArrayList<Pair> lastThreeWhiteMoves = new ArrayList<>();
    ArrayList<Piece> lastThreeWhitePieces = new ArrayList<>();
    ArrayList<Pair> lastThreeBlackMoves = new ArrayList<>();
    ArrayList<Piece> lastThreeBlackPieces = new ArrayList<>();
    public void setModeToRandom(){
        mode = "Random";
    }

    public void setPottentialEmpassant(int color){
        if (color == -1){
            this.pottentialEmpassantWhite = true;
        } else {
            this.pottentialEmpassantBlack = true;
        }
    }

    public void setEmpassantLocation(Pair p){
        empassantLocation = p;
    }

    public Board(boolean b){
        // Setting up squares so that they can hold pieces
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                board[i][j] = new Square();
            }
        }
        if (b==true){
            // We set up the pawns and kings
            // Setting up white pawns
            for (int i=0; i<8; i++){
                Piece p = new Pawn(new Pair(i,1),1, this);
                board[i][1].addPiece(p);
                whitePieces.add(p);
            }
            // Setting up black pawns
            for (int i=0; i<8; i++){
                Piece p = new Pawn(new Pair(i,6),-1, this);
                board[i][6].addPiece(p);
                blackPieces.add(p);
            }
            // Setting up kings
            this.whiteKing = new King(new Pair(4,0),1, this);
            this.blackKing = new King(new Pair(4,7), -1, this);
            board[4][0].addPiece(whiteKing);
            board[4][7].addPiece(blackKing);
            whitePieces.add(whiteKing);
            blackPieces.add(blackKing);
        }
    }

    public String toString(){
        String output="";
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                output+=board[j][i].getPiece();
            }
        }
        return output;
    }

    public boolean inCheck(int color, Board board){
        Pair kingPos = new Pair (-1,-1);
        System.out.println("e");
        ArrayList<Piece> pieces = new ArrayList<>();
        if (color==1){
            for (int i = 0; i < 8; i ++){
                for (int j = 0; j < 8; j ++){
                    if (board.board[i][j].getPiece()!= null && board.board[i][j].getPiece().color == 1 && board.board[i][j].getPiece().toString() == "King"){
                        kingPos = new Pair(i,j);
                    }
                }
            }
            System.out.println("color is 1");
            pieces = blackPieces;
        }
        else{
            for (int i = 0; i < 8; i ++){
                for (int j = 0; j < 8; j ++){
                    if (board.board[i][j].getPiece()!= null && board.board[i][j].getPiece().color == -1 && board.board[i][j].getPiece().toString() == "King"){
                        kingPos = new Pair(i,j);
                    }
                }
            }
            pieces = whitePieces;
            System.out.println("color is -1");
        }
        LinkedList<Piece> piecesNew = new LinkedList<>();
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j ++){
                if (board.board[i][j].getPiece() != null){
                    if (board.board[i][j].getPiece().color == color * -1){
                        piecesNew.add(board.board[i][j].getPiece());
                    }
                }
            }
        }
        for (Piece p: piecesNew){
            System.out.println("Checking :" + p.toString() + "at " + p.position.x + " & " + p.position.y + " if it can go to " + kingPos.x + " & " + kingPos.y);
            if (p.move(kingPos, 1)){
                System.out.println("CHECKKKKKK");
                if (color == 1){
                    inCheckWhite = true;
                } else if (color == -1){
                    inCheckBlack = true;
                }
                return true;
             }
        }
        System.out.println("Currently the position is not in check");
        return false;
    }

    public boolean checkRemover(Piece P, Pair p, int a) {
        if (a ==1 && !inCheckWhite){
            System.out.println("Currently white king is not in check, so checkRemover inactive");
            return true;
        }
        else if (a ==-1 && !inCheckBlack){
            System.out.println("Currently black king is not in check, so checkRemover inactive");
            return true;
        }
        Board imaginaryBoard = new Board(false);
        if (a ==1){
            imaginaryBoard.inCheckWhite = true;}
        else if (a ==-1){
            imaginaryBoard.inCheckBlack = true;}
        Piece tmpP = P.clone();
        tmpP.changeBoard(imaginaryBoard);
        imaginaryBoard.whitePieces = new ArrayList<>(this.whitePieces);
        imaginaryBoard.blackPieces = new ArrayList<>(this.blackPieces);

        // Adding pieces to the temporary board
        for (Piece tmp : imaginaryBoard.whitePieces) {
            if (tmp.equals(tmpP)){continue;}
            if (tmp.equals(whiteKing)){
                imaginaryBoard.whiteKing = (King) tmp;}
            imaginaryBoard.board[tmp.getPosition().x][tmp.getPosition().y].addPiece(tmp);
        }
        for (Piece tmp : imaginaryBoard.blackPieces) {
            if (tmp.equals(tmpP)){continue;}
            if (tmp.equals(blackKing)){
                imaginaryBoard.blackKing = (King) tmp;}
            imaginaryBoard.board[tmp.getPosition().x][tmp.getPosition().y].addPiece(tmp);
        }
        // Making the intended move on temporary board and checking if the check is removed
        imaginaryBoard.board[p.x][p.y].addPiece(tmpP);
        if (a ==1){
            if (!imaginaryBoard.inCheck(a,imaginaryBoard)){
                System.out.println("Now the check is removed when " + tmpP + " is moved to " + p.x+ " "+ p.y);
                this.inCheckWhite = false;
                return true;
            }
        }
        else if (a ==1){
            if (!imaginaryBoard.inCheck(a,imaginaryBoard)){
                System.out.println("Now the check is removed when " + tmpP + " is moved to " + p);
                this.inCheckBlack = false;
                return true;
            }
        }
        System.out.println("Check is not removed when " + tmpP + " is moved to " + p);
        System.out.println("----------------");
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (imaginaryBoard.board[j][i].getPiece() == null){
                    System.out.print("□");
                } else {
                    if (imaginaryBoard.board[j][i].getPiece().toString() == "King"){
                        System.out.print("K");
                    } else if (imaginaryBoard.board[j][i].getPiece().toString() == "Queen"){
                        System.out.print("Q");
                    } else if (imaginaryBoard.board[j][i].getPiece().toString() == "Rook"){
                        System.out.print("R");
                    } else if (imaginaryBoard.board[j][i].getPiece().toString() == "Bishop"){
                        System.out.print("B");
                    } else if (imaginaryBoard.board[j][i].getPiece().toString() == "Pawn"){
                        System.out.print("P");
                    } else {
                        System.out.print("N");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("----------------");
        return false;
    }

    public LinkedList<Pair> getAllPossibleMoves(int turn){
        LinkedList<Pair> listToReturn = new LinkedList<>();
        for (int i = 0; i < 8; i ++){
            for (int j = 0; j < 8; j ++){
                if (this.board[i][j].getPiece()!=null && this.board[i][j].getPiece().toString()!="King"){
                    if (this.board[i][j].getPiece().color != turn){
                        LinkedList<Pair> listTemp = this.board[i][j].getPiece().getMoves();
                        for (Pair elm : listTemp){
                            listToReturn.add(elm);
                        }
                    }
                }
            }
        }
        return listToReturn;
    }

    //check if the king is stalemated
    public boolean isStalemated(int color){
        if (inCheck(color,this)){return false;}
        for (int row = 0; row < 8; row ++) {
            for (int column = 0; column < 8; column++) {
                Square b = board[row][column];
                //for every white piece check if they can move to every piece on the board
                if(b.piece != null && b.piece.color == color){
                    LinkedList<Pair> moves = b.piece.getMoves();
                    if(moves.size()!=0){return false;}
                }
            }
        }
        System.out.println("STALEMATE!!!!!DRAW");
        return true;
    }

    //check if three-fold repetition
    public boolean isThreeFoldRepetition(){
        if (lastThreeWhiteMoves.size()<6||lastThreeBlackMoves.size()<6){
            System.out.println("Not even 6 moves yet");
            return false;
        }
        if (lastThreeWhitePieces.get(lastThreeWhitePieces.size()-1).equals(lastThreeWhitePieces.get(lastThreeWhitePieces.size()-2))&&lastThreeWhitePieces.get(lastThreeWhitePieces.size()-2).equals(lastThreeWhitePieces.get(lastThreeWhitePieces.size()-3))){
            if (lastThreeBlackPieces.get(lastThreeBlackPieces.size()-1).equals(lastThreeBlackPieces.get(lastThreeBlackPieces.size()-2))&&lastThreeBlackPieces.get(lastThreeBlackPieces.size()-2).equals(lastThreeBlackPieces.get(lastThreeBlackPieces.size()-3))){
                if (lastThreeWhiteMoves.get(lastThreeWhiteMoves.size()-1).equals(lastThreeWhiteMoves.get(lastThreeWhiteMoves.size()-3))&&lastThreeWhiteMoves.get(lastThreeWhiteMoves.size()-3).equals(lastThreeWhiteMoves.get(lastThreeWhiteMoves.size()-5))){
                    if(lastThreeBlackMoves.get(lastThreeBlackMoves.size()-1).equals(lastThreeBlackMoves.get(lastThreeBlackMoves.size()-3))&&lastThreeBlackMoves.get(lastThreeBlackMoves.size()-3).equals(lastThreeBlackMoves.get(lastThreeBlackMoves.size()-5))){
                        System.out.println("Same moves");
                        return true;
                    }
                }
                System.out.println("Same moves");
                return true;
            }
        }
        if (lastThreeWhiteMoves.get(lastThreeWhiteMoves.size()-1).equals(lastThreeWhiteMoves.get(lastThreeBlackMoves.size()-2))&&lastThreeWhiteMoves.get(lastThreeWhiteMoves.size()-2).equals(lastThreeWhiteMoves.get(lastThreeWhiteMoves.size()-3))){
            if (lastThreeBlackMoves.get(lastThreeBlackMoves.size()-1).equals(lastThreeBlackMoves.get(lastThreeBlackMoves.size()-2))&&lastThreeBlackMoves.get(lastThreeBlackMoves.size()-2).equals(lastThreeBlackMoves.get(lastThreeBlackMoves.size()-3))){
                System.out.println("Same moves");
                return true;
            }
        }
        System.out.println("Not same moves");
        return false;}


    public boolean enPassant(Pawn P1, Pawn P2){if(!P1.moved && P1.position.y == 1){
            Pair p1 = new Pair(P1.position.x, P1.position.y + 2);
            // if P1 is moved 2 pos forward for the same time, and it is right next to  P2
            if (P1.move(p1,1) && P2.position.y == P1.position.y && P2.position.x +1 == P1.position.x && P2.position.x +1 == P1.position.x){
                return true;
            }
        }
        if(!P1.moved && P1.position.y == 6){
            Pair p2 = new Pair(P1.position.x, P1.position.y - 2);
            // if P1 is moved 2 pos forward for the same time, and it is right next to  P2
            if (P1.move(p2,1) && P2.position.y == P1.position.y && P2.position.x +1 == P1.position.x && P2.position.x +1 == P1.position.x){
                return true;
            }
        }
        return false;
    }
    public Square[][] getBoard(){
        return this.board;
    }

    public void Randomize(){
        // We randomize the rest of the pieces in random places at the start of the game
        // First, let's randomize white pieces
        Pair p;
        Piece piece;
        p = getRandomPair(8, 4, 0);
        piece = new Queen(p, 1, this);
        whitePieces.add(piece);
        board[p.x][p.y].addPiece(piece);

        p = getRandomPair(8, 4, 0);
        piece = new Rook(p, 1, this);
        whitePieces.add(piece);
        board[p.x][p.y].addPiece(piece);

        p = getRandomPair(8, 4, 0);
        piece = new Rook(p, 1, this);
        whitePieces.add(piece);
        board[p.x][p.y].addPiece(piece);

        p = getRandomPair(8, 4, 0);
        piece = new Knight(p, 1, this);
        whitePieces.add(piece);
        board[p.x][p.y].addPiece(piece);

        p = getRandomPair(8, 4, 0);
        piece = new Knight(p, 1, this);
        whitePieces.add(piece);
        board[p.x][p.y].addPiece(piece);

        p = getRandomPair(8, 4, 0);
        piece = new Bishop(p, 1, this);
        whitePieces.add(piece);
        board[p.x][p.y].addPiece(piece);

        p = getRandomPair(8, 4, 0);
        piece = new Bishop(p, 1, this);
        whitePieces.add(piece);
        board[p.x][p.y].addPiece(piece);
//        Pair p = getRandomPair(8, 4,0);
//        board[p.x][p.y].addPiece(new Queen(p, 1,this));
//        p = getRandomPair(8, 4,0);
//        board[p.x][p.y].addPiece(new Rook(p, 1,this));
//        p = getRandomPair(8, 4,0);
//        board[p.x][p.y].addPiece(new Rook(p, 1,this));
//        p = getRandomPair(8, 4,0);
//        board[p.x][p.y].addPiece(new Knight(p, 1,this));
//        p = getRandomPair(8, 4,0);
//        board[p.x][p.y].addPiece(new Knight(p, 1,this));
//        p = getRandomPair(8, 4,0);
//        board[p.x][p.y].addPiece(new Bishop(p, 1,this));
//        p = getRandomPair(8, 4,0);
//        board[p.x][p.y].addPiece(new Bishop(p, 1,this));

        // Now let's randomize black pieces
        p = getRandomPair(8, 4, 4);
        piece = new Queen(p, -1, this);
        blackPieces.add(piece);
        board[p.x][p.y].addPiece(piece);

        p = getRandomPair(8, 4, 4);
        piece = new Rook(p, -1, this);
        blackPieces.add(piece);
        board[p.x][p.y].addPiece(piece);

        p = getRandomPair(8, 4, 4);
        piece = new Rook(p, -1, this);
        blackPieces.add(piece);
        board[p.x][p.y].addPiece(piece);

        p = getRandomPair(8, 4, 4);
        piece = new Knight(p, -1, this);
        blackPieces.add(piece);
        board[p.x][p.y].addPiece(piece);

        p = getRandomPair(8, 4, 4);
        piece = new Knight(p, -1, this);
        blackPieces.add(piece);
        board[p.x][p.y].addPiece(piece);

        p = getRandomPair(8, 4, 4);
        piece = new Bishop(p, -1, this);
        blackPieces.add(piece);
        board[p.x][p.y].addPiece(piece);

        p = getRandomPair(8, 4, 4);
        piece = new Bishop(p, -1, this);
        blackPieces.add(piece);
        board[p.x][p.y].addPiece(piece);
    }
    public void Classic(){
        // First, let's set up the black pieces
        Piece p = new Rook(new Pair(0, 7), -1, this);
        blackPieces.add(p);
        board[0][7].addPiece(p);

        p = new Knight(new Pair(1, 7), -1, this);
        blackPieces.add(p);
        board[1][7].addPiece(p);

        p = new Bishop(new Pair(2, 7), -1, this);
        blackPieces.add(p);
        board[2][7].addPiece(p);

        p = new Queen(new Pair(3, 7), -1, this);
        blackPieces.add(p);
        board[3][7].addPiece(p);

        p = new Bishop(new Pair(5, 7), -1, this);
        blackPieces.add(p);
        board[5][7].addPiece(p);

        p = new Knight(new Pair(6, 7), -1, this);
        blackPieces.add(p);
        board[6][7].addPiece(p);

        p = new Rook(new Pair(7, 7), -1, this);
        blackPieces.add(p);
        board[7][7].addPiece(p);

// Now, let's set up the white pieces
        p = new Rook(new Pair(0, 0), 1, this);
        whitePieces.add(p);
        board[0][0].addPiece(p);

        p = new Knight(new Pair(1, 0), 1, this);
        whitePieces.add(p);
        board[1][0].addPiece(p);

        p = new Bishop(new Pair(2, 0), 1, this);
        whitePieces.add(p);
        board[2][0].addPiece(p);

        p = new Queen(new Pair(3, 0), 1, this);
        whitePieces.add(p);
        board[3][0].addPiece(p);

        p = new Bishop(new Pair(5, 0), 1, this);
        whitePieces.add(p);
        board[5][0].addPiece(p);

        p = new Knight(new Pair(6, 0), 1, this);
        whitePieces.add(p);
        board[6][0].addPiece(p);

        p = new Rook(new Pair(7, 0), 1, this);
        whitePieces.add(p);
        board[7][0].addPiece(p);
    }

    public Pair getRandomPair(int x, int y, int z){
        while (true){
            int a = (int)(Math.random()*x);
            int b = z+(int)(Math.random()*y);
            if (!board[a][b].isOccupied()){
                return new Pair(a,b);
            }
        }
    }

    public void updatePromotion(){
        for (int i = 0; i < 8; i ++){
            for (int j = 0; j < 8; j ++){
                if (board[i][j].getPiece() != null){
                    if (board[i][j].getPiece().toString() == "Pawn" && board[i][j].getPiece().color == -1 && j == 0){
                        board[i][j].removePiece();
                        board[i][j].addPiece(new Queen(new Pair(i,j),-1,this));
                    }
                    if (board[i][j].getPiece().toString() == "Pawn" && board[i][j].getPiece().color == 1 && j == 7){
                        board[i][j].removePiece();
                        board[i][j].addPiece(new Queen(new Pair(i,j),1,this));                    }
                }
            }
        }
    }

    public int checkGameEnd(){
        boolean whiteKing = false;
        boolean blackKing = false;
        for (int i = 0; i < 8; i ++){
            for (int j = 0; j < 8; j ++){
                if (board[i][j].getPiece() != null){
                    if (board[i][j].getPiece().toString() == "King" && board[i][j].getPiece().color == -1){
                        whiteKing = true;
                    }
                    if (board[i][j].getPiece().toString() == "King" && board[i][j].getPiece().color == 1){
                        blackKing = true;
                    }
                }
            }
        }
        if (whiteKing == false){
            return 1;
        } else if (blackKing == false){
            return -1;
        }
        return 0;
    }

    public boolean isCheck(int turn, Board board){
        Pair kingPos = new Pair(-1,-1);
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8 ; j++){
                if (board.board[i][j].getPiece() != null){
                    if (board.board[i][j].getPiece().color == turn && board.board[i][j].toString() == "King"){
                        kingPos.x = i;
                        kingPos.y = j;
                    }
                }
            }
        }
        LinkedList<Pair> moveList = new LinkedList<>();
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (board.board[i][j].getPiece() != null){
                    if (board.board[i][j].getPiece().color != turn){
                        for (Pair elm : this.board[i][j].getPiece().getMoves()){
                            moveList.add(elm);
                        }
                    }
                }
            }
        }
        for (Pair elm : moveList){
            if (kingPos.x == elm.x && kingPos.y == elm.y){
                return true;
            }
        }
        return false;
    }
}
