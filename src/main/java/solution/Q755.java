package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Pour Water",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/pour-water/"
)
public class Q755 {
    /**
     * Time:  O(V * N)
     * Space: O(N)
     * */
    public int[] pourWater(int[] heights, int volume, int k) {
        int n = heights.length;
        int[] ans = Arrays.copyOf(heights, n);

        while(volume != 0) {
            int l = next(ans, k, -1);
            int r = next(ans, k, 1);

            if(l != k) {
                ans[l]++;
            } else {
                ans[r]++;
            }
            volume--;
        }

        return ans;
    }

    private int next(int[] x, int k, int d) {
        int n = x.length;
        int i = k;
        int j = k;
        while(i + d >= 0 && i + d < n && x[i + d] <= x[i]) {
            if(x[i + d] < x[i]) {
                j = i + d;
            }
            i += d;
        }

        return j;
    }
}
