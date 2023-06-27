import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class Pawn extends Piece{
    public boolean moved = false;
    public Pawn(Pair position, int color, Board b){
        super(position, color, b);
    }
    public String toString(){
        return "Pawn";
    }
    @Override
    public boolean move(Pair p, int i){
        // Check if we have a piece at the end square
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
        if (this.color == 1){
            Pair topRight = new Pair (position.x + 1,position.y + 1);
            Pair topLeft = new Pair (position.x - 1,position.y+1);
            if (topRight.x <= 7 && topRight.y <= 7 && topRight.x >= 0 && topRight.y >= 0){
                if (b.board[topRight.x][topRight.y].getPiece() != null){
                    if (b.board[topRight.x][topRight.y].getPiece().color == color * -1){
                        if (p.x == topRight.x && p.y == topRight.y){
                            if (i==0){
                                this.moved = true;
                                MOVE(p);
                                promotion(p);
                            }
                            return true;
                        }
                    }
                }
            }
            if (topLeft.x <= 7 && topLeft.y <= 7 && topLeft.x >= 0 && topLeft.y >= 0){
                if (b.board[topLeft.x][topLeft.y].getPiece() != null){
                    if (b.board[topLeft.x][topLeft.y].getPiece().color == color * -1){
                        if (p.x == topLeft.x && p.y == topLeft.y){
                            if (i==0){
                                this.moved = true;
                                MOVE(p);
                                promotion(p);
                            }
                            return true;
                        }
                    }
                }
            }
        }
        if (this.color == -1){
            Pair topRight = new Pair (position.x + 1,position.y - 1);
            Pair topLeft = new Pair (position.x - 1,position.y - 1);
            if (topRight.x <= 7 && topRight.y <= 7 && topRight.x >= 0 && topRight.y >= 0){
                if (b.board[topRight.x][topRight.y].getPiece() != null){
                    if (b.board[topRight.x][topRight.y].getPiece().color == color * -1){
                        if (p.x == topRight.x && p.y == topRight.y){
                            if (i==0){
                                this.moved = true;
                                MOVE(p);
                                promotion(p);
                            }
                            return true;
                        }
                    }
                }
            }
            if (topLeft.x <= 7 && topLeft.y <= 7 && topLeft.x >= 0 && topLeft.y >= 0){
                if (b.board[topLeft.x][topLeft.y].getPiece() != null){
                    if (b.board[topLeft.x][topLeft.y].getPiece().color == color * -1){
                        if (p.x == topLeft.x && p.y == topLeft.y){
                            if (i==0){
                                this.moved = true;
                                MOVE(p);
                                promotion(p);
                            }
                            return true;
                        }
                    }
                }
            }
        }
        if (b.board[p.x][p.y].isOccupied()){
            if (i==0){invalidMove(p);}
            return false;
        }
        // impelement empassant
        if (canEmpassant()){
            Pair empassantLocation = b.empassantLocation;// tells you where the OPPONENT's piece is
            Pair RightToEmpassantLocation = new Pair(empassantLocation.x + 1, empassantLocation.y);
            Pair LeftToEmpassantLocation = new Pair(empassantLocation.x - 1, empassantLocation.y);
            // they are where YOUR pawn should be located for empassant
            Pair placeToMove;
            // this is whre YOUR pawn will move
            if (this.color == -1){
                placeToMove = new Pair(empassantLocation.x, empassantLocation.y-1);
            } else {
                placeToMove = new Pair(empassantLocation.x, empassantLocation.y+1);
            }
            if ((position.x == RightToEmpassantLocation.x && position.y == RightToEmpassantLocation.y) || position.x == LeftToEmpassantLocation.x && position.y == LeftToEmpassantLocation.y){
                if (p.x == placeToMove.x && p.y == placeToMove.y){
                    if (i==0){
                        this.moved = true;
                        MOVE(p);
                        // manually remove the pawn
                        b.board[empassantLocation.x][empassantLocation.y].addPiece(null);
                    }
                    return true;
                }
            }
        }
        // Check if the move is legal for a pawn
        if (!isLegal(p)){
            if (i==0){invalidMove(p);}
            return false;
        }
        else{
            if (i==0){
                if (p.y - position.y == -2 || p.y - position.y == 2){
                    // this means that the pawn has moved for two spaces
                    b.setPottentialEmpassant(this.color);
                    b.setEmpassantLocation(p);
                }
                this.moved = true;
                MOVE(p);
                promotion(p);
            }
            return true;
        }
    }
    public boolean isLegal(Pair p){
        // Cannot move horizontally
        if (position.x!=p.x){
            return false;}
        // Cannot move backwards
        if (this.color==-1 && p.y>= position.y){
            return false;
        }
        if (this.color==1 && p.y<= position.y){
            return false;
        }
        // Cannot move more than one square if already moved once
        if (moved && (Math.abs(p.y- position.y)>1)){
            return false;
        }
        if (!moved && (Math.abs(p.y- position.y))>2){
            return false;
        }
        if ((this.color==-1 && b.board[p.x][position.y-1].isOccupied())|| (this.color==1 && b.board[p.x][position.y+1].isOccupied())){
            return false;
        }
        return true;
    }

    public boolean canEmpassant(){
        if (this.color == -1){
            return b.pottentialEmpassantWhite;
        } else {
            return b.pottentialEmpassantBlack;
        }
    }

    public void promotion(Pair p){
        if ((p.y == 0 && this.color == -1)||(p.y == 7 && this.color == 1)){
            System.out.println("Promotion!");
            b.board[p.x][p.y].removePiece();
            String[] options = {"Queen", "Rook", "Bishop", "Knight"};
            if (b.mode == "Random"){
                Random random = new Random();
                int next = random.nextInt(3);
                Piece pieceToPromote = null;
                if (next == 0) {
                    pieceToPromote = new Queen(p, this.color, b);
                } else if (next == 1) {
                    pieceToPromote = new Rook(p, this.color, b);
                } else if (next == 2) {
                    pieceToPromote = new Bishop(p, this.color, b);
                } else if (next == 3) {
                    pieceToPromote = new Knight(p, this.color, b);
                } else {
                    pieceToPromote = new Queen(p, this.color, b);
                }
                b.board[p.x][p.y].addPiece(pieceToPromote);
            } else {
                int choice = JOptionPane.showOptionDialog(null,
                        "Promote to:",
                        "Promotion",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]);
                Piece pieceToPromote = null;
                if (choice == 0) {
                    pieceToPromote = new Queen(p, this.color, b);
                } else if (choice == 1) {
                    pieceToPromote = new Rook(p, this.color, b);
                } else if (choice == 2) {
                    pieceToPromote = new Bishop(p, this.color, b);
                } else if (choice == 3) {
                    pieceToPromote = new Knight(p, this.color, b);
                } else {
                    pieceToPromote = new Queen(p, this.color, b);
                }
                b.board[p.x][p.y].addPiece(pieceToPromote);
            }
        }
    }

    public Piece clone(){
        return new Pawn(this.position, this.color, this.b);
    }
}
