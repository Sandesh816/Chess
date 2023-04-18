public class Knight extends Piece{
    public Knight(Pair position, int color, Board b){
        super(position, color, b);
    }
    public String toString(){
        return "Knight";
    }
    @Override
    public void move(Pair p){
        // Check if we have same colored piece at the end square
        if (b.board[p.x][p.y].isOccupied() && (b.board[p.x][p.y].getPiece().color==color)){invalidMove(p);return;}
        // Check if the move is legal for a knight
        if (!isLegal(p)){invalidMove(p);}
        else{
            this.position = p;
        }
    }
    public boolean isLegal(Pair p){
        if ((Math.abs(position.x-p.x)==1&&(Math.abs(position.y-p.y)==2))||((Math.abs(position.x-p.x)==2)&&(Math.abs(position.y-p.y)==1))){
            return true;
        }
        return false;
    }
}
