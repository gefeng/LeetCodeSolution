package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find XOR Sum of All Pairs Bitwise AND",
        difficulty = QDifficulty.HARD,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/find-xor-sum-of-all-pairs-bitwise-and/"
)
public class Q1835 {
    /**
     * Time:  O(M + N)
     * Space: O(1)
     * */
    public int getXORSum(int[] arr1, int[] arr2) {
        int ans = 0;
        int xor = 0;
        for(int x : arr2) {
            xor ^= x;
        }

        for(int x : arr1) {
            ans ^= (x & xor);
        }

        return ans;
    }
}