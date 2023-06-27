import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;



public class Rules {
    public boolean isCheckRightNow(Square[][] board, int turn){
        Pair kingLocation = null;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j ++){
               if (board[i][j].getPiece() != null){
                   Piece piece = board[i][j].getPiece();
                   if (piece.toString() == "King" && piece.color == turn){
                       kingLocation = new Pair(i,j);
                   }
               }
            }
        }
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j ++){
                if (board[i][j].getPiece() != null){
                    Piece piece = board[i][j].getPiece();
                    if (piece.color != turn){
                        for (Pair pair: piece.getMoves()){
                            if (pair.x == kingLocation.x && pair.y == kingLocation.y){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isCheckNext(Board board, int color, Pair newPair){
        Square[][] newBoard = board.board;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j ++){
                if (newBoard[i][j].getPiece() != null){
                    Piece piece = newBoard[i][j].getPiece();
                    if (piece.color != color){
                        for (Pair pair: piece.getMoves()){
                            if (pair.x == newPair.x && pair.y == newPair.y){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isCheckMate(Square[][] board, int turn){
        ;
        return true;
    }
}
