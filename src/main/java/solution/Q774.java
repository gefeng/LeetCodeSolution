package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimize Max Distance to Gas Station",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/minimize-max-distance-to-gas-station/"
)
public class Q774 {
    /**
     * It'a actually the first time I learned how to apply binary search on float/double.
     * The time complexity is higher than integer since we need to consider precision.
     *
     * Time:  O(N * logW) where W is max distance between two adjacent stations divided
     * by 1e-6 (the acceptable precision)
     * Space: O(1)
     * */
    private static final double EXP = 1e-6;
    public double minmaxGasDist(int[] stations, int k) {
        int n = stations.length;
        double ans = 0.0;
        double lo = 0.0;
        double hi = 0.0;

        for(int i = 1; i < n; i++) {
            hi = Math.max(hi, (double)stations[i] - stations[i - 1]);
        }

        while(hi - lo >= EXP) {
            double mid = lo + (hi - lo) / 2.0;

            if(isOk(stations, k, mid)) {
                ans = mid;
                hi = mid - EXP;
            } else {
                lo = mid + EXP;
            }
        }

        return ans;
    }

    private boolean isOk(int[] s, int k, double d) {
        int n = s.length;

        for(int i = 1; i < n; i++) {
            double dist = s[i] - s[i - 1];

            int need = (int)Math.ceil(dist / d) - 1;

            if(need > k) {
                return false;
            }

            k -= need;
        }

        return true;
    }
}
