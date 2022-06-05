package solution.weekly296;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Partition Array Such That Maximum Difference Is K",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/weekly-contest-296/problems/partition-array-such-that-maximum-difference-is-k/"
)
public class Q2294 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int partitionArray(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        Arrays.sort(nums);

        for(int i = 0; i < n; ) {
            int j = i;
            while(i < n && nums[i] - nums[j] <= k) {
                i++;
            }
            ans++;
        }

        return ans;
    }
}
