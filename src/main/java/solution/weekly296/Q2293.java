package solution.weekly296;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Min Max Game",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/contest/weekly-contest-296/problems/min-max-game/"
)
public class Q2293 {
    /**
     * Time:  O(N * logN)
     * Space: O(1)
     * */
    public int minMaxGame(int[] nums) {
        int n = nums.length;
        while(n != 1) {
            n /= 2;
            for(int i = 0; i < n; i++) {
                nums[i] = i % 2 == 0 ? Math.min(nums[2 * i], nums[2 * i + 1]) : Math.max(nums[2 * i], nums[2 * i + 1]);
            }
        }

        return nums[0];
    }
}
