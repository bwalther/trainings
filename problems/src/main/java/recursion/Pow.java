package recursion;

public class Pow {

    public static void main(String[] args) {
        System.out.println(new Pow().myPowIt(2.00000, -2147483648));
        System.out.println(new Pow().myPow(2.0, -2));
        System.out.println(new Pow().myPowIt(2.0, -2));
        System.out.println(new Pow().myPowIt(2.0, 1));
        System.out.println(new Pow().myPow(0.00001, 200));
    }

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 1 / (x * myPow(x, -n - 1));
        }
        return x * myPow(x, n - 1);
    }

    public double myPowIt(double base, int exp) {
        long n = Math.abs((long) exp);
        if (n == 0 || base == 1) {
            return 1;
        }
        double res = base;
        for (long i = 1; i < n; i++) {
            res = res * base;
        }
        if (exp < 0) {
            res = 1 / res;
        }
        return res;
    }


    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    public double myPowRec(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        return fastPow(x, N);
    }

    public double myPowItBest(double x, int n) {
        long exp = n;
        if (exp < 0) {
            exp = -exp;
            x = 1 / x;
        }
        double res = 1;
        double cur = x;
        for (long i = exp; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                res = res * cur;
            }
            cur = cur * cur;
        }
        return res;
    }
}
