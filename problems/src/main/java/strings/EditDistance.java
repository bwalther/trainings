package strings;

import java.util.ArrayList;
import java.util.List;

public class EditDistance {
    static class TestInput {
        final String left;
        final String right;
        final boolean expected;

        TestInput(String left, String right, boolean expected) {
            this.left = left;
            this.right = right;
            this.expected = expected;
        }
    }

    public static boolean oneOrZeroEditsAway(String one, String two) {
        // max 1 apart
        if (Math.abs(one.length() - two.length()) > 1) {
            return false;
        }
        //
        boolean added = one.length() < two.length();
        boolean removed = !added && one.length() > two.length();
        int diffCount = 0;
        boolean replaced = false;
        for (int i = 0; i < one.length(); i++) {
            for (int j = 0; j < two.length(); j++) {

            }
            if (removed) {
                if (two.length() == i) {
                    diffCount++;
                } else if (one.charAt(i) != two.charAt(i)) {

                }
            }
            if (!removed && !added && one.charAt(i) != two.charAt(i)) {
                if (replaced) {
                    return false;
                }
                replaced = true;
            }
        }
        return replaced;
    }

    public static void main(String[] args) {
        List<TestInput> testInputList = new ArrayList<>();
        testInputList.add(new TestInput("pale", "ple", true));
        testInputList.add(new TestInput("pales", "pale", true));
        testInputList.add(new TestInput("pale", "bale", true));
        testInputList.add(new TestInput("pale", "bae", false));
        for (TestInput input : testInputList) {
            System.out.println("Input:" + input.left + ", " + input.right + " result:" + oneOrZeroEditsAway(input.left, input.right) + " should be:" + input.expected);
        }
    }
}
