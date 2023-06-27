public class Knight extends Piece{
    boolean moved = false;
    public Knight(Pair position, int color, Board b){
        super(position, color, b);
    }
    public String toString(){
        return "Knight";
    }
    @Override
    public boolean move(Pair p, int i){
        if (this.color ==1){
            if (b.inCheckWhite && !b.checkRemover(this, p, this.color)&& i==0){
                System.out.println("Currently the position is in check and this move does not remove check");
                return false;
            }
        }
        else if (this.color ==-1){
            if (b.inCheckBlack && !b.checkRemover(this, p, this.color)&& i==0){
                System.out.println("Currently the position is in check and this move does not remove check");
                return false;
            }
        }
        // Check if we have same colored piece at the end square
        if (b.board[p.x][p.y].isOccupied() && (b.board[p.x][p.y].getPiece().color==color)){
            if (i==0){invalidMove(p);}
            return false;
        }
        // Check if the move is legal for a knight
        if (!isLegal(p)){
            if (i==0){invalidMove(p);}
            return false;
        }
        else{
            if (i==0){MOVE(p);}
            return true;
        }
    }
    public boolean isLegal(Pair p){
        if ((Math.abs(position.x-p.x)==1&&(Math.abs(position.y-p.y)==2))||((Math.abs(position.x-p.x)==2)&&(Math.abs(position.y-p.y)==1))){
            return true;
        }
        return false;
    }
    public Piece clone(){
        return new Knight(this.position, this.color, this.b);
    }
}
