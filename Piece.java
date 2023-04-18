public abstract class Piece {
    Pair position;
    int color;
    Board b;

    public Piece (Pair p, int color, Board b){
        this.position = p;
        this.color = color;
    }
    public Pair getPosition(){
        return this.position;
    }
    public abstract void move(Pair p);
    public void invalidMove(Pair p){
        System.err.println("INVALID MOVE!!!\nPiece at "+position.x+","+position.y+" cannot move to " + p.x+","+p.y);
    }
}
