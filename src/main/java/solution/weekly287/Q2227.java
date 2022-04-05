package solution.weekly287;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Problem(
        title = "Encrypt and Decrypt Strings",
        difficulty = QDifficulty.HARD,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/encrypt-and-decrypt-strings/"
)
public class Q2227 {
    /**
     * Time:  O(len(dict) * len(dict[i]) * total_call)
     * Space: O(len(dict) * len(dict[i]))
     * */

    Map<Character, String> map1;
    Map<String, Set<Character>> map2;
    TrieNode root;

    public Q2227(char[] keys, String[] values, String[] dictionary) {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        root = new TrieNode();

        for(int i = 0; i < keys.length; i++) {
            map1.put(keys[i], values[i]);
            map2.computeIfAbsent(values[i], k -> new HashSet<>()).add(keys[i]);
        }

        for(String s : dictionary) {
            update(root, s);
        }
    }

    public String encrypt(String word1) {
        StringBuilder sb = new StringBuilder();
        int n = word1.length();

        for(int i = 0; i < n; i++) {
            sb.append(map1.get(word1.charAt(i)));
        }

        return sb.toString();
    }

    public int decrypt(String word2) {
        return query(root, word2, 0);
    }

    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = "";
    }

    private void update(TrieNode root, String s) {
        TrieNode cur = root;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }

        cur.word = s;
    }

    private int query(TrieNode root, String s, int idx) {
        TrieNode cur = root;
        int n = s.length();
        int ans = 0;

        if(idx == n) {
            if(root.word.isEmpty()) {
                return 0;
            }
            return 1;
        }

        String ss = s.substring(idx, idx + 2);

        Set<Character> set = map2.get(ss);

        if(set == null) {
            for(int i = 0; i < 2; i++) {
                char c = ss.charAt(i);
                if(cur.children[c - 'a'] == null) {
                    return 0;
                }
                cur = cur.children[c - 'a'];
            }

            return query(cur, s, idx + 2);
        } else {
            for(char c : set) {
                if(cur.children[c - 'a'] == null) {
                    continue;
                }

                ans += query(cur.children[c - 'a'], s, idx + 2);
            }

            return ans;
        }
    }
}
