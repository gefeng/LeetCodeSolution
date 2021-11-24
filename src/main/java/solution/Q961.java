package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "N-Repeated Element in Size 2N Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/n-repeated-element-in-size-2n-array/"
)
public class Q961 {
    /**
     * Time:  O(N)
     * Space: O(max(nums))
     * */
    public int repeatedNTimes(int[] nums) {
        boolean[] seen = new boolean[10001];

        for(int x : nums) {
            if(seen[x]) {
                return x;
            }
            seen[x] = true;
        }

        return -1;
    }
}
