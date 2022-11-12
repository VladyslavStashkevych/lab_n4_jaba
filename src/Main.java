import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int K = 10;
        Sudoku sudoku = new Sudoku(K);
        sudoku.fillValues();
        sudoku.printSudoku();

        System.out.print("Enter cell number\n >>> ");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();

        System.out.print("Enter number to insert\n >>> ");
        int num = scanner.nextInt();

        System.out.println(i + " " + num);

        boolean check = sudoku.changeCellNumber(i, num);
        if (check) {
            sudoku.printSudoku();
        }
        else {
            System.out.println("You're wrong!!! Loser");
        }
    }
}