package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find the Duplicate Number",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/find-the-duplicate-number/"
)
public class Q287 {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);

        slow = 0;
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
    public int findDuplicateBinarySearch(int[] nums) {
        int left = 1;
        int right = nums.length;
        int mid = 0;
        while(left < right) {
            mid = left + (right - left) / 2;
            int count = 0;
            for(int n : nums) {
                if(n <= mid)
                    count++;
            }
            if(count > mid)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}
