package numbers_and_arithmetics;

import java.util.Arrays;
import java.util.stream.Collectors;

//https://leetcode.com/problems/squares-of-a-sorted-array/submissions/
public class SortingSquares {

    public static void main(String[] args) {
        int[] input = new int[]{-4, -1, 0, 3, 10};
        int[] input2 = new int[]{-40, -30, -10, -3, 5};
        int[] input3 = new int[]{};
        System.out.println(Arrays.stream(sortedSquares(input)).boxed().collect(Collectors.toList()));
        System.out.println(Arrays.stream(sortedSquares(input2)).boxed().collect(Collectors.toList()));
        System.out.println(Arrays.stream(sortedSquares(input3)).boxed().collect(Collectors.toList()));
    }

    public static int[] sortedSquares(int[] nums) {
        int[] neg = new int[nums.length];
        int[] squares = new int[nums.length];
        int j = 0;
        int j2 = 0;
        int s = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                neg[j++] = Math.abs(nums[i]);
                j2 = j - 1;
            } else {
                while (j2 >= 0 && neg[j2] < nums[i]) {
                    squares[s++] = neg[j2] * neg[j2];
                    j2--;
                }
                squares[s++] = nums[i] * nums[i];
            }
        }
        for (int z = j2; z >= 0; z--) {
            squares[s++] = neg[z] * neg[z];
        }
        return squares;
    }
}
