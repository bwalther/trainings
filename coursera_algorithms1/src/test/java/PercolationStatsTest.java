import org.junit.jupiter.api.Test;
import week1.PercolationStats;

class PercolationStatsTest {

    @Test
    void infixPrefix() {
        int i = 2;
        int[] nums = new int[10];
        for (int j = 0; j < nums.length; j++) {
            nums[i] = i;
        }
        System.out.println("i:" + i);
        System.out.println(nums[i++] + " i:" + i);
        System.out.println("i:" + i);
        int b = --i;
        System.out.println("b:" + b + "i:" + i);
        int c = i--;
        System.out.println("postfix: c:" + c + "i:" + i);
        System.out.println(nums[++i] + " i:" + i);
        System.out.println("i:" + i);
    }

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
