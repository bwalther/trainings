package arrays;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RotateArray {

    static Pattern parsePattern = Pattern.compile("\\[([\\d,]+)\\]\\s+(\\d)\\s+\\[([\\d,]+)\\]$");

    static class TestCase {
        int[] input;
        String inputStr;
        String expected;
        int k;

        TestCase(String def) {
            Matcher m = parsePattern.matcher(def);
            m.find();
            this.inputStr = m.group(1);
            this.input = parseToIntArray(m.group(1));
            this.k = Integer.parseInt(m.group(2).trim());
            this.expected = m.group(3);
        }

        int[] parseToIntArray(String s) {
            return Arrays.stream(s.trim().split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        }
    }

    public static void main(String[] args) {
        // rotate tmp array
        TestCase testCase = new TestCase("[1,2,3] 4 [3,1,2]");
        System.out.println("input:" + testCase.inputStr + " k:" + testCase.k + " expected:" + testCase.expected);
        rotate_tmpArray(testCase.input, testCase.k);
        System.out.println("output:" + Arrays.stream(testCase.input).boxed().collect(Collectors.toList()));

        // rotate reverse
        testCase = new TestCase("[1,2,3] 4 [3,1,2]");
        rotate_reverse(testCase.input, testCase.k);
        System.out.println("output:" + Arrays.stream(testCase.input).boxed().collect(Collectors.toList()));

        // rotate modula
        testCase = new TestCase("[1,2,3] 4 [3,1,2]");
        rotate_modulo(testCase.input, testCase.k);
        System.out.println("output:" + Arrays.stream(testCase.input).boxed().collect(Collectors.toList()));
    }

    public static void rotate_tmpArray(int[] nums, int k) {

        int n = nums.length;
        k = k % n;
        // fill tmp array with el to shift
        int tmp[] = new int[k];
        int j = 0;
        for (int i = n - k; i < n; i++) {
            tmp[j] = nums[i];
            j++;
        }

        // shift orig array
        for (int i = n - 1; (i - k) >= 0; i--) {
            nums[i] = nums[i - k];
        }

        // copy back tmp array
        for (int i = 0; i < tmp.length; i++) {
            nums[i] = tmp[i];
        }
    }

    public static void rotate_juggling(int[] nums, int k) {
        //TODO
    }


    private static int gcd(int a, int b) {
        // Euclidean algorithm
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }


    public static void rotate_modulo(int[] nums, int k) {
        int[] tmp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            tmp[(k + i) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = tmp[i];
        }
    }

    public static void rotate_reverse(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[end];
            nums[end] = nums[start];
            nums[start] = tmp;
            start++;
            end--;
        }
    }

    public static void rotate_bubbingUp(int[] nums, int k) {
        k %= nums.length;
        int temp, previous;
        for (int i = 0; i < k; i++) {
            previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }
}
