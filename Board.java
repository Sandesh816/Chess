public class Board {
    Square [][] board = new Square[8][8];

    public Board(){
        // Setting up squares so that they can hold pieces
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                board[i][j] = new Square();
            }
        }
        // We set up the pawns and kings
        // Setting up white pawns
        for (int i=0; i<8; i++){
            board[i][1].addPiece(new Pawn(new Pair(i,1),1, this));
        }
        // Setting up black pawns
        for (int i=0; i<8; i++){
            board[i][6].addPiece(new Pawn(new Pair(i,6),-1, this));
        }
        // Setting up kings
        King whiteKing = new King(new Pair(4,0),1, this);
        King blackKing = new King(new Pair(4,7), -1, this);
        board[4][0].addPiece(whiteKing);
        board[4][7].addPiece(blackKing);
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

    public void Randomize(){
        // We randomize the rest of the pieces in random places at the start of the game
        // First, let's randomize white pieces

    }
    public boolean inCheck(){
        return false;
    }
    //check if the king is checkmated
    public boolean isCheckmated(){return false;}

    //check if the king is stalemated
    public boolean isStalemated(){return  false;}

    //check if three-fold repetition
    public boolean isThreeFoldRepetition(){return false;}

    // check if the king has castling rights
    public boolean canCastle(){return true;}


    public void castle(King K, Rook R){
//        if (!canCastle()){K.invalidMove();R.invalidMove();}
    }
    public void enPassant(Pawn P1, Pawn P2){
        // I think we should place it inside the Board as it requires info abbout another pawn
    }
    public void pawnCapture(Pawn P1, Pawn P2){
        // I think we should place it inside the Board as it requires info abbout another pawn
    }
}
