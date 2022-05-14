package trees;

import sorting.QuickSelection;

import java.util.*;

public class WordBreak {

    private Trie trie = new Trie();

    class Trie {
        Map<Character, Trie> children;
        boolean wordEnd;

        public Trie() {
            children = new HashMap<>();
        }

        public void insert(String s) {
            Trie node = this;
            for (int i = 0; i < s.length(); i++) {
                if (!node.children.containsKey(s.charAt(i))) {
                    node.children.put(s.charAt(i), new Trie());
                }
                node = node.children.get(s.charAt(i));
            }
            node.wordEnd = true;
        }

        public boolean startsWith(String prefix) {
            Trie node = find(prefix);
            return node != null;
        }

        public boolean search(String word) {
            Trie node = find(word);
            return node != null && node.wordEnd;
        }

        private Trie find(String s) {
            Trie node = this;
            for (int i = 0; i < s.length(); i++) {
                if (node.children.containsKey(s.charAt(i))) {
                    node = node.children.get(s.charAt(i));
                } else {
                    node = null;
                    break;
                }
            }
            return node;
        }

    }

    private Set<String> seen = new HashSet<>();


    public boolean wordBreak(String s, List<String> wordDict) {
        for (String word : wordDict) {
            trie.insert(word);
        }

        return checkWordBreaks(0, 1, s.length(), s);
    }

    private boolean checkWordBreaks(int start, int end, int n, String s) {
        if (end > n || seen.contains(start + "_" + end)) return false;
        seen.add(start + "_" + end);
        String prefix = s.substring(start, end);
        if (trie.startsWith(prefix)) {
            if (trie.search(prefix)) {
                return end == n || checkWordBreaks(end, end + 1, n, s) || checkWordBreaks(start, end + 1, n, s);
            } else {
                return checkWordBreaks(start, end + 1, n, s);
            }
        }
        return false;

    }

    public class DPIterationOverDict {
        public boolean wordBreak(String s, List<String> wordDict) {
            int n = s.length();
            boolean[] dp = new boolean[s.length() + 1];
            dp[s.length()] = true;
            int j = s.length() - 1;
            for (int i = s.length() - 1; i >= 0; i--) {
                for (String word : wordDict) {
                    if (i + word.length() <= n && s.substring(i, i + word.length()).equals(word)) {
                        dp[i] = dp[i + word.length()];
                    }
                    if (dp[i]) {
                        break;
                    }
                }
            }
            return dp[0];
        }
    }

    public class SimpleDPSolution {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDictSet = new HashSet<>(wordDict);
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[s.length()];
        }
    }


    class SolutionTrieAndDP {

        TrieNode root = new TrieNode('#');
        Boolean[] memo = null;

        public boolean wordBreak(String s, List<String> wordDict) {
            memo = new Boolean[s.length()];
            String[] arr = null;
            Arrays.sort(arr);
            QuickSelection

            //Build Trie Tree
            for (String word : wordDict) {
                insertIntoTrie(word);
            }

            return search(s, 0);
        }

        boolean search(String s, int index) {

            TrieNode localRoot = root;

            if (memo[index] != null) {
                return memo[index];
            }

            for (; index < s.length(); ) {
                if (localRoot.children.containsKey(s.charAt(index))) {
                    if (localRoot.children.get(s.charAt(index)).isWord && index == s.length() - 1) {
                        return memo[index] = true;
                    } else if (localRoot.children.get(s.charAt(index)).isWord && search(s, index + 1)) {
                        return memo[index] = true;
                    } else {
                        localRoot = localRoot.children.get(s.charAt(index++));
                    }
                } else {
                    return memo[index++] = false;
                }
            }

            return false;
        }

        void insertIntoTrie(String word) {
            TrieNode localRoot = root;
            for (int i = 0; i < word.length(); i++) {
                if (localRoot.children.get(word.charAt(i)) == null) {
                    localRoot.children.put(word.charAt(i), new TrieNode(word.charAt(i)));
                }
                localRoot = localRoot.children.get(word.charAt(i));
            }
            localRoot.isWord = true;
        }

        class TrieNode {
            Character c;
            Map<Character, TrieNode> children = new HashMap();
            boolean isWord;

            public TrieNode(Character c) {
                this.c = c;
            }
        }
    }

}
