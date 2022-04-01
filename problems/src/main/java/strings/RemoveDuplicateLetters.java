package strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class RemoveDuplicateLetters {

    public static void main(String[] args) {
        String s = "bacacb";
        System.out.println("Input:\t" + s + "\nOutput:\t" + removeDuplicateLetters(s));
    }

    public static String removeDuplicateLetters(String s) {

        Stack<Character> stack = new Stack<>();

        // this lets us keep track of what's in our solution in O(1) time
        HashSet<Character> seen = new HashSet<>();

        // this will let us know if there are any more instances of s[i] left in s
        HashMap<Character, Integer> last_occurrence = new HashMap<>();
        for (int i = 0; i < s.length(); i++) last_occurrence.put(s.charAt(i), i);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // we can only try to add c if it's not already in our solution
            // this is to maintain only one of each character
            if (!seen.contains(c)) {
                // if stack not empty and
                // current char is smaller than last on stack, and the char last on stack occurs also later in the string
                // -> then we can remove last char from stack it from the stack and seen set
                while (!stack.isEmpty() && c < stack.peek() && last_occurrence.get(stack.peek()) > i) {
                    seen.remove(stack.pop());
                }
                seen.add(c);
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder(stack.size());
        for (Character c : stack) sb.append(c.charValue());
        return sb.toString();
    }
}
