package arrays;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SocksColors {
    public static void main(String[] args) {
        List<Integer> input = List.of(10, 20, 20, 10, 10, 30, 50, 10, 20);
        System.out.println(sockMerchant(input.size(), input));
    }

    public static int sockMerchant(int n, List<Integer> ar) {
        // Write your code here
        Set<Integer> set = new HashSet<>();
        int pairs = 0;
        for (Integer color : ar) {
            if (set.contains(color)) {
                pairs++;
                set.remove(color);
            } else {
                set.add(color);
            }
        }
        return pairs;
    }
}
