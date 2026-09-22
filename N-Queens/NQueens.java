import java.util.*;

public class NQueens {

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();

        char[][] board = new char[n][n];

        // Initially empty board
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        solve(0, board, result, n);

        return result;
    }

    static void solve(int row, char[][] board,
                      List<List<String>> result, int n) {

        // All queens placed
        if (row == n) {
            List<String> solution = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                solution.add(new String(board[i]));
            }

            result.add(solution);
            return;
        }

        // Try every column
        for (int col = 0; col < n; col++) {

            if (isSafe(row, col, board, n)) {

                // Place queen
                board[row][col] = 'Q';

                // Move to next row
                solve(row + 1, board, result, n);

                // Backtrack
                board[row][col] = '.';
            }
        }
    }

    static boolean isSafe(int row, int col,
                          char[][] board, int n) {

        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0;
             i--, j--) {

            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n;
             i--, j++) {

            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        int n = 4;

        List<List<String>> solutions = solveNQueens(n);

        for (List<String> solution : solutions) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}