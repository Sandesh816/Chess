public class Queen extends Piece implements LinearMovable, DiagonalMovable{
    boolean moved = false;
    public Queen(Pair position, int color,Board b){
        super(position, color, b);
    }
    public String toString(){
        return "Queen";
    }
    @Override
    public boolean move(Pair p, int i){
        if (this.color == 1){
            if (b.inCheckWhite && !b.checkRemover(this, p, this.color)&& i==0){
                System.out.println("Currently the position is in check and this move does not remove check");
                return false;
            }
        }
        else if (this.color == -1){
            if (b.inCheckBlack && !b.checkRemover(this, p, this.color)&& i==0){
                System.out.println("Currently the position is in check and this move does not remove check");
                return false;
            }
        }
        if (canMoveLinear(this, p)||canMoveDiagonal(this, p)){
            if (i==0){MOVE(p);}
            return true;
        } else {
            if (i==0){invalidMove(p);}
            return false;
        }
    }
    public Piece clone(){
        return new Queen(this.position, this.color, this.b);
    }
}
