package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Pairs With XOR in a Range",
        difficulty = QDifficulty.HARD,
        tag = QTag.TRIE,
        url = "https://leetcode.com/problems/count-pairs-with-xor-in-a-range/"
)
public class Q1803 {
    private class TrieNode {
        TrieNode[] children;
        int cnt;
        TrieNode() {
            children = new TrieNode[2];
            cnt = 0;
        }
    }

    private static final int HEIGHT = 15; // 2^15 = 65536 / 2

    public int countPairs(int[] nums, int low, int high) {
        TrieNode root = new TrieNode();

        int cnt = 0;
        for(int x : nums) {
            cnt += (countPairs(root, x, high + 1) - countPairs(root, x, low));
            insert(root, x);
        }
        return cnt;
    }

    // count how many elements is less than k
    private int countPairs(TrieNode root, int x, int k) {
        TrieNode curr = root;
        int cnt = 0;

        for(int i = HEIGHT; i >= 0; i--) {
            int bitk = (k & (1 << i)) == 0 ? 0 : 1;
            int bitx = (x & (1 << i)) == 0 ? 0 : 1;
            if(bitk == 0) {
                if(curr.children[bitx] == null) {
                    break;
                }
                curr = curr.children[bitx];
            } else {
                if(curr.children[bitx] != null) {
                    cnt += curr.children[bitx].cnt;
                }
                if(curr.children[bitx ^ 1] == null) {
                    break;
                }
                curr = curr.children[bitx ^ 1];
            }
        }
        return cnt;
    }

    private void insert(TrieNode root, int x) {
        TrieNode curr = root;

        for(int i = HEIGHT; i >= 0; i--) {
            int bit = (x & (1 << i)) == 0 ? 0 : 1;

            if(curr.children[bit] == null) {
                curr.children[bit] = new TrieNode();
            }
            curr = curr.children[bit];
            curr.cnt++;
        }
    }
}
