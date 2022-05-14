package recursion;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        System.out.println(new Subsets().subsetsBitmask(new int[]{1, 2, 3,}));
    }

    //bitmask magic solution
    public List<List<Integer>> subsetsBitmask(int[] nums) {
        List<List<Integer>> output = new ArrayList();
        int n = nums.length;

        for (int i = (int) Math.pow(2, n); i < (int) Math.pow(2, n + 1); ++i) {
            // generate bitmask, from 0..00 to 1..11
            String bitmask = Integer.toBinaryString(i).substring(1);

            // append subset corresponding to that bitmask
            List<Integer> curr = new ArrayList();
            for (int j = 0; j < n; ++j) {
                if (bitmask.charAt(j) == '1') curr.add(nums[j]);
            }
            output.add(curr);
        }
        return output;
    }


    public List<List<Integer>> subsetsCascading(int[] nums) {
        List<List<Integer>> output = new ArrayList();
        output.add(new ArrayList<Integer>());

        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList();
            for (List<Integer> curr : output) {
                newSubsets.add(new ArrayList<Integer>(curr) {{
                    add(num);
                }});
            }
            for (List<Integer> curr : newSubsets) {
                output.add(curr);
            }
        }
        return output;
    } // T: O(n*2^n) S: O(n*2^n)

    public List<List<Integer>> subsetsBacktracking(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), nums, 0);
        return res;
    }

    // alternative backtracking solution
    class Solution {
        List<List<Integer>> output = new ArrayList();
        int n, k;

        public void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
            // if the combination is done
            if (curr.size() == k) {
                output.add(new ArrayList(curr));
                return;
            }
            for (int i = first; i < n; ++i) {
                // add i into the current combination
                curr.add(nums[i]);
                // use next integers to complete the combination
                backtrack(i + 1, curr, nums);
                // backtrack
                curr.remove(curr.size() - 1);
            }
        }

        public List<List<Integer>> subsets(int[] nums) {
            n = nums.length;
            for (k = 0; k < n + 1; ++k) {
                backtrack(0, new ArrayList<Integer>(), nums);
            }
            return output;
        }
    }

    private void dfs(List<List<Integer>> res, List<Integer> subset, int[] nums, int i) {
        if (i >= nums.length) {
            res.add(new ArrayList<Integer>(subset));
            return;
        }
        // decision to inlucde
        subset.add(nums[i]);
        dfs(res, subset, nums, i + 1);

        // decision Not to include
        subset.remove(subset.size() - 1);
        dfs(res, subset, nums, i + 1);

    }
}
