package solution.biweekly73;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Sort the Jumbled Numbers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/contest/biweekly-contest-73/problems/sort-the-jumbled-numbers/"
)
public class Q2191 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int[] sortJumbled(int[] mapping, int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][2];

        for(int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = map(nums[i], mapping);
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));

        int[] ans = new int[n];
        for(int i = 0; i < n; i++) {
            ans[i] = arr[i][0];
        }

        return ans;
    }

    private int map(int num, int[] mapping) {
        int x = num;
        int res = 0;
        int d = 1;
        if(x == 0) {
            return mapping[0];
        }

        while(x != 0) {
            res += d * (mapping[x % 10]);
            x /= 10;
            d = d * 10;
        }
        return res;
    }
}
