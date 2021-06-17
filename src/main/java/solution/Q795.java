package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Subarrays with Bounded Maximum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/"
)
public class Q795 {
    /*
    * Basically there are 3 cases,
      1.Current value is within the range [left, right].
        Expand the right bound j for sliding window.
        Reset k.
      2.Current value is larger than upper bound right
        Reset the left bound i for sliding window to j + 1.
        Reset k.
      3.Current value is less than lower bound left
        Increase k by 1, since those are the new subarrays only contain value less than left. Subtract them from the answer.
    * */
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int n = nums.length;
        int cnt = 0;

        for(int i = 0, j = 0, k = 0; j < n; j++) {
            if(nums[j] < left) {
                k++;
            } else {
                i = nums[j] > right ? j + 1 : i;
                k = 0;
            }
            cnt += (j - i + 1);
            cnt -= k;
        }

        return cnt;
    }

    /*
        atMost(right) - atMost(left -1)
    */
    private int countingSolution(int[] nums, int left, int right) {
        return atMost(nums, right) - atMost(nums, left - 1);
    }

    private int atMost(int[] nums, int k) {
        int n = nums.length;
        int cnt = 0;
        for(int i = 0, j = 0; j < n; j++) {
            i = nums[j] > k ? j + 1 : i;
            cnt += (j - i + 1);
        }
        return cnt;
    }
}
