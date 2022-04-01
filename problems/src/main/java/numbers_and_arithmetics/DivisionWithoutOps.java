package numbers_and_arithmetics;

public class DivisionWithoutOps {

    public static void main(String[] args) {
        long a = 10;
        long b = 3;
        System.out.println("Input:\ta:" + a + " b:" + b + "\nOutput:\t" + divide(a, b));
    }

    public static long divide(long a, long b) {
        int sign = (a < 0) ^ (b < 0) ? -1 : 1;

        long dividend = Math.abs(a);
        long divisor = Math.abs(b);
        long quotient = 0;
        while (dividend >= divisor) {
            dividend -= divisor;
            quotient++;
        }
        //if the sign value computed earlier is -1 then negate the value of quotient
        if (sign == -1) quotient = -quotient;
        return quotient;
    }
}
