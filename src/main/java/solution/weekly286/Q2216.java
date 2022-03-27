package solution.weekly286;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Deletions to Make Array Beautiful",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/weekly-contest-286/problems/minimum-deletions-to-make-array-beautiful/"
)
public class Q2216 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minDeletion(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for(int i = 0; i < n;) {
            int j = i;
            while(j < n && nums[j] == nums[i]) {
                j++;
            }

            ans += j - i - 1;
            i = j + 1;
        }

        return (n - ans) % 2 == 0 ? ans : ans + 1;
    }
}
