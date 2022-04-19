package recursion;

public class PrintReverse {
    public static void main(String[] args) {
        String input = "1234567";
        printReverse(input);
        reverseString(input.toCharArray());
        char[] ip = "inputz".toCharArray();
        reverseInPlace(ip);
        System.out.println("  Inplace:" + new String(ip));
    }

    public static void reverseString(char[] s) {
        for (int i = s.length - 1; i >= 0; i--) {
            System.out.print(s[i]);
        }
    }

    private static void printReverse(String input) {
        helper(0, input);
    }

    private static void helper(int i, String input) {
        if (i == input.length()) {
            return;
        }
        helper(i + 1, input);
        System.out.print(input.charAt(i));
    }


    private static void reverseInPlace(char[] input) {
        int n = input.length;
        int start = 0;
        int end = n - 1;
        while (start < end) {
            char tmp = input[start];
            input[start] = input[end];
            input[end] = tmp;
            start++;
            end--;
        }
    }

    private static void reverseInPlaceRec(char[] input) {
        helperInPlace(0, input.length - 1, input);
    }

    private static void helperInPlace(int start, int end, char[] s) {
        if (start >= end) {
            return;
        }
        // swap between the first and the last elements.
        char tmp = s[start];
        s[start] = s[end];
        s[end] = tmp;

        helperInPlace(start + 1, end - 1, s);
    }


}
