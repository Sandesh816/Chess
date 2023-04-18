public class Square {
    // Kind of like a node, each square holds a piece
    Piece piece;

    public Square(){
        // Default piece is null
        this.piece = null;
    }
    public boolean isOccupied(){
        return piece!=null;
    }
    public Piece getPiece(){
        return piece;
    }
    public void addPiece(Piece P){
        if (isOccupied()){
            removePiece();
        }
        this.piece= P;
    }
    public void removePiece(){
        System.out.println("The "+ piece+ " has been eliminated.");
        this.piece = null;
    }
}

