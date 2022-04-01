package sorting;

import static sorting.SortUtil.*;

public class Insertion {

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0 && isLess(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static int[] indexSort(Comparable[] a) {
        int[] index = new int[a.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0 && isLess(a[j], a[j - 1]); j--) {
                exch(index, j, j - 1);
            }
        }
        return index;
    }


    public static void main(String[] args) {
        sortTest(Insertion::sort, Insertion::indexSort);
    }


}
