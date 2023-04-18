public class Main {
    public static void main(String[] args) {
        Board A = new Board();
        System.out.println(A);
        System.out.println(A.board[4][7].getPiece());
        A.board[4][7].getPiece().move(new Pair(3,7));
        System.out.println(A.board[3][7].getPiece());
    }
}