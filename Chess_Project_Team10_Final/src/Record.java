import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public class Record {
    List<String> recordList = new ArrayList<>();
    public void addRecord(Piece pieceToMove, int turn, boolean involveCatch, Pair oldPair, Pair newPair, boolean didLeftCastling, boolean didRightCastling){
        String catchSymbol = "";
        String castlingSymbol = "";
        if (didLeftCastling){
            castlingSymbol = "0-0-0";
        } else if (didRightCastling){
            castlingSymbol = "0-0";
        }
        if (involveCatch == true){
            catchSymbol = "x";
        }
        int fromX = (char)(oldPair.x + 'a');
        int fromY = 8 - oldPair.y;
        int newX = (char)(newPair.x + 'a');
        int newY = 8 - newPair.y;
        String initialAlphabet = "?";
        if (pieceToMove.toString() == "King"){
            initialAlphabet = "K";
        } else if (pieceToMove.toString() == "Queen"){
            initialAlphabet = "Q";
        } else if (pieceToMove.toString() == "Rook"){
            initialAlphabet = "R";
        } else if (pieceToMove.toString() == "Bishop"){
            initialAlphabet = "B";
        } else if (pieceToMove.toString() == "Knight"){
            initialAlphabet = "N";
        } else if (pieceToMove.toString() == "Pawn"){
            initialAlphabet = "";
        }
        String toAdd = initialAlphabet + catchSymbol + "(" + fromX + fromY + ")" + newX + newY;
        recordList.add(toAdd);
    }

    public void createFile(){
        try {
            FileWriter myWriter = new FileWriter("record.txt");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            myWriter.write("[Time " + now + "]\n");
            myWriter.write("\n");
            for (int i = 0; i < recordList.size(); i++){
                myWriter.write(recordList.get(i) + " " + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("err");
            e.printStackTrace();
        }
    }
}
