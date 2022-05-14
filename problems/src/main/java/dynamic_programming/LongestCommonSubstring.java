package dynamic_programming;

public class LongestCommonSubstring {

    public static void main(String[] args) {
        String a = "war einmal";
        String b = "esxwar ein mal";
        System.out.println("a:" + a + " b:" + b + " length substring:" + lcsLength(a, b));
        System.out.println("a:" + a + " b:" + b + " space lenght opt. substring:" + lcsLengthSpaceOptimized(a, b));
        System.out.println("a:" + a + " b:" + b + " substring:" + lcs(a, b));

        String X = "OldSite:GeeksforGeeks.org";
        String Y = "NewSite:GeeksQuiz.com";
        System.out.println("x:" + X + " y:" + Y + " substring:" + lcs(X, Y));

        int m = X.length();
        int n = Y.length();
        geek4geeksLcsSolution(X, Y, m, n);
    }

    public static int lcsLength(String a, String b) {
        int[][] memo = new int[a.length() + 1][b.length() + 1];
        int res = 0;
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0 || j == 0) {
                    memo[i][j] = 0;
                } else {
                    if (a.charAt(i - 1) == b.charAt(j - 1)) {
                        memo[i][j] = memo[i - 1][j - 1] + 1;
                        res = Math.max(memo[i][j], res);
                    } else {
                        memo[i][j] = 0;
                    }
                }
            }
        }
        return res;
    }

    public static int lcsLengthSpaceOptimized(String a, String b) {

        if (a.length() < b.length()) {
            String tmp = b;
            b = a;
            a = tmp;
        }
        int[] previous = new int[b.length() + 1];
        int[] current;

        int res = 0;
        for (int i = 0; i < a.length(); i++) {
            current = new int[b.length() + 1];
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    current[j + 1] = previous[j] + 1;
                    res = Math.max(current[j + 1], res);
                } else {
                    current[j + 1] = 0;
                }
            }
            previous = current;
        }
        return res;
    }

    public static String lcs(String a, String b) {
        if (a.length() < b.length()) {
            String tmp = b;
            b = a;
            a = tmp;
        }
        int[] previous = new int[b.length() + 1];
        int[] current;

        int res = 0;
        int maxi = -1;
        for (int i = 0; i < a.length(); i++) {
            current = new int[b.length() + 1];
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    current[j + 1] = previous[j] + 1;
                    if (current[j + 1] > res) {
                        maxi = i;
                        res = current[j + 1];
                    }
                } else {
                    current[j + 1] = 0;
                }
            }
            previous = current;
        }
        if (res > 0) {
            return a.substring(maxi - res + 1, maxi + 1);
        }
        return null;
    }

    static void geek4geeksLcsSolution(String X, String Y, int m, int n) {
        // Create a table to store lengths of longest common
        // suffixes of substrings.   Note that LCSuff[i][j]
        // contains length of longest common suffix of X[0..i-1]
        // and Y[0..j-1]. The first row and first column entries
        // have no logical meaning, they are used only for
        // simplicity of program
        int[][] LCSuff = new int[m + 1][n + 1];

        // To store length of the longest common substring
        int len = 0;

        // To store the index of the cell which contains the
        // maximum value. This cell's index helps in building
        // up the longest common substring from right to left.
        int row = 0, col = 0;

        /* Following steps build LCSuff[m+1][n+1] in bottom
           up fashion. */
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    LCSuff[i][j] = 0;

                else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    LCSuff[i][j] = LCSuff[i - 1][j - 1] + 1;
                    if (len < LCSuff[i][j]) {
                        len = LCSuff[i][j];
                        row = i;
                        col = j;
                    }
                } else
                    LCSuff[i][j] = 0;
            }
        }

        // if true, then no common substring exists
        if (len == 0) {
            System.out.println("No Common Substring");
            return;
        }

        // allocate space for the longest common substring
        String resultStr = "";

        // traverse up diagonally form the (row, col) cell
        // until LCSuff[row][col] != 0
        while (LCSuff[row][col] != 0) {
            resultStr = X.charAt(row - 1) + resultStr; // or Y[col-1]
            --len;

            // move diagonally up to previous cell
            row--;
            col--;
        }

        // required longest common substring
        System.out.println(resultStr);
    }


}
