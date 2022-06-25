package solution.biweekly81;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum XOR After Operations",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/contest/biweekly-contest-81/problems/maximum-xor-after-operations/"
)
public class Q2317 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maximumXOR(int[] nums) {
        int ans = 0;

        for(int x : nums) {
            ans |= x;
        }

        return ans;
    }
}
