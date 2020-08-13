package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum XOR of Two Numbers in an Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TRIE,
        url = "https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/"
)
public class Q421 {
    class TrieNode {
        TrieNode[] children = new TrieNode[2];
    }
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        for(int n : nums)
            max = Math.max(max, n);

        int maxLen = 0;
        while(max != 0) {
            max = max / 2;
            maxLen++;
        }

        TrieNode root = new TrieNode();
        int[][] bitNums = new int[nums.length][maxLen];
        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            TrieNode curr = root;
            for(int j = maxLen - 1; j >= 0; j--) {
                bitNums[i][j] = num % 2;
                num /= 2;
            }
            for(int j = 0; j < maxLen; j++) {
                int bit = bitNums[i][j];
                if(curr.children[bit] == null)
                    curr.children[bit] = new TrieNode();
                curr = curr.children[bit];
            }
        }

        int maxXor = 0;
        for(int i = 0; i < nums.length; i++) {
            TrieNode curr = root;
            int xor = 0;
            for(int j = 0; j < maxLen; j++) {
                int bit = bitNums[i][j];
                int oppoBit = bit == 0 ? 1 : 0;
                if(curr.children[oppoBit] != null) {
                    xor = (xor << 1) | 1;
                    curr = curr.children[oppoBit];
                }
                else {
                    xor = xor << 1;
                    curr = curr.children[bit];
                }
            }
            maxXor = Math.max(maxXor, xor);
        }

        return maxXor;
    }
}
