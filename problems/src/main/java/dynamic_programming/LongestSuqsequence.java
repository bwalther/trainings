package dynamic_programming;

public class LongestSuqsequence {

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println("Input:\ttext1:" + text1 + " text2:" + text2);
        System.out.println("Output:\t" + new LongestSuqsequence().lcsMemoization(text1, text2));
    }

    private int[][] memo;
    private String text1;
    private String text2;

    // button up, tabulation algo
    public int lcsDP(String text1, String text2) {
        memo = new int[text1.length() + 1][text2.length() + 1];

        for (int row = text1.length() - 1; row >= 0; row--) {
            for (int col = text2.length() - 1; col >= 0; col--) {
                if (text1.charAt(row) == text2.charAt(col)) {
                    memo[row][col] = 1 + memo[row + 1][col + 1];
                } else {
                    memo[row][col] = Math.max(memo[row + 1][col], memo[row][col + 1]);
                }
            }
        }
        return memo[0][0];
    }

    // we do not use full matrix but only 2 cols.
    // time: O(M*N)
    // space: O(min(M,N))
    public int lcsDPOptimizedSpace(String text1, String text2) {
        String shortTxt = text1;
        String longTxt = text2;
        if (text1.length() > text2.length()) {
            shortTxt = text2;
            longTxt = text1;
        }
        int[] current = new int[shortTxt.length() + 1];
        int[] previous = new int[shortTxt.length() + 1];
        for (int col = longTxt.length() - 1; col >= 0; col--) {
            for (int row = shortTxt.length() - 1; row >= 0; row--) {
                if (shortTxt.charAt(row) == longTxt.charAt(col)) {
                    current[row] = 1 + previous[row + 1];
                } else {
                    current[row] = Math.max(current[row + 1], previous[row]);
                }
            }
            // for correctness we need to use the current as previous for next iteration.
            //   and the current would ideally be empty.
            // this could be done with new current array creation each iteration OR better
            // with this swap, thereby reusing the array.

            // The current column becomes the previous one, and vice versa.
            int[] temp = previous;
            previous = current;
            current = temp;
        }
        return previous[0];
    }


    //top-down, recursive memoization algo
    // time:  O(M*N) since m*n char-pairs, m/n being length of text1/text2
    // space: O(M*N) for memo 2-dim array
    public int lcsMemoization(String text1, String text2) {
        this.memo = new int[text1.length() + 1][text2.length() + 1];
        // keep '0' in last row/col of memo
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                memo[i][j] = -1;
            }
        }
        if (text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        this.text1 = text1;
        this.text2 = text2;
        return memoSolve(0, 0);
    }

    private int memoSolve(int pos1, int pos2) {
        if (memo[pos1][pos2] != -1) {
            return memo[pos1][pos2];
        }
        int answer = 0;
        if (text1.charAt(pos1) == text2.charAt(pos2)) {
            answer = 1 + memoSolve(pos1 + 1, pos2 + 1);
        } else {
            answer = Math.max(memoSolve(pos1, pos2 + 1), memoSolve(pos1 + 1, pos2));
        }

        memo[pos1][pos2] = answer;
        return answer;
    }
}
