package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Divide Array Into Increasing Sequences",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/divide-array-into-increasing-sequences/"
)
public class Q1121 {
    /**
     * Time:  O(N)
     * Space: O(N / k)
     * */
    public boolean canDivideIntoSubsequences(int[] nums, int k) {
        int m = nums.length / k;
        int n = nums.length;

        int[][] seq = new int[m][2];
        int i = 0, j = 0;
        while(i < n) {
            int[] s = seq[j];

            if(nums[i] > s[0]) {
                s[0] = nums[i];
                s[1] += 1;
            } else {
                return false;
            }

            i++;
            j = (j + 1) % m;
        }

        return true;
    }
}
