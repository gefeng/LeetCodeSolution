package solution.weekly285;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Hills and Valleys in an Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/contest/weekly-contest-285/problems/count-hills-and-valleys-in-an-array/"
)
public class Q2210 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int countHillValley(int[] nums) {
        int ans = 0;
        int n = nums.length;

        for(int i = 0; i < n; ) {
            int j = i;
            while(j < n && nums[j] == nums[i]) {
                j++;
            }

            if(i > 0 && j < n) {
                if((nums[i] > nums[i - 1] && nums[i] > nums[j]) ||
                        (nums[i] < nums[i - 1] && nums[i] < nums[j])) {
                    ans++;
                }
            }

            i = j;
        }

        return ans;
    }
}
