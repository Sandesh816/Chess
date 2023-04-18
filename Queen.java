public class Queen extends Piece implements LinearMovable, DiagonalMovable{
    public Queen(Pair position, int color,Board b){
        super(position, color, b);
    }
    public String toString(){
        return "Queen";
    }
    @Override
    public void move(Pair p){
    if (canMoveLinear(this, p)||canMoveDiagonal(this, p)){this.position = p;}
    else invalidMove(p);
    }
}
