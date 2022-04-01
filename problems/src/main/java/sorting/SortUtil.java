package sorting;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortUtil {

    public static boolean isLess(Comparable one, Comparable two) {
        return one.compareTo(two) < 0;
    }

    public static void exch(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void shuffle(Object[] a) {
        Random r = new Random();
        r.nextInt(a.length);
        for (int i = 0; i < a.length; i++) {
            int rIdx = i + r.nextInt(a.length - i);
            exch(a, i, rIdx);
        }
    }

    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length);
    }

    public static boolean isSorted(Comparable[] a, int low, int high) {
        for (int i = low + 1; i < high; i++) {
            if (isLess(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static Integer[] generateRandomArray(int n) {
        Random r = new Random();
        Integer[] a = new Integer[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(a.length);
        }
        return a;
    }

    public static void sortTest(Consumer<Comparable[]> sorter, Function<Comparable[], int[]> indexSorter) {
        Integer[] a = generateRandomArray(10);
        System.out.println("Sorting:" + Arrays.stream(a).collect(Collectors.toList()));
        if (indexSorter != null) {
            System.out.println("Index sorted:" + Arrays.stream(indexSorter.apply(a)).boxed().collect(Collectors.toList()));
        }
        sorter.accept(a);
        System.out.println("Real sorted:" + Arrays.stream(a).collect(Collectors.toList()));
        System.out.println("Is sorted:" + isSorted(a));
    }

    public static void sortTest(Consumer<Comparable[]> sorter) {
        sortTest(sorter, null);
    }
}
