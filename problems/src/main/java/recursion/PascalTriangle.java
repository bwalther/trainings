package recursion;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

    /*
   1
   11
   121
   1331
   14641
   */
    public List<Integer> getRowDP(int rowIndex) {
        List<Integer> prev = new ArrayList<>();
        prev.add(1);
        List<Integer> curr = null;
        for (int i = 1; i <= rowIndex; i++) {
            curr = new ArrayList<Integer>(i + 1);
            curr.add(1);
            for (int j = 1; j < i; j++) {
                curr.add(prev.get(j - 1) + prev.get(j));
            }
            curr.add(1);
            prev = curr;
        }
        return prev;
    } // Time: k^2 Space: k

    public List<Integer> getRowMemDP(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for (int i = 0; i < rowIndex; i++) {
            for (int j = i; j > 0; j--) {
                row.set(j, row.get(j - 1) + row.get(j));
            }
            row.add(1);
        }
        return row;
    } // time: k^2, Space: k


    public List<Integer> getRowRec(int rowIndex) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i <= rowIndex; i++) {
            ans.add(getRowAt(rowIndex, i));
        }

        return ans;
    } // Time: 2^k (k=level, rowIndex), Space: k rec call stacks + k for list = 2k = k

    private Integer getRowAt(int row, int col) {
        if (row == 0 || col == 0 || row == col) return 1;
        return getRowAt(row - 1, col - 1) + getRowAt(row - 1, col);
    }

    public List<Integer> getRowMath(int n) {
        List<Integer> row =
                new ArrayList<>() {
                    {
                        add(1);
                    }
                };

        for (int k = 1; k <= n; k++) {
            row.add((int) ((row.get(row.size() - 1) * (long) (n - k + 1)) / k));
        }

        return row;
    } // math, successive binominal coeff differ by a factor (n-r+1)/r see https://leetcode.com/problems/pascals-triangle-ii/solution/


}

