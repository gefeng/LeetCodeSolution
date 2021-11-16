package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Stream of Characters",
        difficulty = QDifficulty.HARD,
        tag = QTag.TRIE,
        url = "https://leetcode.com/problems/stream-of-characters/"
)
public class Q1032 {
    /**
     * Time:  O(L) for each query
     * Space: O(N * L) we can limit space to L by using deque
     * */
    class TrieNode {
        TrieNode[] chi = new TrieNode[26];
        boolean isWord = false;
    }

    private static final int MAX = 2000;
    private TrieNode root;
    private List<Character> in;

    public Q1032(String[] words) {
        in = new ArrayList<>();

        root = new TrieNode();

        for(String w : words) {
            TrieNode cur = root;
            int len = w.length();
            for(int i = len - 1; i >= 0; i--) {
                char c = w.charAt(i);
                if(cur.chi[c - 'a'] == null) {
                    cur.chi[c - 'a'] = new TrieNode();
                }
                cur = cur.chi[c - 'a'];
            }
            cur.isWord = true;
        }
    }

    public boolean query(char letter) {
        in.add(letter);

        TrieNode cur = root;
        int sz = in.size();
        for(int i = sz - 1; i >= Math.max(0, sz - MAX); i--) {
            char c = in.get(i);

            if(cur.chi[c - 'a'] == null) {
                return false;
            }

            cur = cur.chi[c - 'a'];

            if(cur.isWord) {
                return true;
            }
        }

        return false;
    }
}
