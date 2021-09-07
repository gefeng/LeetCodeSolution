package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "NUmber of Good Pairs",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/number-of-good-pairs/"
)
public class Q1512 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int numIdenticalPairs(int[] nums) {
        int n = nums.length;
        int res = 0;
        int[] cnt = new int[101];

        for(int i = 0; i < n; i++) {
            res += cnt[nums[i]];
            cnt[nums[i]]++;
        }
        return res;
    }
}
