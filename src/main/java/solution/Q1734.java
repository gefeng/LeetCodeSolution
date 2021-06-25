package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Decode XORed Permutation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/decode-xored-permutation/"
)
public class Q1734 {
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int[] ans = new int[n];

        int xor = 0;
        for(int i = 1; i <= n; i++) {
            xor ^= i;
        }

        int head = 0;
        for(int i = 1; i < n - 1; i += 2) {
            head ^= encoded[i];
        }

        head = head ^ xor;
        ans[0] = head;
        for(int i = 1; i < n; i++) {
            ans[i] = encoded[i - 1] ^ ans[i - 1];
        }
        return ans;
    }
}
