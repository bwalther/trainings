package recursion;

public class PrintReverse {
    public static void main(String[] args) {
        String input = "1234567";
        printReverse(input);
        reverseString(input.toCharArray());
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
}
