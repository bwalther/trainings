package numbers_and_arithmetics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PalindromeInt {

    public static void main(String[] args) {
        int input = 1_000_000_009;
        System.out.println("Input:\t" + input + "\nOutput:\t" + isPalindrome(input));
    }

    public static boolean isPalindrome(int x) {
        // Special cases:
        // As discussed above, when x < 0, x is not a palindrome.
        // Also if the last digit of the number is 0, in order to be a palindrome,
        // the first digit of the number also needs to be 0.
        // Only 0 satisfy this property.
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        // xn: cutting away 10-er each repetitions
        // revertedNumber is constructed by 10-er cut of xn plus the previous revertedNumer * 10
        // xn decreases with each it, r increases with each it
        // we stop as soon as r overtakes xn

        int xn = x;
        while (xn > revertedNumber) {
            revertedNumber = revertedNumber * 10 + xn % 10;
            xn /= 10;
        }

        // as the number of digits is even or uneven either the xn equals the reverted number OR equals revertedNumber / 10 (depending on cond. where we stopped before)

        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
        // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        return xn == revertedNumber || xn == revertedNumber / 10;
    }

    public static boolean isPalindromeMy(int x) {
        if (x < 0 || x % 10 == 0 && x != 0) {
            return false;
        }
        int res = x;
        int mod = x;
        List<Integer> list = new ArrayList<>();
        while (res > 0) {
            mod = res % 10;
            res = res / 10;
            list.add(mod);
        }
        int half = list.size() / 2;
        int rest = list.size() % 2;
        List left = list.subList(half + rest, list.size());
        Collections.reverse(left);
        List right = list.subList(0, half);
        return toInt(left) == toInt(right);
    }

    static int toInt(Collection<Integer> col) {
        int exp = col.size() - 1;
        int num = 0;
        for (Integer i : col) {
            num += i * Math.pow(10, exp);
            exp--;
        }
        return num;
    }
}
