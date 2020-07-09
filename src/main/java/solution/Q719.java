package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Find K-th Smallest Pair Distance",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/find-k-th-smallest-pair-distance/"
)
public class Q719 {
    private int distanceLessThan(int[] nums, int dist) {
        int left = 0;
        int count = 0;
        for(int right = 0; right < nums.length; right++) {
            while(nums[right] - nums[left] > dist)
                left++;
            count += (right - left);
        }
        return count;
    }
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums[nums.length - 1] - nums[0];
        int mid = 0;
        while(left < right) {
            mid = left + (right - left) / 2;
            int count = distanceLessThan(nums, mid);
            if(count < k)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}
