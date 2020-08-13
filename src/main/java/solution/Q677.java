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
    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<>();
        int value = 0;
        int sumAsPrefix = 0;
    }

    TrieNode root;

    /** Initialize your data structure here. */
    public Q677() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode curr = root;
        for(int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if(!curr.children.containsKey(c))
                curr.children.put(c, new TrieNode());
            curr = curr.children.get(c);
        }
        int delta = val - curr.value;
        curr.value = val;
        if(delta != 0) {
            curr = root;
            for(int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                curr = curr.children.get(c);
                curr.sumAsPrefix += delta;
            }
        }
    }

    public int sum(String prefix) {
        TrieNode curr = root;
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            TrieNode next = curr.children.get(c);
            if(next == null)
                return 0;
            curr = next;
        }
        return curr.sumAsPrefix;
    }
}
