abstract public class Piece{
    int color;
    int x;
    int y;
    // black is 0, white is -1
    public Piece(int color, int x, int y){
        this.color = color;
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getColor(){
        return color;
    }

    public void update(int x, int y){
        this.x = x;
        this.y = y;
    }

    abstract public int[][] getMove();
}


class King extends Piece{
    public King(int color, int x, int y) {
        super(color, x, y);
    }
    public int[][] getMove(){
        int[][] move = new int[8][2];
        move[0][0] = 1;
        move[0][1] = 1;
        move[1][0] = 1;
        move[1][1] = 0;
        move[2][0] = 1;
        move[2][1] = -1;
        return move;
    }

    public String toString(){
        return "King";
    }
}

class Queen extends Piece{
    public Queen(int color, int x, int y) {
        super(color, x, y);
    }
    public int[][] getMove() {
        int[][] move = new int[32][2];
        move[0][0] = 0;
        move[0][1] = 1;
        move[1][0] = 0;
        move[1][1] = 2;
        move[2][0] = 0;
        move[2][1] = 3;
        move[3][0] = 0;
        move[3][1] = 4;
        move[4][0] = 0;
        move[4][1] = -1;
        move[5][0] = 0;
        move[5][1] = -2;
        move[6][0] = 0;
        move[6][1] = -3;
        move[7][0] = 0;
        move[7][1] = -4;
        move[8][0] = 1;
        move[8][1] = 0;
        move[9][0] = 2;
        move[9][1] = 0;
        move[10][0] = 3;
        move[10][1] = 0;
        move[11][0] = 4;
        move[11][1] = 0;
        move[12][0] = -1;
        move[12][1] = 0;
        move[13][0] = -2;
        move[13][1] = 0;
        move[14][0] = -3;
        move[14][1] = 0;
        move[15][0] = -4;
        move[15][1] = 0;
        move[16][0] = 1;
        move[16][1] = 1;
        move[17][0] = 1;
        move[17][1] = -1;
        move[18][0] = -1;
        move[18][1] = 1;
        move[19][0] = -1;
        move[19][1] = -1;
        move[20][0] = 2;
        move[20][1] = 2;
        move[21][0] = 2;
        move[21][1] = -2;
        move[22][0] = -2;
        move[22][1] = 2;
        move[23][0] = -2;
        move[23][1] = -2;
        move[24][0] = 3;
        move[24][1] = 3;
        move[25][0] = 3;
        move[25][1] = -3;
        move[26][0] = -3;
        move[26][1] = 3;
        move[27][0] = -3;
        move[27][1] = -3;
        move[28][0] = 4;
        move[28][1] = 4;
        move[29][0] = 4;
        move[29][1] = -4;
        move[30][0] = -4;
        move[30][1] = 4;
        move[31][0] = -4;
        move[31][1] = -4;
        return move;
    }
    public String toString(){
        return "Queen";
    }
}

class Rook extends Piece{
    public Rook(int color, int x, int y) {
        super(color, x, y);
    }
    public int[][] getMove() {
        int[][] move = new int[16][2];
        move[0][0] = 0;
        move[0][1] = 1;
        move[1][0] = 0;
        move[1][1] = 2;
        move[2][0] = 0;
        move[2][1] = 3;
        move[3][0] = 0;
        move[3][1] = 4;
        move[4][0] = 0;
        move[4][1] = -1;
        move[5][0] = 0;
        move[5][1] = -2;
        move[6][0] = 0;
        move[6][1] = -3;
        move[7][0] = 0;
        move[7][1] = -4;
        move[8][0] = 1;
        move[8][1] = 0;
        move[9][0] = 2;
        move[9][1] = 0;
        move[10][0] = 3;
        move[10][1] = 0;
        move[11][0] = 4;
        move[11][1] = 0;
        move[12][0] = -1;
        move[12][1] = 0;
        move[13][0] = -2;
        move[13][1] = 0;
        move[14][0] = -3;
        move[14][1] = 0;
        move[15][0] = -4;
        move[15][1] = 0;
        return move;
    }
    public String toString(){
        return "Rook";
    }
}

class Bishop extends Piece{
    public Bishop(int color, int x, int y) {
        super(color, x, y);
    }
    public int[][] getMove() {
        int[][] move = new int[16][2];
        move[0][0] = 1;
        move[0][1] = 1;
        move[1][0] = 1;
        move[1][1] = -1;
        move[2][0] = -1;
        move[2][1] = 1;
        move[3][0] = -1;
        move[3][1] = -1;
        move[4][0] = 2;
        move[4][1] = 2;
        move[5][0] = 2;
        move[5][1] = -2;
        move[6][0] = -2;
        move[6][1] = 2;
        move[7][0] = -2;
        move[7][1] = -2;
        move[8][0] = 3;
        move[8][1] = 3;
        move[9][0] = 3;
        move[9][1] = -3;
        move[10][0] = -3;
        move[10][1] = 3;
        move[11][0] = -3;
        move[11][1] = -3;
        move[12][0] = 4;
        move[12][1] = 4;
        move[13][0] = 4;
        move[13][1] = -4;
        move[14][0] = -4;
        move[14][1] = 4;
        move[15][0] = -4;
        move[15][1] = -4;
        return move;
    }
    public String toString(){
        return "Bishop";
    }
}

class Knight extends Piece{
    public Knight(int color, int x, int y) {
        super(color, x, y);
    }
    public int[][] getMove() {
        int[][] move = new int[8][2];
        move[0][0] = 1;
        move[0][1] = 2;
        move[1][0] = 1;
        move[1][1] = -2;
        move[2][0] = -1;
        move[2][1] = 2;
        move[3][0] = -1;
        move[3][1] = -2;
        move[4][0] = 2;
        move[4][1] = 1;
        move[5][0] = 2;
        move[5][1] = -1;
        move[6][0] = -2;
        move[6][1] = 1;
        move[7][0] = -2;
        move[7][1] = -1;
        return move;
    }
    public String toString(){
        return "Knight";
    }
}

class Pawn extends Piece{
    boolean hasMoved;
    public Pawn(int color, int x, int y) {
        super(color, x, y);
        hasMoved = true;
    }
    public int[][] getMove() {
        int[][] move = new int[4][2];
        int count = 0;

        if (color == 0) { // black pawn moves
            if (!hasMoved) { // can move 2 spaces forward on first move
                move[count][0] = 2;
                move[count][1] = 0;
                count++;
            }
            move[count][0] = 1;
            move[count][1] = 0;
            count++;
            move[count][0] = 1;
            move[count][1] = 1;
            count++;
            move[count][0] = 1;
            move[count][1] = -1;
            count++;
        }
        else { // white pawn moves
            if (!hasMoved) { // can move 2 spaces forward on first move
                move[count][0] = -2;
                move[count][1] = 0;
                count++;
            }
            move[count][0] = -1;
            move[count][1] = 0;
            count++;
            move[count][0] = -1;
            move[count][1] = 1;
            count++;
            move[count][0] = -1;
            move[count][1] = -1;
            count++;
        }

        return move;
    }

    public void move() {
        hasMoved = true;
    }
    public String toString(){
        return "Pawn";
    }
}

class Nothing extends Piece{
    boolean hasMoved;
    public Nothing(int color, int x, int y) {
        super(color, x, y);
    }

    public int[][] getMove(){
        return null;
    }

    public String toString(){
        return "Nothing";
    }
}