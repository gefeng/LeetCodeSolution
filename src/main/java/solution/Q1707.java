package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Maximum XOR With an Element From Array",
        difficulty = QDifficulty.HARD,
        tag = QTag.TRIE,
        url = "https://leetcode.com/problems/maximum-xor-with-an-element-from-array/"
)
public class Q1707 {
    /*
         1e9 ~ 2 ^ 30

         Build trie for searching optimal xor result again...
    */
    private class TrieNode {
        TrieNode[] children = new TrieNode[2];
        int val = -1;
    }
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int m = nums.length;
        int n = queries.length;
        int[] ans = new int[n];

        int[][] q = new int[n][3];
        for(int i = 0; i < n; i++) {
            q[i] = new int[] {queries[i][0], queries[i][1], i};
        }

        Arrays.sort(nums);
        Arrays.sort(q, Comparator.comparingInt(a -> a[1]));

        TrieNode root = new TrieNode();
        for(int i = 0, j = 0; i < n; i++) {
            int x = q[i][0], y = q[i][1];

            while(j < m && nums[j] <= y) {
                insert(root, toBits(nums[j]), nums[j]);
                j++;
            }

            int cand = search(root, toBits(x));
            ans[q[i][2]] = cand == -1 ? -1 : cand ^ x;
        }

        return ans;
    }

    private int[] toBits(int num) {
        int[] bits = new int[30];
        int i = bits.length - 1;
        while(num != 0) {
            bits[i--] = num % 2;
            num >>= 1;
        }
        return bits;
    }

    private void insert(TrieNode root, int[] bits, int val) {
        TrieNode curr = root;
        for(int i = 0; i < bits.length; i++) {
            int bit = bits[i];
            if(curr.children[bit] == null) {
                curr.children[bit] = new TrieNode();
            }
            curr = curr.children[bit];
        }
        curr.val = val;
    }

    private int search(TrieNode root, int[] x) {
        if(root.children[0] == null && root.children[1] == null) {
            return -1;
        }

        TrieNode curr = root;
        for(int i = 0; i < x.length; i++) {
            int b = x[i];
            int rb = (b + 1) % 2;

            curr = curr.children[rb] != null ? curr.children[rb] : curr.children[b];
        }

        return curr.val;
    }
}
