package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Count of Range Sum",
        difficulty = QDifficulty.HARD,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/count-of-range-sum/"
)
public class Q327 {
    /**
     * Time:  O(NlogN)
     * Space: O(N)
     * */
    public int countRangeSum(int[] nums, int lower, int upper) {
        return mergeSortSol(nums, lower, upper);
    }

    private class BIT {
        int[] bit;
        BIT(int n) {
            bit = new int[n + 1];
        }

        void add(int i) {
            for(; i < bit.length; i += i & (-i)) {
                bit[i]++;
            }
        }

        int query(int i) {
            int sum = 0;
            for(; i > 0; i -= i & (-i)) {
                sum += bit[i];
            }
            return sum;
        }
    }
    private int binaryIndexTreeSol(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] ps = new long[n + 1];
        BIT bit = new BIT(n + 1);

        for(int i = 1; i < n + 1; i++) {
            ps[i] = ps[i - 1] + nums[i - 1];
        }

        Arrays.sort(ps);

        bit.add(binarySearch(ps, 0) + 1);

        long sum = 0;
        int ans = 0;
        for(int num : nums) {
            sum += num;
            int lo = bit.query(binarySearch(ps, sum - upper - 1) + 1);
            int hi = bit.query(binarySearch(ps, sum - lower) + 1);
            ans += hi - lo;
            bit.add(binarySearch(ps, sum) + 1);
        }

        return ans;
    }

    private int binarySearch(long[] nums, long target) {
        int lo = 0;
        int hi = nums.length - 1;
        int ans = -1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] <= target) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    private int mergeSortSol(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] ps = new long[n + 1];
        for(int i = 1; i < n + 1; i++) {
            ps[i] = ps[i - 1] + nums[i - 1];
        }

        int cnt = mergeSortCount(ps, 0, n, lower, upper);

        return cnt;
    }

    private int mergeSortCount(long[] nums, int start, int end, int lower, int upper) {
        if(start >= end) {
            return 0;
        }

        int mid = (start + end) / 2;
        int cnt = mergeSortCount(nums, start, mid, lower, upper) + mergeSortCount(nums, mid + 1, end, lower, upper);

        long[] m = new long[end - start + 1];

        int lo = mid + 1;
        int hi = mid + 1;
        int j = mid + 1;
        int k = 0;
        for(int i = start; i <= mid; i++) {
            while(lo <= end && nums[lo] - nums[i] < lower) {
                lo++;
            }
            while(hi <= end && nums[hi] - nums[i] <= upper) {
                hi++;
            }
            while(j <= end && nums[j] < nums[i]) {
                m[k++] = nums[j++];
            }
            m[k++] = nums[i];
            cnt += hi - lo;
        }

        while(j <= end) {
            m[k++] = nums[j++];
        }

        for(int i = 0; i < m.length; i++) {
            nums[i + start] = m[i];
        }

        return cnt;
    }
}
