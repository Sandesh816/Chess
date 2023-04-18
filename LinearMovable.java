public interface LinearMovable{
    public default Boolean isLinear(Piece P, Pair p){
        return (P.position.x==p.x || P.position.y == p.y);
    }
    public default boolean canMoveLinear(Piece P, Pair p){
        if (!isLinear(P, p)){return false;}
        // Check if we have same colored piece at the end square
        if (P.b.board[p.x][p.y].isOccupied() && (P.b.board[p.x][p.y].getPiece().color==P.color)){return false;}
        // Now checking if there are any pieces blocking the squares
        int start, end;
        // For horizontal movement
        if (P.position.y==p.y){
            start = Math.min(P.position.x, p.x)+1;
            end = Math.max(P.position.x, p.x);
            for (int i=start; i<end; i++){
                if (P.b.board[i][p.y].isOccupied()){return false;}
            }
        }
        // For vertical movement
        else {
            start = Math.min(P.position.y, p.y)+1;
            end = Math.max(P.position.y, p.y);
            for (int i=start; i<end; i++){
                if (P.b.board[p.x][i].isOccupied()){return false;}
            }
        }
        return true;
    }
}
