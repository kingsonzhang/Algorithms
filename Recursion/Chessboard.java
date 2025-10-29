import java.util.Arrays;

public class Chessboard{
    /**
     * Finds the maxiumu number queens needed to be moved to reach a valid board state of queens
     * A valid board state of queens is when all 8 queens on the chessboard cannot attack each other
     * @param initialState int array size 8 of positions in the column, must be between 0 and 7
     * @return int number of queens in order to reach a valid state
     * @throws Exception if board given is invalid (not size 8) or pieces are placed outside board
     */
    public int maximumMoves(int[] initialState) throws Exception{
        if (initialState.length != 8)
            throw new Exception("Invalid Chessboard");
        for (int i = 0; i < initialState.length; i++)
            if (initialState[i] < 0 || initialState[i] > 7)
                throw new Exception("Invalid piece placement in chessboard");
        int[] updatedBoard = Arrays.copyOf(initialState, initialState.length);
        return this.placePieces(initialState, updatedBoard, 0);
    }

    /**
     * Recursive function to place queens onto a board until all 8 placed queens result in a valid board
     * A valid board state of queens is when all 8 queens on the chessboard cannot attack each other
     * Once a valid board state has been found, compare to initial state of board to find amount of queens moved
     * @param initialState Initial state of 8 queens placed on the board
     * @param updatedBoard Updated board keeping track of newly placed queens to check
     * @param row Current row to place a new queen in, and check if valid position
     * @return the minimum number of queens needed to be moved to reach a valid board state
     */
    private int placePieces(int[] initialState, int[] updatedBoard, int row){
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

    /**
     * Check to see if the position to place the queen is valid by checking if previous queens placed on the board cannot attack the newly placed queen
     * @param boardState Current board state of placed queens
     * @param row to insert the new queen
     * @param column to insert the new queen
     * @return true if the position is valid to place the queen, otherwise false
     */
    private boolean validSpace(int[] boardState, int row, int column){
        for (int previousRow = 0; previousRow < row; previousRow++){
            int previousColumn = boardState[previousRow];
            if (column == previousColumn)
                return false;
            if (Math.abs(previousRow - row) == Math.abs(previousColumn - column))
                return false;
        }
        return true;
    }

    /**
     * Calculate the number of queens that have been moved from the initialBoard to updatedBoard
     * Assumed queens can only be moved to different columns, rows will stay the same
     * @param initialBoard int array of the initial board state of positions
     * @param updatedBoard int array of the final board state of positions
     * @return the count of number of queens moved
     */
    private int boardMoves(int initialBoard[], int updatedBoard[]){
        int count = 0;
        for (int i = 0; i < 8; i++)
            if (initialBoard[i] != updatedBoard[i])
                count++;
        return count;
    }
}