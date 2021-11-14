package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Most Beautiful Item for Each Query",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/most-beautiful-item-for-each-query/"
)
public class Q2070 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, Comparator.comparingInt(a -> a[0]));
        int n = queries.length;

        int[][] copy = new int[n][2];

        for(int i = 0; i < n; i++) {
            copy[i][0] = queries[i];
            copy[i][1] = i;
        }

        Arrays.sort(copy, Comparator.comparingInt(a -> a[0]));


        int[] ans = new int[n];
        int max = 0;
        for(int i = 0, j = 0; i < n; i++) {
            int[] q = copy[i];
            while(j < items.length && items[j][0] <= q[0]) {
                max = Math.max(max, items[j][1]);
                j++;
            }

            ans[q[1]] = max;
        }

        return ans;
    }
}
