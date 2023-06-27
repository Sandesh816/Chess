import java.util.LinkedList;

public abstract class Piece {
    Pair position;
    public boolean whiteEm = false;
    public boolean blackEm = false;
    boolean moved = false;
    int color;
    Board b;

    public Piece (Pair p, int color, Board b){
        this.position = p;
        this.color = color;
        this.b = b;
    }
    public Pair getPosition(){
        return this.position;
    }
    public abstract boolean move(Pair p, int i);
    public void invalidMove(Pair p){
        // System.err.println("INVALID MOVE!!!\nPiece at "+position.x+","+position.y+" cannot move to " + p.x+","+p.y);
    }
    public void MOVE(Pair p){
        b.board[position.x][position.y].removePiece();
        position = p;
        b.board[p.x][p.y].addPiece(this);
    }

    public LinkedList<Pair> getMoves(){
        LinkedList<Pair> moves = new LinkedList<>();
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                Pair tmp = new Pair(i,j);
                if (this.move(tmp, 1)){
                    moves.add(tmp);
                }
            }
        }
        return moves;
    }

    public void changeBoard(Board newBoard){
        this.b = newBoard;
    }
    public abstract Piece clone();

    public Board createArrayCopy(Square[][] originalBoard){
        Board newBoard = new Board(true);
        Square[][] boardToReturn = new Square[8][8];
        newBoard.board = boardToReturn;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j ++){
                boardToReturn[i][j] = new Square();
                Square originalSquare = originalBoard[i][j];
                if (originalSquare != null && originalSquare.getPiece() != null){
                    String originalPieceInString = originalSquare.getPiece().toString();
                    Pair pos = new Pair(i,j);
                    int color = originalSquare.getPiece().color;
                    Piece newPiece = null;
                    if (originalPieceInString.equals("Queen")){
                        newPiece = new Queen(pos,color,newBoard);
                    } else if (originalPieceInString.equals("King")){
                        newPiece = new King(pos,color,newBoard);
                    } else if (originalPieceInString.equals("Rook")){
                        newPiece = new Rook(pos,color,newBoard);
                    } else if (originalPieceInString.equals("Bishop")){
                        newPiece = new Bishop(pos,color,newBoard);
                    } else if (originalPieceInString.equals("Knight")){
                        newPiece = new Knight(pos,color,newBoard);
                    } else if (originalPieceInString.equals("Pawn")){
                        newPiece = new Pawn(pos,color,newBoard);
                    }
                    boardToReturn[i][j].addPiece(newPiece);
                }
            }
        }
        return newBoard;
    }
}
