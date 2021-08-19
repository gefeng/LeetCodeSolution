package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Arithmetic Slices",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/arithmetic-slices/"
)
public class Q413 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int res = 0;

        int preDiff = 1001;
        int l = 0;
        int r = 1;
        while(r < n) {
            int curDiff = nums[r] - nums[r - 1];
            if(preDiff != 1001 && curDiff != preDiff) {
                l = r - 1;
            }

            if(r - l + 1 > 2) {
                res += (r - l - 1);
            }

            preDiff = curDiff;
            r++;

        }

        return res;
    }
}
