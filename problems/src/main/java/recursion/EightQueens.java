package recursion;

import java.util.*;
import java.util.stream.Collectors;

public class EightQueens {

    private static final int BOARD_SIZE = 8;
    Set<Integer> cols = new HashSet<>();
    Set<Integer> posDiag = new HashSet<>();
    Set<Integer> negDiag = new HashSet<>();
    List<String[][]> solutions = new ArrayList<>();
    String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
    final int n;

    public EightQueens(int n) {
        this.n = n;
    }


    public static void main(String[] args) {
        // Test cases
        // print out
        EightQueens q = new EightQueens(8);
        q.solve(0);
        int j = 0;
        for (String[][] sol : q.solutions) {
            System.out.println("Solution: " + j);
            j++;
            for (int i = 0; i < sol.length; i++) {
                System.out.println(Arrays.stream(sol[i]).map(s -> s == null ? "" : s).collect(Collectors.toList()));
            }
        }
    }

    private void solve(int row) {
        // neg / posDiag see https://www.youtube.com/watch?v=Ph95IHmRp5M
        if (row == n) {
            String[][] tmp = new String[n][n];
            for (int i = 0; i < n; i++) {
                tmp[i] = Arrays.copyOf(board[i], n);
            }
            solutions.add(tmp);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!cols.contains(col) && !posDiag.contains(row + col) && !negDiag.contains(row - col)) {
                cols.add(col);
                posDiag.add(row + col);
                negDiag.add(row - col);
                board[row][col] = "Q";

                solve(row + 1);

                cols.remove(col);
                posDiag.remove(row + col);
                negDiag.remove(row - col);
                board[row][col] = null;
            }

        }
    }

}
