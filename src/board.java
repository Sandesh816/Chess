public class board {
    private final int COLUMN = 8;
    private final int ROW = 8;
    private Piece[][] array;

    public board(Piece[][] array) {
        this.array = array;
    }

    public Piece[][] current() {
        return array;
    }

    public void initialize() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (i == 0) {
                    if (j == 0 || j == 7) {
                        array[i][j] = new Piece(2, 3);
                    } else if (j == 1 || j == 6) {
                        array[i][j] = new Piece(2, 2);
                    } else if (j == 2 || j == 5) {
                        array[i][j] = new Piece(2, 4);
                    } else if (j == 3) {
                        array[i][j] = new Piece(2, 5);
                    } else {
                        array[i][j] = new Piece(2, 6);
                    }
                } else if (i == 1) {
                    array[i][j] = new Piece(2, 1);
                } else if (i >= 2 && i <= 5) {
                    array[i][j] = new Piece(0, 0);
                } else if (i == 6) {
                    array[i][j] = new Piece(1, 1);
                } else {
                    if (j == 0 || j == 7) {
                        array[i][j] = new Piece(1, 3);
                    } else if (j == 1 || j == 6) {
                        array[i][j] = new Piece(1, 2);
                    } else if (j == 2 || j == 5) {
                        array[i][j] = new Piece(1, 4);
                    } else if (j == 3) {
                        array[i][j] = new Piece(1, 6);
                    } else {
                        array[i][j] = new Piece(1, 5);
                    }
                }
            }
        }
    }
}
