public class Rook extends Piece implements LinearMovable{
    public Rook(Pair position, int color,Board b){
        super(position, color, b);
    }
    public String toString(){
        return "Rook";
    }
    @Override
    public void move(Pair p){
        if (canMoveLinear(this, p)){this.position = p;}
        else invalidMove(p);
    }
}
