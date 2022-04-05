package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Implement Trie II (Prefix Tree)",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TRIE,
        url = "https://leetcode.com/problems/implement-trie-ii-prefix-tree/"
)
public class Q1804 {
    TrieNode root;
    public Q1804() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = root;
        int n = word.length();

        for(int i = 0; i < n; i++) {
            int c = word.charAt(i) - 'a';

            if(cur.children[c] == null) {
                cur.children[c] = new TrieNode();
            }
            cur = cur.children[c];
            cur.cnt1++;
        }

        cur.cnt2++;
    }

    public int countWordsEqualTo(String word) {
        TrieNode cur = root;
        int n = word.length();

        for(int i = 0; i < n; i++) {
            int c = word.charAt(i) - 'a';

            if(cur.children[c] == null) {
                return 0;
            }

            cur = cur.children[c];
        }

        return cur.cnt2;
    }

    public int countWordsStartingWith(String prefix) {
        TrieNode cur = root;
        int n = prefix.length();

        for(int i = 0; i < n; i++) {
            int c = prefix.charAt(i) - 'a';

            if(cur.children[c] == null) {
                return 0;
            }

            cur = cur.children[c];
        }

        return cur.cnt1;
    }

    public void erase(String word) {
        TrieNode cur = root;
        int n = word.length();

        for(int i = 0; i < n; i++) {
            int c = word.charAt(i) - 'a';

            if(--cur.children[c].cnt1 == 0) {
                cur.children[c] = null;
                return;
            }

            cur = cur.children[c];
        }

        cur.cnt2--;
    }

    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int cnt1 = 0;  // count prefix
        int cnt2 = 0;  // count words
    }
}
