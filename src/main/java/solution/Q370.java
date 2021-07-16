package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Range Addition",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.PREFIX_SUM,
        url = "https://leetcode.com/problems/range-addition/"
)
public class Q370 {
    /*
        for each query [start, end, val]
        for each element i in [start, end] we want to acheive
        arr[i] = arr[i] + val

        which is equivalent to
        apply addition to every elements i in [start, n)
        arr[i] = arr[i] + val  where i >= start and i < n

        apply subtraction to every elements j in (end, n)
        arr[j] = arr[j] + val - val

        therefore we can just update
        arr[start] with  +val
        arr[end + 1] with -val
        and after applying updates, carry over the cumulative sums to every element in arr.
    */
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] ans = new int[length];

        for(int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int val = update[2];

            ans[start] += val;
            if(end + 1 < length) {
                ans[end + 1] -= val;
            }
        }

        for(int i = 1; i < length; i++) {
            ans[i] += ans[i - 1];
        }
        return ans;
    }
}
