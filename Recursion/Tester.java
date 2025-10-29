public class Tester{
    public static void main(String args[]){
        Permutation tester = new Permutation();
        System.out.println(tester.permutationExists("HELLO WORLD", "world"));

        Chessboard board = new Chessboard();
        System.out.println(board.maximumMoves(new int[] {4, 2, 0, 6, 1, 7, 5, 3}));
    }
}