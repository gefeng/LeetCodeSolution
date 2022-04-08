package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Pour Water Between Buckets to Make Water Levels Equal",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/pour-water-between-buckets-to-make-water-levels-equal/"
)
public class Q2137 {
    /**
     * Time:  O(N * log(1e10))
     * Space: O(1)
     * */
    public double equalizeWater(int[] buckets, int loss) {
        double lo = 0.0;
        double hi = 1e5;
        double exp = 1e-5;
        double ans = 0.0;

        while(hi - lo >= exp) {
            double mid = (lo + hi) / 2;

            if(isOk(buckets, loss, mid)) {
                ans = mid;
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return ans;
    }

    private boolean isOk(int[] buckets, int loss, double amount) {
        int n = buckets.length;
        double perc = (double)loss / 100;
        double sum = 0;
        double tot = n * amount;

        for(int b : buckets) {
            sum += b;
        }

        double d = 0;
        for(int b : buckets) {
            if(b > amount) {
                d += (b - amount) * perc;
            }
        }

        return sum - d >= tot;
    }
}
