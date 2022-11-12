public class Main {
    public static void main(String[] args) {
        int K = 10;
        Sudoku sudoku = new Sudoku(K);
        sudoku.fillValues();
        sudoku.printSudoku();

        sudoku.gameRound();
    }
}