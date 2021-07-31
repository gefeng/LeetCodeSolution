package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Special Array With X Elements Greater Than or Equal X",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/"
)
public class Q1608 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int specialArray(int[] nums) {
        Arrays.sort(nums);

        int lo = 0;
        int hi = 1000;
        int res = -1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            int cnt = 0;
            for(int num : nums) {
                if(num >= mid) {
                    cnt++;
                }
            }

            if(cnt == mid) {
                res = mid;
                break;
            }

            if(cnt > mid) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }


        return res;
    }
}
