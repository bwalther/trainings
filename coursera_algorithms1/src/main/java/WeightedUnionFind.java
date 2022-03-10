public class WeightedUnionFind {

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
