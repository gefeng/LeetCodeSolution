package solution.weekly294;

import java.util.*;
import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Lines to Represent a Line Chart",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GEOMETRY,
        url = "https://leetcode.com/contest/weekly-contest-294/problems/minimum-lines-to-represent-a-line-chart/"
)
public class Q2280 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int minimumLines(int[][] stockPrices) {
        int n = stockPrices.length;
        int ans = 0;

        Arrays.sort(stockPrices, Comparator.comparingInt(a -> a[0]));

        for(int i = 0; i < n; ) {
            int j = i;
            int k = i + 1;

            if(k < n) {
                ans++;
            }

            i = k;
            while(i + 1 < n && isOk(stockPrices[j], stockPrices[k], stockPrices[i + 1])) {
                i++;
            }
        }

        return ans;
    }

    private boolean isOk(int[] a, int[] b, int[] c) {
        long dx1 = b[0] - a[0];
        long dy1 = b[1] - a[1];
        long dx2 = c[0] - a[0];
        long dy2 = c[1] - a[1];
        return dx1 * dy2 == dx2 * dy1;
    }
}
