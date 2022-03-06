package solution.biweekly73;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Most Frequent Number Following Key In an Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/contest/biweekly-contest-73/problems/most-frequent-number-following-key-in-an-array/"
)
public class Q2190 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int mostFrequent(int[] nums, int key) {
        int[] cnt = new int[1001];
        int n = nums.length;
        for(int i = 1; i < n; i++) {
            if(nums[i - 1] == key) {
                cnt[nums[i]]++;
            }
        }

        int max = 0;
        int ans = 0;
        for(int i = 1; i <= 1000; i++) {
            if(max < cnt[i]) {
                max = cnt[i];
                ans = i;
            }
        }

        return ans;
    }
}
