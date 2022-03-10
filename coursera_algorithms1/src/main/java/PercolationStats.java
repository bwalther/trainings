import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;
import org.apache.commons.lang3.tuple.Pair;

public class PercolationStats {
    final double[] stats;
    double timeFindingOpen = 0;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        stats = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                Pair<Integer, Integer> randomSite = randomSite(n);
                Stopwatch stopwatch = new Stopwatch();
                while (percolation.isOpen(randomSite.getLeft(), randomSite.getRight())) {
                    randomSite = randomSite(n);
                }
                timeFindingOpen += stopwatch.elapsedTime();
                percolation.open(randomSite.getLeft(), randomSite.getRight());
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

    private static Pair<Integer, Integer> randomSite(int n) {
        int idx = StdRandom.uniform(n * n);
        return Pair.of(idx / n + 1, idx % n + 1);
    }

}
