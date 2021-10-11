package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Operations to Make a Uni-Value Grid",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/"
)
public class Q2033 {
    /**
     * Time:  O(M * N * log(M * N))
     * Space: O(M * N)
     * */
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;

        int[] arr = new int[m * n];
        for(int i = 0, k = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                arr[k++] = grid[i][j];
            }
        }

        Arrays.sort(arr);

        int l = 0;
        int r = arr.length - 1;
        while(l < r) {
            int diff = arr[r] - arr[l];
            if(diff % x != 0) {
                return -1;
            }
            ans += diff / x;
            l++;
            r--;
        }

        return ans;
    }
}
