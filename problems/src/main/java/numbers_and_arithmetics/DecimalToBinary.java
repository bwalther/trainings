package numbers_and_arithmetics;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecimalToBinary {
    public static void main(String[] args) {
        int n = 50;
        System.out.println("Converting n:" + n + " to binary");
        System.out.println("java built-in:" + Integer.toBinaryString(n));
        System.out.println("using stack:" + decimalToBinaryStack(n));
        System.out.println("reverse:" + binaryToDecimal(decimalToBinaryStack(n)));

    }

    private static int binaryToDecimal(String binary) {
        int sum = 0;
        for (int i = 0; i < binary.length(); i++) {
            sum += Math.pow(2, i) * Integer.parseInt(binary.charAt(binary.length() - i - 1) + "");
        }
        return sum;
    }

    private static String decimalToBinaryStack(int n) {
        //attention do not use Stack interface!!!!Java Stack class iteration causes stack elements to be printed in FIFO order instead of the expected LIFO order.
        /*
        All Deque implementations like ArrayDeque, LinkedList, etc., use “double ended queue,”
        which provides a more complete and consistent set of LIFO and FIFO operations.
        For example, the iterator() method on Deque iterates through the stack from top to bottom:
         */
        Deque<Integer> stack = new ArrayDeque<>();
        while (n > 0) {
            stack.push(n % 2);
            n = n / 2;
        }
        StringBuilder sb = new StringBuilder();
        for (int digit : stack) {
            sb.append(digit);
        }
        return sb.toString();
    }
}
