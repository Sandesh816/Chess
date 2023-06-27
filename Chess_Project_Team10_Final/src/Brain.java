import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

public class Brain {
    Board board;
    public Brain(Board board){
        this.board = board;
    }
    public List<Possibilities> getPossibleMoves(Square[][] currentBoard, int turn){
        List<Pair> canMoveList = new ArrayList<>();
        List<Possibilities> allPossibilities = new ArrayList<>();
        for (int i = 0; i < currentBoard.length; i++){
            for (int j = 0; j < currentBoard[i].length; j++){
                if (currentBoard[i][j].getPiece() != null){
                    if (currentBoard[i][j].getPiece().color == turn){
                        canMoveList.add(new Pair(i,j));
                    }
                }
            }
        }
        for (Pair originalPair: canMoveList){
            for (int i = 0; i < currentBoard.length; i++){
                for (int j = 0; j < currentBoard[i].length; j++){
                    if (currentBoard[originalPair.x][originalPair.y].getPiece() != null){
                        if (currentBoard[originalPair.x][originalPair.y].getPiece().move(new Pair(i,j), 1)){
                            allPossibilities.add(new Possibilities(originalPair,new Pair(i,j)));
                        }
                    }
                }
            }
        }
        return allPossibilities;
    }

    public Board createArrayCopy(Square[][] originalBoard){
        Board newBoard = new Board(true);
        Square[][] boardToReturn = new Square[8][8];
        newBoard.board = boardToReturn;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j ++){
                boardToReturn[i][j] = new Square();
                Square originalSquare = originalBoard[i][j];
                if (originalSquare != null && originalSquare.getPiece() != null){
                    String originalPieceInString = originalSquare.getPiece().toString();
                    Pair pos = new Pair(i,j);
                    int color = originalSquare.getPiece().color;
                    Piece newPiece = null;
                    if (originalPieceInString.equals("Queen")){
                        newPiece = new Queen(pos,color,newBoard);
                    } else if (originalPieceInString.equals("King")){
                        newPiece = new King(pos,color,newBoard);
                    } else if (originalPieceInString.equals("Rook")){
                        newPiece = new Rook(pos,color,newBoard);
                    } else if (originalPieceInString.equals("Bishop")){
                        newPiece = new Bishop(pos,color,newBoard);
                    } else if (originalPieceInString.equals("Knight")){
                        newPiece = new Knight(pos,color,newBoard);
                    } else if (originalPieceInString.equals("Pawn")){
                        newPiece = new Pawn(pos,color,newBoard);
                    }
                    boardToReturn[i][j].addPiece(newPiece);
                }
            }
        }
        return newBoard;
    }

    public Possibilities getMaxCount(Square[][] originalBoard, int turn){
        int imaginaryTurn = turn;
        if (imaginaryTurn == -1){
            Board newBoard = new Board(true);
            newBoard.board = createArrayCopy(originalBoard).board;
            Square[][] imaginaryBoard = newBoard.board;
            System.out.println("Current board looks like: ");
            visualize(imaginaryBoard);
            List<Possibilities> allPossibilities = getPossibleMoves(originalBoard,imaginaryTurn);
            int count = 0;
            int maxCount = Integer.MIN_VALUE;
            Possibilities bestPrev = null;
            Possibilities bestSecondPrev = null;
            System.out.println("Length of the possibilities " + allPossibilities.size());
            // print out all the possibilities of my move
            System.out.println("My possibilities are: ");
            for (int i = 0; i < allPossibilities.size(); i++){
                Possibilities elm = allPossibilities.get(i);
                System.out.println("- <" + i + "> " + "Move " + imaginaryBoard[elm.originalPair.x][elm.originalPair.y].getPiece() + " at " + "[" + elm.originalPair.x + "," + elm.originalPair.y + "] to " + "[" + elm.newPair.x + ", " + elm.newPair.y + "]");
            }
            System.out.println("Now, let's consider all the possibilities");
            for (int i = 0; i < allPossibilities.size(); i++){
                Possibilities elm = allPossibilities.get(i);
                System.out.println("- <" + i + "> " + "Move " + imaginaryBoard[elm.originalPair.x][elm.originalPair.y].getPiece() + " at " + "[" + elm.originalPair.x + "," + elm.originalPair.y + "] to " + "[" + elm.newPair.x + ", " + elm.newPair.y + "]");
                // visualize(imaginaryBoard);
                if (imaginaryBoard[elm.originalPair.x][elm.originalPair.y].getPiece()!=null){
                    Piece FromMove = imaginaryBoard[elm.originalPair.x][elm.originalPair.y].getPiece();
                    Piece ToMove = imaginaryBoard[elm.newPair.x][elm.newPair.y].getPiece();
                    imaginaryBoard[elm.originalPair.x][elm.originalPair.y].addPiece(null);
                    imaginaryBoard[elm.newPair.x][elm.newPair.y].addPiece(FromMove);
                    // now new imaginary board made
                    visualize(imaginaryBoard);
                    List<Possibilities> secondPossibilities = getPossibleMoves(imaginaryBoard,turn*-1);
                    System.out.println("Length of the second possibilities: " + secondPossibilities.size());
                    int minSecondCount = Integer.MAX_VALUE;
                    Possibilities bestResponse = null;
                    for (int j = 0; j < secondPossibilities.size(); j++){
                        Possibilities secondElm = secondPossibilities.get(j);
                        System.out.println("- Second Possibility<" + i + "-" +j + "> " + "Move " + imaginaryBoard[secondElm.originalPair.x][secondElm.originalPair.y].getPiece() + " at " + "[" + secondElm.originalPair.x + "," + secondElm.originalPair.y + "] to " + "[" + secondElm.newPair.x + ", " + secondElm.newPair.y + "]");
                        Piece FromMoveSecond = imaginaryBoard[secondElm.originalPair.x][secondElm.originalPair.y].getPiece();
                        Piece ToMoveSecond = imaginaryBoard[secondElm.newPair.x][secondElm.newPair.y].getPiece();
                        imaginaryBoard[secondElm.originalPair.x][secondElm.originalPair.y].addPiece(null);
                        imaginaryBoard[secondElm.newPair.x][secondElm.newPair.y].addPiece(FromMoveSecond);
                        System.out.println("-- this will yield count = " + count(imaginaryBoard));
                        if (count(imaginaryBoard)<minSecondCount){
                            minSecondCount = count(imaginaryBoard);
                            bestSecondPrev = elm;
                            bestResponse = secondElm;
                        }
                        if (j == secondPossibilities.size()-1){
                            System.out.println("This is the last search: The optimal move against this fist possibity is to move " + imaginaryBoard[bestResponse.originalPair.x][bestResponse.originalPair.y].getPiece() + " to " + bestResponse.newPair.x + " & " + bestResponse.newPair.y + " whcih ends up count " + minSecondCount);
                        }
                        // reset
                        imaginaryBoard[secondElm.originalPair.x][secondElm.originalPair.y].addPiece(FromMoveSecond);
                        imaginaryBoard[secondElm.newPair.x][secondElm.newPair.y].addPiece(ToMoveSecond);
                    }
                    if (minSecondCount > maxCount){
                        maxCount = minSecondCount;
                        bestPrev = bestSecondPrev;
                    }
                    // reset
                    imaginaryBoard[elm.originalPair.x][elm.originalPair.y].addPiece(FromMove);
                    imaginaryBoard[elm.newPair.x][elm.newPair.y].addPiece(ToMove);
                }
            }
            System.out.println("[Conclusion] The optimal move right now therefore is to move " + imaginaryBoard[bestPrev.originalPair.x][bestPrev.originalPair.y].getPiece() + " to " + bestPrev.newPair.x + " & " + bestPrev.newPair.y + " whcih ends up count " + maxCount);
            return bestPrev;
        } else {
            Board newBoard = new Board(true);
            newBoard.board = createArrayCopy(originalBoard).board;
            Square[][] imaginaryBoard = newBoard.board;
            System.out.println("Current board looks like: ");
            visualize(imaginaryBoard);
            List<Possibilities> allPossibilities = getPossibleMoves(originalBoard,imaginaryTurn);
            int count = 0;
            int minCount = Integer.MAX_VALUE;
            Possibilities bestPrev = null;
            Possibilities bestSecondPrev = null;
            System.out.println("Length of the possibilities " + allPossibilities.size());
            for (int i = 0; i < allPossibilities.size(); i++){
                Possibilities elm = allPossibilities.get(i);
                System.out.println("Possibility [" + i + "] : " + "move " + imaginaryBoard[elm.originalPair.x][elm.originalPair.y].getPiece() + " to " + elm.newPair.x + " & " + elm.newPair.y);
                if (imaginaryBoard[elm.originalPair.x][elm.originalPair.y].getPiece()!=null){
                    Piece FromMove = imaginaryBoard[elm.originalPair.x][elm.originalPair.y].getPiece();
                    Piece ToMove = imaginaryBoard[elm.newPair.x][elm.newPair.y].getPiece();
                    imaginaryBoard[elm.originalPair.x][elm.originalPair.y].addPiece(null);
                    imaginaryBoard[elm.newPair.x][elm.newPair.y].addPiece(FromMove);
                    // now new imaginary board made
                    visualize(imaginaryBoard);
                    if (imaginaryBoard[3][4].getPiece() != null){
                        if (imaginaryBoard[3][4].getPiece().color == 1){
                            ;
                        };
                    }
                    List<Possibilities> secondPossibilities = getPossibleMoves(imaginaryBoard,imaginaryTurn*-1);
                    System.out.println("Length of the second possibilities: " + secondPossibilities.size());
                    int maxSecondCount = Integer.MIN_VALUE;
                    Possibilities bestResponse = null;
                    for (int j = 0; j < secondPossibilities.size(); j++){
                        Possibilities secondElm = secondPossibilities.get(j);
                        System.out.println("- Second Possibility<" + i + "-" +j + "> " + "Move " + imaginaryBoard[secondElm.originalPair.x][secondElm.originalPair.y].getPiece() + " at " + "[" + secondElm.originalPair.x + "," + secondElm.originalPair.y + "] to " + "[" + secondElm.newPair.x + ", " + secondElm.newPair.y + "]");
                        Piece FromMoveSecond = imaginaryBoard[secondElm.originalPair.x][secondElm.originalPair.y].getPiece();
                        Piece ToMoveSecond = imaginaryBoard[secondElm.newPair.x][secondElm.newPair.y].getPiece();
                        imaginaryBoard[secondElm.originalPair.x][secondElm.originalPair.y].addPiece(null);
                        imaginaryBoard[secondElm.newPair.x][secondElm.newPair.y].addPiece(FromMoveSecond);
                        System.out.println("-- this will yield count = " + count(imaginaryBoard));
                        if (count(imaginaryBoard)>maxSecondCount){
                            maxSecondCount = count(imaginaryBoard);
                            bestSecondPrev = elm;
                            bestResponse = secondElm;
                        }
                        if (j == secondPossibilities.size()-1){
                            System.out.println("This is the last search: The optimal move against this fist possibity is to move " + imaginaryBoard[bestSecondPrev.originalPair.x][bestSecondPrev.originalPair.y].getPiece() + " to " + bestSecondPrev.newPair.x + " & " + bestSecondPrev.newPair.y + " whcih ends up count " + maxSecondCount);
                        }
                        // reset
                        imaginaryBoard[secondElm.originalPair.x][secondElm.originalPair.y].addPiece(FromMoveSecond);
                        imaginaryBoard[secondElm.newPair.x][secondElm.newPair.y].addPiece(ToMoveSecond);
                    }
                    if (maxSecondCount < minCount){
                        minCount = maxSecondCount;
                        bestPrev = bestSecondPrev;
                    }
                    // reset
                    imaginaryBoard[elm.originalPair.x][elm.originalPair.y].addPiece(FromMove);
                    imaginaryBoard[elm.newPair.x][elm.newPair.y].addPiece(ToMove);
                }
            }
            System.out.println("The optimal move right now therefore is to move " + imaginaryBoard[bestPrev.originalPair.x][bestPrev.originalPair.y].getPiece() + " to " + bestPrev.newPair.x + " & " + bestPrev.newPair.y + " whcih ends up count " + minCount);
            return bestPrev;
        }
    }
    public int count(Square[][] imaginaryBoard){
        int count = 0;
        for (int i = 0; i < imaginaryBoard.length; i++){
            for (int j = 0; j < imaginaryBoard[i].length; j++){
                if (imaginaryBoard[j][i].getPiece() == null){
                    ;
                } else if (imaginaryBoard[j][i].getPiece().color == -1){
                    String pieceName = imaginaryBoard[j][i].getPiece().toString();
                    count += getPieceScore(pieceName,new Pair(i,j),getGamePhase(imaginaryBoard));
                } else if (imaginaryBoard[j][i].getPiece().color == 1){
                    String pieceName = imaginaryBoard[j][i].getPiece().toString();
                    count -= getPieceScore(pieceName,new Pair(i,j),getGamePhase(imaginaryBoard));
                }
            }
        }
        return count;
    }

    public void visualize(Square[][] imaginaryBoard){
        System.out.println("----------------");
        for (int i = 0; i < imaginaryBoard.length; i++){
            for (int j = 0; j < imaginaryBoard[i].length; j++){
                if (imaginaryBoard[j][i].getPiece() == null){
                    System.out.print("□");
                } else {
                    if (imaginaryBoard[j][i].getPiece().toString() == "King"){
                        System.out.print("K");
                    } else if (imaginaryBoard[j][i].getPiece().toString() == "Queen"){
                        System.out.print("Q");
                    } else if (imaginaryBoard[j][i].getPiece().toString() == "Rook"){
                        System.out.print("R");
                    } else if (imaginaryBoard[j][i].getPiece().toString() == "Bishop"){
                        System.out.print("B");
                    } else if (imaginaryBoard[j][i].getPiece().toString() == "Pawn"){
                        System.out.print("P");
                    } else {
                        System.out.print("N");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("----------------");
    }

    public int getGamePhase(Square[][] originalBoard){
        int count = 0;
        for (int i = 0; i < 8; i++){
            for (int j =0; j <8; j++){
                if (originalBoard[i][j].getPiece()!=null){
                    count += 1;
                }
            }
        }
        if (count < 32){
            return 1;
        }
        return 0;
    }

    public int getPieceScore(String pieceName, Pair position, int phase){
        int base = 0;
        int bonus = 0;
        if (phase == 0){
            if (pieceName == "Pawn"){
                base = 142;
                int[][] arr = {
                        {0,0,0,0,0,0,0,0},
                        {-4,20,-8,-4,-4,-8,20,-4},
                        {-6,-8,-6,-2,-2,-6,-8,-6},
                        {-6,5,3,21,21,3,5,-6},
                        {-17,-9,20,35,35,20,-9,-17},
                        {-18,-2,19,24,24,19,-2,-18},
                        {-11,6,7,3,3,7,6,-11},
                        {0,0,0,0,0,0,0,0}
                };
                bonus = arr[position.x][position.y];
            } else if (pieceName == "Knight"){
                base = 784;
                int[][] arr = {
                        {-195,-67,-42,-29,-29,-42,-67,-195},
                        {-63,-19,5,14,14,5,-19,-63},
                        {-11,37,56,65,65,56,37,-11},
                        {-26,16,38,50,50,38,16,-26},
                        {-25,18,43,47,47,43,18,-25},
                        {-71,-22,0,9,9,0,-22,-71},
                        {-83,-43,-21,-10,-10,-21,-43,-83},
                        {-161,-96,-80,-73,-73,-80,-96,-161}
                };
                bonus = arr[position.x][position.y];
            } else if (pieceName == "Bishiop"){
                base = 828;
                int[][] arr = {
                        {-35,-11,-19,-29,-29,-19,-11,-35},
                        {-23,17,6,-2,-2,6,17,-23},
                        {-17,16,12,2,2,12,16,-17},
                        {-11,27,16,9,9,16,27,-11},
                        {-11,28,21,10,10,21,28,-11},
                        {-9,27,21,21,11,11,21,27,-9},
                        {-20,20,12,1,1,12,20,-20},
                        {-44,-13,-25,-34,-34,-25,-13,-44}
                };
                bonus = arr[position.x][position.y];
            } else if (pieceName == "Rook"){
                base = 1286;
                int[][] arr = {
                        {-23,-15,-11,-5,-5,-11,-15,-23},
                        {-12,4,8,12,12,8,4,-12},
                        {-21,-7,0,2,2,0,-7,-21},
                        {-22,-7,0,1,1,0,-7,-22},
                        {-22,-6,-1,2,2,-1,-6,-22},
                        {-21,-9,-4,2,2,-4,-9,-21},
                        {-21,-8,-3,0,0,-3,-8,-21},
                        {-25,-16,-16,-9,-9,-16,-16,-25}
                };
                bonus = arr[position.x][position.y];
            } else if (pieceName == "Queen"){
                base = 2528;
                int[][] arr = {
                        {-1,-4,-1,0,0,-1,-4,-1},
                        {-2,7,7,6,6,7,7,-2},
                        {-2,6,8,10,10,8,6,-2},
                        {-3,9,8,7,7,8,9,-3},
                        {-1,8,10,7,7,10,8,-1},
                        {-2,6,9,9,9,9,6,-2},
                        {-4,6,9,8,8,9,6,-4},
                        {0,-4,-3,-1,-1,-3,-4,0}
                };
                bonus = arr[position.x][position.y];
            } else if (pieceName == "King"){
                base = 0;
                int[][] arr = {
                        {64,87,49,0,0,49,87,64},
                        {87,120,64,25,25,64,120,87},
                        {122,159,85,36,36,85,159,122},
                        {145,176,112,69,69,112,176,145},
                        {169,191,136,108,108,136,191,169},
                        {198,253,168,120,120,168,253,198},
                        {277,305,241,183,183,241,305,277},
                        {272,325,273,190,190,273,325,272}
                };
                bonus = arr[position.x][position.y];
            }
        } else {
            if (pieceName == "Pawn"){
                base = 142;
                int[][] arr = {
                        {0, 0, 0, 0, 0, 0, 0, 0},
                        {3, -9, 1, 18, 18, 1, -9, 3},
                        {8, -5, 2, 4, 4, 2, -5, 8},
                        {8, 9, 7, -6, -6, 7, 9, 8},
                        {3, 3, -8, -3, -3, -8, 3, 3},
                        {-4, -5, 5, 4, 4, 5, -5, -4},
                        {7, -4, 8, -2, -2, 8, -4, 7},
                        {0, 0, 0, 0, 0, 0, 0, 0}
                };
                bonus = arr[position.x][position.y];
            } else if (pieceName == "Knight"){
                base = 784;
                int[][] arr = {
                        {-109,-89,-50,-13,-13,-50,-89,-109},
                        {-65,-50,-24,13,13,-24,-50,-65},
                        {-54,-38,-7,27,27,-7,-38,-54},
                        {-46,-25,3,40,40,3,-25,-46},
                        {-41,-25,6,38,38,6,-25,-41},
                        {-50,-39,-7,28,28,-7,-39,-50},
                        {-69,-54,-17,9,9,-17,-54,-69},
                        {-105,-82,-46,-14,-14,-46,-82,-105}
                };
                bonus = arr[position.x][position.y];
            } else if (pieceName == "Bishiop"){
                base = 828;
                int[][] arr = {
                        {-55,-32,-36,-17,-17,-36,-32,-55},
                        {-34,-10,-12,6,6,-12,-10,-34},
                        {-24,-2,0,13,13,0,-2,-24},
                        {-26,-4,-7,14,14,-7,-4,-26},
                        {-26,-3,-5,16,16,-5,-3,-26},
                        {-23,0,-3,16,16,-3,0,-23},
                        {-34,-9,-14,4,4,-14,-9,-34},
                        {-58,-31,-37,-19,-19,-37,-31,-58}
                };
                bonus = arr[position.x][position.y];
            } else if (pieceName == "Rook"){
                base = 1286;
                int[][] arr = {
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0}
                };
                bonus = arr[position.x][position.y];
            } else if (pieceName == "Queen"){
                base = 2528;
                int[][] arr = {
                        {-74,-55,-43,-30,-30,-43,-55,-74},
                        {-55,-30,-21,-6,-6,-21,-30,-55},
                        {-40,-16,-10,3,3,-10,-16,-40},
                        {-27,-5,10,21,21,10,-5,-27},
                        {-29,-5,9,19,19,9,-5,-29},
                        {-39,-17,-8,5,5,-8,-17,-39},
                        {-56,-30,-21,-5,-5,-21,-30,-56},
                        {-71,-56,-42,-29,-29,-42,-56,-71}
                };
                bonus = arr[position.x][position.y];
            } else if (pieceName == "King"){
                base = 30000;
                int[][] arr = {
                        {5,60,75,75,75,75,60,5},
                        {40,99,128,141,141,128,99,40},
                        {87,164,174,189,189,174,164,87},
                        {98,166,197,194,194,197,166,98},
                        {103,152,168,169,169,168,152,103},
                        {86,138,165,173,173,165,138,86},
                        {57,98,138,131,131,138,98,57},
                        {0,41,80,93,93,80,41,0}
                };
                bonus = arr[position.x][position.y];
            }
        }
        return base + bonus;
    }
}

class Possibilities{
    Pair originalPair; // where the piece is currently located
    Pair newPair;
    int count;
    public Possibilities(Pair originalPair, Pair newPair){
        this.originalPair = originalPair;
        this.newPair = newPair;
    }
}