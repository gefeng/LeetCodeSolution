package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "The k strongest Values in an Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/the-k-strongest-values-in-an-array/"
)
public class Q1471 {
    /**
     * Time:  O(N * logN)
     * Space: O(max(logN, K))
     * */
    public int[] getStrongest(int[] arr, int k) {
        int n = arr.length;
        int[] ans = new int[k];

        Arrays.sort(arr);

        int m = arr[(n - 1) / 2];

        int l = 0;
        int r = n - 1;
        int i = 0;
        while(i < k) {
            int x = Math.abs(arr[l] - m);
            int y = Math.abs(arr[r] - m);

            if(x == y) {
                ans[i++] = arr[l] > arr[r] ? arr[l++] : arr[r--];
            } else {
                ans[i++] = x > y ? arr[l++] : arr[r--];
            }
        }

        return ans;
    }
}
