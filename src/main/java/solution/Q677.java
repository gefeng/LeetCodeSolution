package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

@Problem(
        title = "Map Sum Pairs",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TRIE,
        url = "https://leetcode.com/problems/map-sum-pairs/"
)
public class Q677 {
    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int val = 0;
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public Q677() {
        root = new TrieNode();
    }

    /**
     * Time:  O(len(key))
     * Space: O(len(key))
     * */
    public void insert(String key, int val) {
        TrieNode curr = root;

        for(int i = 0; i < key.length(); i++) {
            int c = key.charAt(i) - 'a';
            if(curr.children[c] == null) {
                curr.children[c] = new TrieNode();
            }
            curr = curr.children[c];
        }
        curr.val = val;
    }

    /**
     * N: number of words start with prefix
     * L: maximum length of words start with prefix
     *
     * Time:  O(N * L)
     * Space: O(L)
     * */
    public int sum(String prefix) {
        TrieNode curr = root;
        int sum = 0;

        for(int i = 0; i < prefix.length(); i++) {
            int c = prefix.charAt(i) - 'a';
            if(curr.children[c] == null) {
                return 0;
            }
            curr = curr.children[c];
        }

        return dfs(curr);
    }

    private int dfs(TrieNode node) {
        int sum = 0;

        sum = node.val;

        for(int i = 0; i < 26; i++) {
            if(node.children[i] != null) {
                sum += dfs(node.children[i]);
            }
        }

        return sum;
    }
}
