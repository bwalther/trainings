package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {

    public static void main(String[] args) {
        List<String> ans = calcPerm("abc");
        System.out.println("size:" + ans.size() + " content:" + ans);
        System.out.println(new Permutations().permute(new int[]{1, 2, 3}));
        perm2("123");
    }


    // print n! permutation of the elements of array a (not in order)
    public static void perm2(String s) {
        int n = s.length();
        char[] a = new char[n];
        for (int i = 0; i < n; i++)
            a[i] = s.charAt(i);
        perm2(a, n);
    }

    private static void perm2(char[] a, int n) {
        if (n == 1) {
            System.out.print(new String(a) + ", ");
            return;
        }
        for (int i = 0; i < n; i++) {
            swap(a, i, n - 1);
            perm2(a, n - 1);
            swap(a, i, n - 1);
        }
    }

    // swap the characters at indices i and j
    private static void swap(char[] a, int i, int j) {
        char c = a[i];
        a[i] = a[j];
        a[j] = c;
    }

    // SWAP Solution
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> numList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        perm(result, numList, 0, nums.length);
        return result;
    }

    private void perm(List<List<Integer>> result, List<Integer> numList, int first, int n) {
        if (first == n) result.add(new ArrayList<Integer>(numList));
        for (int i = first; i < n; i++) {
            Collections.swap(numList, first, i);
            perm(result, numList, first + 1, n);
            Collections.swap(numList, i, first);
        }
    }

    // for strings
    private static List<String> calcPerm(String s) {
        List<String> ans = new ArrayList<>();
        calcPerm("", s, ans);
        return ans;
    }

    private static void calcPerm(String prefix, String s, List<String> ans) {
        int n = s.length();
        if (n == 0) ans.add(prefix);
        for (int i = 0; i < n; i++) {
            calcPerm(prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1, n), ans);
        }
    }
}
