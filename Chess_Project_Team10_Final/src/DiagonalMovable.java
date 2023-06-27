public interface DiagonalMovable {
    public default boolean isDiagonal(Piece P, Pair p){
        return (Math.abs(P.position.x-p.x)==Math.abs(P.position.y - p.y));
    }
    public default boolean canMoveDiagonal(Piece P, Pair p) {
        if (!isDiagonal(P, p)) {
            // System.out.println("This is not a diagonal");
            return false;
        }
        // Check if we have same colored piece at the end square
        if (P.b.board[p.x][p.y].isOccupied() && (P.b.board[p.x][p.y].getPiece().color==P.color)){
            return false;}
        // Now checking if there are any pieces blocking the squares
        int startX, endX, startY, yi;
        // We will always move from left to right
        startX = Math.min(P.position.x, p.x)+1; endX=Math.max(P.position.x, p.x);
        // if moving from top left to bottom right or vice versa
        if ((P.position.x<p.x && P.position.y<p.y)||(P.position.x>p.x && P.position.y>p.y)){
            startY = Math.min(P.position.y, p.y)+1;yi=1;
            // System.out.println("Considered moving from top left to bottom right or vice versa. So, startY is min"+startY);
        }
        // if moving from top right to bottom left or vice versa
        else{
            startY = Math.max(P.position.y, p.y)-1;yi=-1;
            //System.out.println("Considered moving from top right to bottom left or vice versa. So, startY is max"+ startY);
        }
        for (int i = startX, j = startY; i<endX; i++, j+=yi){
            //System.out.println("checking "+ i+" "+ j);
            if (P.b.board[i][j].isOccupied()){
                //System.out.println(i+" "+ j+" is occupied by "+ P.b.board[i][j].getPiece());
                return false;}
        }
//        P.b.board[P.position.x][P.position.y].removePiece();
//        P.position = p;
//        P.b.board[p.x][p.y].addPiece(P);
        return true;
    }
}
