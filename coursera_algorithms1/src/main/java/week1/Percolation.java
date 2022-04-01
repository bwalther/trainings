package week1;

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
        IntPair rowCol = rangeCheck(row, col);
        row = rowCol.left;
        col = rowCol.right;
        int idx = toIndex(row, col);
        if (!open[idx]) {
            open[idx] = true;
            if (row > 0 && isOpenInternal(row - 1, col)) {
                weightedUnionFind.union(idx, toIndex(row - 1, col));
            }
            if (row < n - 1 && isOpenInternal(row + 1, col)) {
                weightedUnionFind.union(idx, toIndex(row + 1, col));
            }
            if (col > 0 && isOpenInternal(row, col - 1)) {
                weightedUnionFind.union(idx, toIndex(row, col - 1));
            }
            if (col < n - 1 && isOpenInternal(row, col + 1)) {
                weightedUnionFind.union(idx, toIndex(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        IntPair rowCol = rangeCheck(row, col);
        row = rowCol.left;
        col = rowCol.right;
        return isOpenInternal(row, col);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        IntPair rowCol = rangeCheck(row, col);
        row = rowCol.left;
        col = rowCol.right;
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

        Percolation percolation = new Percolation(3);
        percolation.open(1, 2);
        percolation.open(2, 2);
        percolation.open(3, 2);
        System.out.printf("Should percolate: %s", percolation.percolates());

        percolation = new Percolation(5);
        percolation.open(1, 2);
        percolation.open(2, 2);
        percolation.open(3, 2);
        percolation.open(3, 3);
        percolation.open(4, 3);
        percolation.open(4, 4);
        percolation.open(5, 4);
        System.out.printf("Should percolate: %s", percolation.percolates());

    }

    private int toIndex(int row, int col) {
        return row * n + col;
    }

    private boolean isOpenInternal(int row, int col) {
        return open[toIndex(row, col)];
    }

    private IntPair rangeCheck(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) {
            throw new IllegalArgumentException("invalid size. Must between 1 and " + n);
        }
        return new IntPair(row - 1, col - 1);
    }

    private static class IntPair {
        Integer left;
        Integer right;

        IntPair(Integer pLeft, Integer pRight) {
            left = pLeft;
            right = pRight;
        }

    }

    private static class WeightedUnionFind {

        final int n;
        int[] parent;
        int[] size;

        public WeightedUnionFind(int n) {
            this.n = n;
            this.parent = new int[n];
            this.size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int p, int q) {
            int rootP = root(p);
            int rootQ = root(q);
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] = rootP;
            }
        }

        public boolean connected(int p, int j) {
            return root(p) == root(j);
        }

        private int root(int i) {
            while (i != parent[i]) {
                i = parent[i];
            }
            return i;
        }

    }

}
