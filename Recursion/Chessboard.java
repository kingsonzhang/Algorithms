import java.util.Arrays;

public class Chessboard{
    public int maximumMoves(int[] initialState){
        int[] updatedBoard = Arrays.copyOf(initialState, initialState.length);
        return this.placePieces(initialState, updatedBoard, 0);
    }

    public int placePieces(int[] initialState, int[] updatedBoard, int row){
        if (row == 7){
            int moves = 8;
            for (int i = 0; i < 8; i++){
                if (this.validSpace(updatedBoard, row, i)){
                    updatedBoard[row] = i;
                    moves = this.boardMoves(initialState, updatedBoard);
                }
            }
            return moves;
        }
        else{
            int[] moves = {8, 8, 8, 8, 8, 8, 8, 8};
            for (int i = 0; i < 8; i++){
                if (this.validSpace(updatedBoard, row, i)){
                    updatedBoard[row] = i;
                    moves[i] = this.placePieces(initialState, updatedBoard, row + 1);
                }
            }
            int minimum = moves[0];
            for (int i = 1; i < moves.length; i++)
                minimum = moves[i] < minimum ? moves[i] : minimum;
            return minimum;
        }
    }

    public boolean validSpace(int[] boardState, int row, int column){
        for (int previousRow = 0; previousRow < row; previousRow++){
            int previousColumn = boardState[previousRow];
            if (column == previousColumn)
                return false;
            if (Math.abs(previousRow - row) == Math.abs(previousColumn - column))
                return false;
        }
        return true;
    }

    public int boardMoves(int initialBoard[], int updatedBoard[]){
        int count = 0;
        for (int i = 0; i < 8; i++)
            if (initialBoard[i] != updatedBoard[i])
                count++;
        return count;
    }
}