package numbers_and_arithmetics;

import java.util.ArrayList;
import java.util.List;

public class PrintPrimes {
    // Sieve of Eratosthenes
    public static void main(String[] args) {
        System.out.println(calcPrimesUpTo(100));
    }

    private static List<Integer> calcPrimesUpTo(int n) {
        List<Integer> res = new ArrayList<>();
        if (n <= 2) return res;
        boolean[] isPrime = new boolean[n];
        for (int i = 0; i < n; i++) {
            isPrime[i] = true;
        }
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        for (int i = 0; i < isPrime.length; i++) {
            if (isPrime[i]) {
                res.add(i);
            }
        }
        return res;
    }
}
