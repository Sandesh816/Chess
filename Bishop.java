public class Bishop extends Piece implements DiagonalMovable{
    public Bishop(Pair position, int color,Board b){
        super(position, color, b);
    }
    public String toString(){
        return "Bishop";
    }
    @Override
    public void move(Pair p){
        if (canMoveDiagonal(this, p)){this.position = p;}
        else invalidMove(p);
    }
}
