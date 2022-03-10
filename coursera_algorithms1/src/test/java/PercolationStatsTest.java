import org.junit.jupiter.api.Test;

class PercolationStatsTest {

    @Test
    void runs() {
        runOnce(200, 5);
        runOnce(200, 10);
        runOnce(2, 100000);
        runOnce(2, 100000);
    }

    void runOnce(int n, int t) {
        System.out.println(n + " " + t);
        PercolationStats.main(new String[]{n + "", t + ""});
        System.out.println("\n");
    }
}
