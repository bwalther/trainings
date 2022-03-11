import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private final double[] stats;
    private double timeFindingOpen = 0;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (trials <= 0) throw new IllegalArgumentException("invalid trials. Must be > 0");

        stats = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                IntPair randomSite = randomSite(n);
                Stopwatch stopwatch = new Stopwatch();
                while (percolation.isOpen(randomSite.left, randomSite.right)) {
                    randomSite = randomSite(n);
                }
                timeFindingOpen += stopwatch.elapsedTime();
                percolation.open(randomSite.left, randomSite.right);
            }
            double p = percolation.numberOfOpenSites() / (double) (n * n);
            stats[i] = p;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(stats);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(stats);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(stats.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(stats.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args == null || args.length != 2) {
            throw new IllegalArgumentException("Usage: <program> n T (n:grid length, T: number of experiments");
        }
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        Stopwatch stopwatch = new Stopwatch();
        PercolationStats stats = new PercolationStats(n, t);
        System.out.println("mean:\t\t\t\t= " + stats.mean());
        System.out.println("stddev:\t\t\t\t= " + stats.stddev());
        System.out.println("95% confidence interval: = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
        System.out.println("Elapsed time (s):" + stopwatch.elapsedTime() + " (time finding open:)" + stats.timeFindingOpen);
    }

    private static IntPair randomSite(int n) {
        int idx = StdRandom.uniform(n * n);
        return new IntPair(idx / n + 1, idx % n + 1);
    }

    private static class IntPair {
        Integer left;
        Integer right;

        IntPair(Integer pLeft, Integer pRight) {
            left = pLeft;
            right = pRight;
        }

    }

}
