package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Statistics from a Large Sample",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/statistics-from-a-large-sample/"
)
public class Q1093 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public double[] sampleStats(int[] count) {
        double[] ans = new double[5];

        double min = -1;
        double max = -1;
        double sum = 0;
        int tot = 0;
        int maxFreq = 0;
        double mostFreq = 0;
        for(int i = 0; i < 256; i++) {
            if(count[i] > 0) {
                min = min == -1 ? i : min;
                max = i;
            }
            sum += (double)i * count[i];
            tot += count[i];

            if(count[i] > maxFreq) {
                maxFreq = count[i];
                mostFreq = i;
            }
        }

        ans[0] = min;
        ans[1] = max;
        ans[2] = sum / tot;
        ans[4] = mostFreq;

        double mid = 0;
        if(tot % 2 == 0) {
            int t1 = tot / 2;
            int t2 = tot / 2 + 1;
            double x = -1, y = -1;
            for(int i = 0; i < 256; i++) {
                t1 -= count[i];
                t2 -= count[i];
                if(t1 <= 0) {
                    x = x == -1 ? i : x;
                }
                if(t2 <= 0) {
                    y = i;
                    break;
                }
            }
            ans[3] = (x + y) / 2;
        } else {
            int t = tot / 2 + 1;
            for(int i = 0; i < 256; i++) {
                t -= count[i];
                if(t <= 0) {
                    ans[3] = i;
                    break;
                }
            }
        }

        return ans;
    }
}
