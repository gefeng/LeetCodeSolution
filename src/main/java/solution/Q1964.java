package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find the Longest Valid Obstacle Course at Each Position",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/find-the-longest-valid-obstacle-course-at-each-position/"
)
public class Q1964 {
    /**
     * Variant LIS, use binary search to improve time complexity due to the
     * given constraints.
     *
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n = obstacles.length;
        int[] lis = new int[n];
        int[] seq = new int[n];
        int len = 0;
        for(int i = 0; i < n; i++) {
            int idx = binarySearch(seq, len, obstacles[i]);
            if(idx == len) {
                len++;
            }

            seq[idx] = obstacles[i];
            lis[i] = idx + 1;
        }

        return lis;
    }

    private int binarySearch(int[] nums, int len, int target) {
        int lo = 0;
        int hi = len - 1;
        int res = len;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(nums[mid] <= target) {
                lo = mid + 1;
            } else {
                res = mid;
                hi = mid - 1;
            }
        }
        return res;
    }
}
