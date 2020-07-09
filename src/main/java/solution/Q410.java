package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Split Array Largest Sum",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/split-array-largest-sum/"
)
public class Q410 {
    private boolean check(int[] nums, int m, long largestSum) {
        int count = 1;
        long sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(sum > largestSum) {
                sum = nums[i];
                count++;
            }
        }
        return count <= m;
    }
    public long splitArray(int[] nums, int m) {
        long left = 0;
        long right = 0;
        long mid = 0;
        long ans = 0;
        for(int i = 0; i < nums.length; i++) {
            right += nums[i];
            left = nums[i] > left ? nums[i] : left;
        }
        while(left <= right) {
            mid = left + (right - left) / 2;
            if(check(nums, m, mid)) {
                ans = mid;
                right = mid - 1;
            }
            else
                left = mid + 1;
        }
        return ans;
    }
}
