import java.lang.*;

public class Sudoku {
    /* Java program for Sudoku generator */
    int[][] matrix;
    int[][] resolution;
    int K; // No. Of missing digits

    // Constructor
    Sudoku(int K) {
        this.K = K;

        matrix = new int[9][9];
        resolution = new int[9][9];
    }

    // Sudoku Generator
    public void fillValues() {
        // Fill the diagonal of SRN x SRN matrices
        fillDiagonal();

        // Fill remaining blocks
        fillRemaining(0, 3);

        // Remove Randomly K digits to make game
        removeKDigits();
    }

    // Fill the diagonal SRN number of SRN x SRN matrices
    void fillDiagonal() {

        for (int i = 0; i < 9; i = i + 3)

            // for diagonal box, start coordinates->i==j
            fillBox(i, i);
    }

    // Returns false if given 3 x 3 block contains num.
    boolean unUsedInBox(int rowStart, int colStart, int num) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (matrix[rowStart + i][colStart + j] == num)
                    return false;

        return true;
    }

    // Fill a 3 x 3 matrix.
    void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    num = randomGenerator(9);
                }
                while (!unUsedInBox(row, col, num));

                matrix[row + i][col + j] = num;
            }
        }
    }

    // Random generator
    int randomGenerator(int num) {
        return (int) Math.floor((Math.random() * num + 1));
    }

    // Check if safe to put in cell
    boolean CheckIfSafe(int i, int j, int num) {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i - i % 3, j - j % 3, num));
    }

    // check in the row for existence
    boolean unUsedInRow(int i, int num) {
        for (int j = 0; j < 9; j++)
            if (matrix[i][j] == num)
                return false;
        return true;
    }

    // check in the row for existence
    boolean unUsedInCol(int j, int num) {
        for (int i = 0; i < 9; i++)
            if (matrix[i][j] == num)
                return false;
        return true;
    }

    // A recursive function to fill remaining
    // matrix
    boolean fillRemaining(int i, int j) {
        if (j >= 9 && i < 8) {
            i = i + 1;
            j = 0;
        }
        if (i >= 9 && j >= 9)
            return true;

        if (i < 3) {
            if (j < 3)
                j = 3;
        } else if (i < 6) {
            if (j == (int) (i / 3) * 3)
                j = j + 3;
        } else {
            if (j == 6) {
                i = i + 1;
                j = 0;
                if (i >= 9)
                    return true;
            }
        }

        for (int num = 1; num <= 9; num++) {
            if (CheckIfSafe(i, j, num)) {
                matrix[i][j] = num;
                resolution[i][j] = num;
                if (fillRemaining(i, j + 1))
                    return true;

                resolution[i][j] = 0;
            }
        }
        return false;
    }

    // Remove the K no. of digits to
    // complete game
    public void removeKDigits() {
        int count = K;
        while (count != 0) {
            int cellId = randomGenerator(9 * 9) - 1;

            int i = (cellId / 9);
            int j = cellId % 9;
            if (j != 0)
                j = j - 1;

            if (resolution[i][j] != 0) {
                count--;
                resolution[i][j] = 0;
            }
        }
    }

    public boolean changeCellNumber(int n, int num) {
        int count = 0;
        boolean res = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (resolution[i][j] == 0) {
                    count++;
                    if (count == n) {
                        res = num == matrix[i][j];
                        resolution[i][i] = res ? num : 0;
                        break;
                    }
                }
            }
        }
        return res;
    }

    // Print sudoku
    public void printSudoku() {
        int count = 0;
        String addSpaces = K >= 10 ? " " : "";
        String addDashes = K >= 10 ? "-----------------------" : "";

        System.out.println("-------------------------------------" + addDashes);
        for (int i = 0; i < 9; i++) {
            System.out.print("| ");
            for (int j = 0; j < 9; j++) {
                if (resolution[i][j] == 0) {
                    count++;
                    if (count < 10)
                        System.out.printf("%2$s{%1d}%2$s", count, addSpaces);
                    else
                        System.out.printf("{%1d}%2$s", count, addSpaces);
                }
                else {
                    System.out.printf("%2$s %1$d %2$s",  resolution[i][j], addSpaces);
                }
                if (j % 3 == 2) {
                    System.out.printf("%1$s | %1$s", addSpaces);
                }
            }
            System.out.println();
            if (i % 3 == 2) {
                System.out.println("-------------------------------------" + addDashes);
            }
        }
        System.out.println();
    }
}
