package combinatorics;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Permutations {
    // print n! permutation of the characters of the string s (in order)
    public  static void perm1(String s) { perm1("", s); }
    private static void perm1(String prefix, String s) {
        int n = s.length();
        if (n == 0) System.out.println(prefix);
        else {
            for (int i = 0; i < n; i++)
                perm1(prefix + s.charAt(i), s.substring(0, i) + s.substring(i+1, n));
        }

    }

    // print n! permutation of the elements of array a (not in order)
    public static void perm2(String s) {
        int n = s.length();
        char[] a = new char[n];
        for (int i = 0; i < n; i++)
            a[i] = s.charAt(i);
        perm2(a, n);
    }

    // shrink from right side
    private static void perm2(char[] a, int n) {
        if (n == 1) {
            System.out.println(new String(a));
            return;
        }
        for (int i = 0; i < n; i++) {
            swap(a, i, n-1);
            perm2(a, n-1);
            swap(a, i, n-1);
        }
    }

    public static void perm3(String s){
        perm3(s.toCharArray(), 0, s.length());
    }

    // shrink from left side
    private static void perm3(char[] arr, int first, int n){
        if (first == n) System.out.println(new String(arr));
        for (int i = first; i < n; i++) {
            swap(arr, first, i);
            perm3(arr, first+1, n);
            swap(arr,  first, i);
        }
    }



    // swap the characters at indices i and j
    private static void swap(char[] a, int i, int j) {
        char c = a[i];
        a[i] = a[j];
        a[j] = c;
    }



    public static void main(String[] args) {
        //int n = Integer.parseInt(args[0]);
        int n = 3;
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String elements = alphabet.substring(0, n);
        perm1(elements);
        System.out.println();
        perm2(elements);
        System.out.println();
        perm3(elements);
    }
}
