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
    /**
     * Time:  O(N * logN * log(max - min))
     * Space: O(logN)
     * */
    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;

        Arrays.sort(nums);

        int lo = 0;
        int hi = nums[n - 1] - nums[0];
        int ans = 0;
        while(lo <= hi) {
            int mid = lo + hi >> 1;

            int cnt = lessEqual(nums, mid);

            if(cnt < k) {
                lo = mid + 1;
            } else if(cnt >= k) {
                ans = mid;
                hi = mid - 1;
            }
        }

        return ans;
    }

    private int lessEqual(int[] nums, int t) {
        int n = nums.length;
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            int idx = lowerBound(nums, i, nums[i] + t);
            if(idx != -1)
                cnt += idx - i;
        }

        return cnt;
    }

    private int lowerBound(int[] nums, int st, int t) {
        int idx = -1;
        int lo = st;
        int hi = nums.length - 1;

        while(lo <= hi) {
            int mid = lo + hi >> 1;

            if(nums[mid] <= t) {
                idx = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return idx;
    }
}
