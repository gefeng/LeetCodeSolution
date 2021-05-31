package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Prefix and Suffix Search",
        difficulty = QDifficulty.HARD,
        tag = QTag.TRIE,
        url = "https://leetcode.com/problems/prefix-and-suffix-search/"
)
public class Q745 {
    /*
    * wrapped suffix,
    * given a word i.e. apple
    * insert e-apple, le-apple, ple-apple, pple-apple, apple-apple to trie and update last index
    * once required to search prefix = "ap" suffix = "e"
    * we can search "e-ap"
    * */
    private class TrieNode {
        TrieNode[] children;
        int lastIdx;
        TrieNode() {
            children = new TrieNode[27];
        }
    }

    TrieNode root;
    public Q745(String[] words) {
        root = new TrieNode();
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            for(int j = 0; j < word.length(); j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(word.substring(j, word.length())).append("-");
                sb.append(word);
                insert(sb.toString(), i);

            }
        }
    }

    public int f(String prefix, String suffix) {
        StringBuilder sb = new StringBuilder();
        sb.append(suffix).append("-").append(prefix);
        int lastIdx = search(sb.toString());
        return lastIdx;
    }

    private void insert(String s, int idx) {
        TrieNode curr = root;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int j = c == '-' ? 26 : c - 'a';
            if(curr.children[j] == null) {
                curr.children[j] = new TrieNode();
            }
            curr = curr.children[j];
            curr.lastIdx = idx;
        }
    }

    private int search(String s) {
        TrieNode curr = root;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int j = c == '-' ? 26 : c - 'a';
            if(curr.children[j] == null) {
                return -1;
            }
            curr = curr.children[j];
        }
        return curr.lastIdx;
    }
}
