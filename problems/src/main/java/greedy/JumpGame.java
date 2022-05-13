package greedy;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/jump-game/
public class JumpGame {


    // super simple greedy
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }


    // buttom up dp
    enum Index {GOOD, BAD, UNKNOWN}


    public boolean canJumpDp(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthest = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthest; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }

        return memo[0] == Index.GOOD;
    }

    // backtracking solution with memo
    Set<Integer> checked = new HashSet<>();

    public boolean canJumpit(int[] nums) {
        return nums.length < 2 || checkPath(0, nums, nums.length);
    }

    private boolean checkPath(int start, int[] nums, int n) {
        if (!checked.contains(start)) {
            checked.add(start);
            for (int j = 0; j <= nums[start]; j++) {
                int nextIdx = start + j;
                // base case
                if (nextIdx == n - 1) {
                    return true;
                }
                if ((j != 0) && nextIdx < n && checkPath(nextIdx, nums, n)) {
                    return true;
                }
            }
        }
        return false;
    }
}
