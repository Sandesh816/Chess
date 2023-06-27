public class Mouse {
    int x;
    int y;
    int selectedX;
    int selectedY;
    Piece pieceSelected;
    int moveToX;
    int moveToY;
    int mouseStatus;

    Draw draw;
    Square[][] current;

    Board currentBoard;
    // mouseStatus
    // 0 is default (before you click on piece)
    // after you click on piece which you can move it becomes 1

    // constructor
    public Mouse(int x, int y, Square[][] current, Draw draw){
        this.x = x;
        this.y = y;
        this.draw = draw;
        this.mouseStatus = 0;
        this.current = current;
        this.currentBoard = new Board(true);
    }

    public void setSelect(int selectedX, int selectedY){
        this.selectedX = selectedX;
        this.selectedY = selectedY;
        System.out.println("Successfully retrieved [" + selectedX + "," + selectedY + "]");
    }

    public void printMouseStatus(){
        if (mouseStatus == 0){
            System.out.println("[Select mode]");
        } else if (mouseStatus == 1){
            System.out.println("[Move mode]");
        }
    }

    public boolean checkInside(int x, int y){
        if (x > 720 || x < 80 || y > 720 || y < 80){
            return false;
        }
        return true;
    }

    public void printCheckInside(int x, int y){
        if (checkInside(x,y)){
            System.out.println("You are clicking somewhere valid.");
        } else {
            System.out.println("You are clicking somewhere invalid");
        }
    }

    public void changeToMoveMode(){
        this.mouseStatus = 1;
    }

    public void select(int x, int y){
        if (mouseStatus == 0){
            selectedX = (int)(Math.floor((x-80)/80));
            selectedY = (int)(Math.floor((y-80)/80));
            System.out.println("End");
            //draw.setWhereToGo(selectedX,selectedY);
            mouseStatus = 1;
            pieceSelected = currentBoard.board[selectedX][selectedY].getPiece();
            System.out.println("Select: You have selected " + pieceSelected.toString() + " at " + "[" + pieceSelected.position.x + "," + pieceSelected.position.y+ "]");
            System.out.println("Select: Now choose the square to move.");
        }
    }

    public void move(int x, int y){
        if (mouseStatus == 1){
            moveToX = (int)(Math.floor((x-80)/80));
            moveToY = (int)(Math.floor((y-80)/80));
            System.out.println("Getting the available moves");
            System.out.println(currentBoard.board[selectedX][selectedY].getPiece().getMoves());
            System.out.println("Move: Okay. I will move " + currentBoard.board[selectedX][selectedY].getPiece() + " at [" + selectedX + "," + selectedY + "] to " + "[" + moveToX + "," + moveToY + "]");
            currentBoard.board[selectedX][selectedY].getPiece().move(new Pair(moveToX,moveToY), 0);
            System.out.println("Move: You have moved it to " + moveToX + " & " + moveToY);
            // check if it is really moved on board
            // System.out.println("Let's check if it is really moved." + currentBoard.getPiece(moveToX, moveToY).toString() + " is certainly at " + "[" + moveToX + "," + moveToY + "]" );
        } else {
            System.out.println("First, choose piece to move. You are in select mode");
        }
    }
}
