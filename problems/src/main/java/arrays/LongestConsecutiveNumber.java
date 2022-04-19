package arrays;

import java.util.HashSet;
import java.util.Set;

// see https://leetcode.com/problems/longest-consecutive-sequence/submissions/
public class LongestConsecutiveNumber {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            numSet.add(nums[i]);
        }

        int maxStreak = 0;

        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int incr = 1;
                while (numSet.contains(num + incr)) {
                    incr++;
                }
                maxStreak = Math.max(maxStreak, incr);
            }
        }
        return maxStreak;
    }
}
