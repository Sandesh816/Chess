public class King extends Piece implements LinearMovable, DiagonalMovable{
    boolean moved = false;
    public King(Pair position, int color,Board b){
        super(position, color, b);
    }
    public String toString(){
        return "King";
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
        if (canLeftCastle() && !this.moved){
            if (this.color == -1 && p.x == 1 && p.y == 7){
                if (i==0){
                    moved = true;
                    MOVE(p);
                    // manually move the location of rook
                    b.board[0][7].addPiece(null);
                    b.board[2][7].addPiece(new Rook(new Pair(2,7),this.color,b));
                    b.didLeftCastling = true;
                }
                return true;
            } else if (this.color == 1 && p.x == 1 && p.y == 0){
                if (i==0){
                    moved = true;
                    MOVE(p);
                    // manually move the location of rook
                    Piece rookTemp = b.board[0][0].getPiece();
                    b.board[0][0].addPiece(null);
                    b.board[2][0].addPiece(new Rook(new Pair(2,0),this.color,b));
                    b.didLeftCastling = true;
                }
                return true;
            }
        }
        if (canRightCastle() && !this.moved){
            if (this.color == -1 && p.x == 6 && p.y == 7){
                if (i==0){
                    moved = true;
                    MOVE(p);
                    // manually move the location of rook
                    b.board[7][7].addPiece(null);
                    b.board[5][7].addPiece(new Rook(new Pair(5,7),this.color,b));
                    b.didRightCastling = true;
                }
                return true;
            } else if (this.color == 1 && p.x == 6 && p.y == 0){
                if (i==0){
                    moved = true;
                    MOVE(p);
                    // manually move the location of rook
                    Piece rookTemp = b.board[7][0].getPiece();
                    b.board[7][0].addPiece(null);
                    b.board[5][0].addPiece(new Rook(new Pair(5,0),this.color,b));
                    b.didRightCastling = true;
                }
                return true;
            }
        }
        if ((Math.abs(this.position.x-p.x)>1)||(Math.abs(this.position.y-p.y)>1)){
            if (i==0){invalidMove(p);}
            return false;
        }
        if (canMoveLinear(this, p)|| canMoveDiagonal(this, p)){
            if (i==0){
                moved = true;
                MOVE(p);}
            return true;
        } else {
            if (i==0){invalidMove(p);}
            return false;
        }
    }

    public boolean canLeftCastle(){
        if (this.color == -1){
            Piece leftRook = b.board[0][7].getPiece();
            if (leftRook == null){
                return false;
            }
            if (leftRook.toString() == "Rook" && leftRook.moved == false  && leftRook.color == this.color){
                if (!b.board[1][7].isOccupied()&&!b.board[2][7].isOccupied()&&!b.board[3][7].isOccupied()){
                    return true;
                }
            }
        } else if (this.color == 1){
            Piece leftRook = b.board[0][0].getPiece();
            if (leftRook == null){
                return false;
            }
            if (leftRook.toString() == "Rook" && leftRook.moved == false  && leftRook.color == this.color){
                if (!b.board[1][0].isOccupied()&&!b.board[2][0].isOccupied()&&!b.board[3][0].isOccupied()){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean canRightCastle(){
        if (this.color == -1){
            Piece rightRook = b.board[7][7].getPiece();
            if (rightRook == null){
                return false;
            }
            if (rightRook.toString() == "Rook" && rightRook.moved == false  && rightRook.color == this.color){
                if (!b.board[6][7].isOccupied()&&!b.board[5][7].isOccupied()){
                    return true;
                }
            }
        } else if (this.color == 1){
            Piece rightRook = b.board[7][0].getPiece();
            if (rightRook == null){
                return false;
            }
            if (rightRook.toString() == "Rook" && rightRook.moved == false  && rightRook.color == this.color){
                if (!b.board[6][0].isOccupied()&&!b.board[5][0].isOccupied()){
                    return true;
                }
            }
        }
        return false;
    }

    public Piece clone(){
        return new King(this.position, this.color, this.b);
    }
}
