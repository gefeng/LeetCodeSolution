package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Limit of Balls in a Bag",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/"
)
public class Q1760 {
    /*
    * 我看到一个大佬的建议： 如果题目求maximum/minimum，优先考虑bs和dp
    * */
    public int minimumSize(int[] nums, int maxOperations) {
        int lo = 1;
        int hi = (int)1e9;
        int penalty = 0;

        while(lo <= hi) {
            int max = lo + (hi - lo) / 2;

            if(isPossible(nums, maxOperations, max)) {
                penalty = max;
                hi = max - 1;
            } else {
                lo = max + 1;
            }
        }

        return penalty;
    }

    private boolean isPossible(int[] nums, int maxOperations, int max) {
        for(int num : nums) {
            maxOperations -= ((num + max - 1) / max - 1);
            if(maxOperations < 0) {
                return false;
            }
        }

        return true;
    }
}
