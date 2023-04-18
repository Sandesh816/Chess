public interface DiagonalMovable {
    public default boolean isDiagonal(Piece P, Pair p){
        return (Math.abs(P.position.x-p.x)==Math.abs(P.position.y - p.y));
    }
    public default boolean canMoveDiagonal(Piece P, Pair p) {
        if (!isDiagonal(P, p)) {return false;}
        // Check if we have same colored piece at the end square
        if (P.b.board[p.x][p.y].isOccupied() && (P.b.board[p.x][p.y].getPiece().color==P.color)){return false;}
        // Now checking if there are any pieces blocking the squares
        int startX, endX, startY, yi;
        startX = Math.min(P.position.x, p.x)+1; endX=Math.max(P.position.x, p.x);
        // if moving from bottomleft to top right or vice versa
        if ((P.position.x<p.x && P.position.y<p.y)||(P.position.x>p.x && P.position.y>p.y)){
            startY = Math.min(P.position.y, p.y);yi=1;
        }
        // if moving from top left to bottom right or vice versa
        else{
            startY = Math.max(P.position.y, p.y)-1;yi=-1;
        }
        for (int i = startX, j = startY; i<endX; i++, j+=yi){
            if (P.b.board[i][j].isOccupied()){return false;}
        }
        return true;
    }
}
