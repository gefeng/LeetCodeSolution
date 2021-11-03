package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Make Array Strictly Increasing",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/make-array-strictly-increasing/"
)
public class Q1187 {
    /**
     * Time:  O(M * N * logN)
     * Space: O(M * N)
     * */
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        arr2 = removeDup(arr2);
        Arrays.sort(arr2);

        int ans = dfs(arr1, arr2, 0, 0, 0, new Integer[m][n + 1][2]);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int dfs(int[] arr1, int[] arr2, int i, int j, int k, Integer[][][] memo) {
        int m = arr1.length;
        int n = arr2.length;

        if(i == m) {
            return 0;
        }

        if(memo[i][j][k] != null) {
            return memo[i][j][k];
        }

        int pre = k == 0 ? (i == 0 ? -1 : arr1[i - 1]) : arr2[j];

        int nj = Arrays.binarySearch(arr2, j, n, pre);
        nj = nj >= 0 ? nj + 1 : -nj - 1;

        int min = Integer.MAX_VALUE;
        if(nj < n) {
            int ret = dfs(arr1, arr2, i + 1, nj, 1, memo);
            min = ret == Integer.MAX_VALUE ? min : ret + 1;
        }
        if(arr1[i] > pre) {
            min = Math.min(min, dfs(arr1, arr2, i + 1, nj, 0, memo));
        }

        return memo[i][j][k] = min;
    }

    private int[] removeDup(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for(int x : arr) {
            set.add(x);
        }
        int[] ret = new int[set.size()];
        int i = 0;
        for(int x : set) {
            ret[i++] = x;
        }
        return ret;
    }
}
