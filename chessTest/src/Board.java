public class Board {
    Piece[][] board;

    int turn;

    public Board(Piece[][] boardInput){
        this.board = new Piece[8][8];
        for (int i = 0; i < 8 ; i++){
            for (int j = 0; j < 8; j ++){
                this.board[i][j] = boardInput[i][j];
            }
        }
    }

    public Board() {
        this.board = new Piece[8][8];
        // initialize white pieces
        board[0][0] = new Rook(0, 0, 0);
        board[1][0] = new Knight(0, 1, 0);
        board[2][0] = new Bishop(0, 2, 0);
        board[3][0] = new Queen(0, 3, 0);
        board[4][0] = new King(0, 4, 0);
        board[5][0] = new Bishop(0, 5, 0);
        board[6][0] = new Knight(0, 6, 0);
        board[7][0] = new Rook(0, 7, 0);
        for (int i = 0; i < 8; i++) {
            board[i][1] = new Pawn(0,i,1);
        }

        // initialize black pieces
        board[0][7] = new Rook(1, 0, 7);
        board[1][7] = new Knight(1, 1, 7);
        board[2][7] = new Bishop(1, 2, 7);
        board[3][7] = new Queen(1, 3, 7);
        board[4][7] = new King(1, 4, 7);
        board[5][7] = new Bishop(1, 5, 7);
        board[6][7] = new Knight(1, 6, 7);
        board[7][7] = new Rook(1, 7, 7);
        for (int i = 0; i < 8; i++) {
            board[i][6] = new Pawn(1,i,6);
        }

        // initialize empty cells
        for (int i = 0; i < 8; i++) {
            for (int j = 2; j < 6; j++) {
                board[i][j] = new Nothing(-1,i,j);
            }
        }

        turn = 1;
    }

    public Piece[][] getBoard(){
        return this.board;
    }

    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    public void setPiece(int x, int y, Piece piece) {
        piece.update(x,y);
        board[x][y] = piece;
    }

    public int[][] canMove(Piece piece) {
        int x = piece.getX();
        int y = piece.getY();
        // Printout information
        String name = piece.toString();
        System.out.println("--- the piece (" + name +") is initially located at [" + x +  "," + y + "]");
        int[][] moves = piece.getMove();
        //System.out.println("Without any constriants, it can go to " + moves.length + " cells");
        // Printout end
        int[][] considered = new int[moves.length][2];
        int numConsidered = 0;
        //System.out.println("---Calcluate where it can go if there is a border constraint");
        for (int i = 0; i < moves.length; i++){
            int newX = x + moves[i][0];
            int newY = y + moves[i][1];
            //System.out.println("------ Considering [" + newX + "," + newY + "]");
            if (newX >= 0 && newX < 8) {
                if (newY >= 0 && newY < 8){
                    considered[numConsidered][0] = newX;
                    considered[numConsidered][1] = newY;
                    //System.out.println("--------- added " + considered[numConsidered][0] + " to the considered list");
                    //System.out.println("--------- added " + considered[numConsidered][1] + " to the considered list");
                    numConsidered ++;
                    //System.out.println("--------- it can go to [" + newX + "," + newY + "]");
                }
            }
        }
        int[][] legalMoves = new int[numConsidered][2];
        //System.out.println("By considering boundaries, it can go to " + numConsidered + " cells. They are: ");
        for (int i = 0; i < numConsidered; i++){
            // System.out.println("---" + considered[i][0] + " & " + considered[i][1]);
        }
        int numLegalMoves = 0;
        //System.out.println("Now, let's check if the color matches or not");
        for (int i = 0; i < numConsidered; i++){
            int newX = considered[i][0];
            int newY = considered[i][1];
            // each newX and newY considered moves
            int color = getPiece(newX,newY).getColor();
            if (color != getPiece(x,y).getColor()){
                if (piece.toString() == "Knight"){
                    legalMoves[numLegalMoves][0] = newX;
                    legalMoves[numLegalMoves][1] = newY;
                    numLegalMoves ++;
                } else {
                    legalMoves[numLegalMoves][0] = newX;
                    legalMoves[numLegalMoves][1] = newY;
                    numLegalMoves ++;
                }
                //System.out.println("---Checking the color of [" + newX + "," + newY + "]: different color");
            } else {
                //System.out.println("---Checking the color of [" + newX + "," + newY + "]: same color");
            }
        }
        //System.out.println("By considering overlaps with my other pieces, it can go to " + numLegalMoves + " cells");
        int[][] legalMovesFinal = new int[numLegalMoves][2];
        for (int i = 0; i < numLegalMoves; i++){
            legalMovesFinal[i][0] = legalMoves[i][0];
            legalMovesFinal[i][1] = legalMoves[i][1];
            //System.out.println("---it can go to [" + legalMovesFinal[i][0] + "," + legalMovesFinal[i][1] + "]");
        }
        if (legalMovesFinal.length == 0){
            //System.out.println("---Nowhere to go!");
        }
        return legalMovesFinal;
    }

    public boolean checkLegal(int initX, int initY, int newX, int newY){
        Piece piece = getPiece(initX,initY);
        int[][] legalMoves = canMove(piece);
        if (piece.getColor() == turn){
            for (int i = 0; i < legalMoves.length; i ++){
                int legalX = legalMoves[i][0];
                int legalY = legalMoves[i][1];
                if (legalX == newX && legalY == newY){
                    return true;
                }
            }
            return false;
        } else if (piece.getColor() == -1){
            System.out.println("There is nothing there!");
            return false;
        } else {
            return false;
        }
    }

    public void move(int initX, int initY, int newX, int newY){
        Piece piece = getPiece(initX, initY);
        boolean legality = checkLegal(initX, initY, newX, newY);
        if (legality != true){
            System.out.print("Not allowed");
        } else {
            setPiece(newX, newY, piece);
            Nothing nothing = new Nothing(-1,initX,initY);
            setPiece(initX, initY, nothing);
            System.out.println("Piece was moved to [" + newX + "," + newY + "]!");
            System.out.println("There is " + getPiece(initX, initY).toString() + " at [" + initX + "," + initY + "]!");
        }
        if (turn == 1){
            turn = 2;
        } else {
            turn = 1;
        }
    }

    public void eat(int initX, int initY, int newX, int newY){
        Piece myPiece = getPiece(initX, initY);
        Piece targetPiece = getPiece(newX, newY);
        if (targetPiece.color > 0 & targetPiece.color != myPiece.color){
            System.out.println("Succesfully eat the opponent's " + targetPiece.toString());
        }
    }

    public void setBoard(Piece[][] newBoard) {
        board = newBoard;
    }
}
