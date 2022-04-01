package sorting;

import static sorting.SortUtil.*;

public class Shell {

    public static void sort(Comparable[] a) {
        // insertion sort with stride lenght h = 3x+1
        int h = 1;
        while (h < a.length / 3) {
            h = 3 * h + 1;
        }
        while (h > 0) {
            for (int i = 0; i < a.length && i + h < a.length; i += h) {
                for (int j = i; j - h >= 0 && isLess(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        sortTest(Shell::sort);
    }
}
