import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class Percolation {

    private final int n;

    private final WeightedUnionFind weightedUnionFind;
    private final boolean[] open;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;
        if (n <= 0) throw new IllegalArgumentException("invalid size. Must be > 0");
        weightedUnionFind = new WeightedUnionFind(n * n);
        open = new boolean[n * n];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        Pair<Integer, Integer> rowCol = rangeCheck(row, col);
        row = rowCol.getLeft();
        col = rowCol.getRight();
        int idx = toIndex(row, col);
        if (!open[idx]) {
            open[idx] = true;
            List<Pair<Integer, Integer>> unionCandidates = new ArrayList<>();
            if (row > 0) {
                unionCandidates.add(Pair.of(row - 1, col));
            }
            if (row < n - 1) {
                unionCandidates.add(Pair.of(row + 1, col));
            }
            if (col > 0) {
                unionCandidates.add(Pair.of(row, col - 1));
            }
            if (col < n - 1) {
                unionCandidates.add(Pair.of(row, col + 1));
            }
            // union all connecting squares if they are open (check 9 pieces)
            unionCandidates
                    .stream()
                    .filter(p -> isOpenInternal(p.getLeft(), p.getRight()))
                    .forEach(p -> weightedUnionFind.union(idx, toIndex(p.getLeft(), p.getRight())));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        Pair<Integer, Integer> rowCol = rangeCheck(row, col);
        row = rowCol.getLeft();
        col = rowCol.getRight();
        return isOpenInternal(row, col);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        Pair<Integer, Integer> rowCol = rangeCheck(row, col);
        row = rowCol.getLeft();
        col = rowCol.getRight();
        boolean full = false;
        int idx = toIndex(row, col);
        if (!open[idx]) return false;
        for (int i = 0; i < n; i++) {
            if (open[i] && weightedUnionFind.connected(i, idx)) {
                full = true;
                break;
            }
        }
        return full;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (boolean b : open) {
            if (b) {
                count++;
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        boolean res = false;
        for (int i = 1; i <= n; i++) {
            if (isFull(n, i)) {
                res = true;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(1, 2);
        percolation.open(2, 2);
        percolation.open(3, 2);
        percolation.open(3, 3);
        percolation.open(4, 3);
        percolation.open(4, 4);
        percolation.open(5, 4);
        System.out.printf("Should percolate: %s%n", percolation.percolates());
    }

    private int toIndex(int row, int col) {
        return row * n + col;
    }

    private boolean isOpenInternal(int row, int col) {
        return open[toIndex(row, col)];
    }

    private Pair<Integer, Integer> rangeCheck(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) {
            throw new IllegalArgumentException("invalid size. Must between 1 and " + n);
        }
        return Pair.of(row - 1, col - 1);
    }
}
