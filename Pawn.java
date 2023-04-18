public class Pawn extends Piece{
    private boolean moved = false;
    public Pawn(Pair position, int color, Board b){
        super(position, color, b);
    }
    public String toString(){
        return "Pawn";
    }
    @Override
    public void move(Pair p){
        // Check if we have same colored piece at the end square
        if (b.board[p.x][p.y].isOccupied() && (b.board[p.x][p.y].getPiece().color==color)){invalidMove(p);return;}
        // Check if the move is legal for a pawn
        if (!isLegal(p)){invalidMove(p);}
        else{
            this.position = p;
            moved = true;
        }
    }
    public boolean isLegal(Pair p){
        // Cannot move horizontally
        if (position.x!=p.x){return false;}
        // Cannot move baclwards
        if (p.y<= position.y){return false;}
        // Cannot move more than one square if already moved once
        if (moved && ((p.y- position.y)>1)){return false;}
        return true;
    }
}
