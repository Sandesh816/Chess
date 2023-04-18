public class King extends Piece implements LinearMovable, DiagonalMovable{
    public King(Pair position, int color,Board b){
        super(position, color, b);
    }
    public String toString(){
        return "King";
    }
    @Override
    public void move(Pair p){
        if ((Math.abs(this.position.x-p.x)>1)||(Math.abs(this.position.y-p.y)>1)){invalidMove(p);return;}
        if (canMoveLinear(this, p)|| canMoveDiagonal(this, p)){this.position = p;b.board[p.x][p.y].addPiece(this);}
    }
    }
