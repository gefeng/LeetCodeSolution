package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count the Hidden Sequences",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/count-the-hidden-sequences/"
)
public class Q2145 {
    /**
     * let the array be:
     *  [x0, x1, x2, ..., xn+1]
     *
     *  x1 - x0 = d[0]
     *  x2 - x1 = d[1]
     *  x3 - x2 = d[2]
     *  ...
     *  xn+1 - xn = d[n]
     *
     *  Notice that xt - x0 = d[t - 1] => xt = x0 + d[t - 1]
     *  We can see if fix the first element, every elements can be calculated.
     *  thus we just need to make sure min and max values of hidden array are within the range.
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    public int numberOfArrays(int[] differences, int lower, int upper) {
        int n = differences.length;
        int ans = 0;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int d : differences) {
            sum += d;
            min = Math.min(min, sum);
            max = Math.max(max, sum);
        }

        for(int x = lower; x <= upper; x++) {
            if(x + min >= lower && x + max <= upper) {
                ans++;
            }
        }

        return ans;
    }
}
