package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Array With Elements Not Equal to Average of Neighbors",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/array-with-elements-not-equal-to-average-of-neighbors/"
)
public class Q1968 {
    /**
     * The statement can be translated to,
     * for each i within [1, n - 1] inclusive,
     * nums[i] - nums[i - 1] != nums[i + 1] - nums[i]
     *
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        Arrays.sort(nums);

        int i = 0;
        int l = 0;
        int r = n - 1;

        while(l <= r) {
            res[i++] = nums[l];
            if(i < n) {
                res[i++] = nums[r];
            }
            l++;
            r--;
        }

        return res;
    }
}
