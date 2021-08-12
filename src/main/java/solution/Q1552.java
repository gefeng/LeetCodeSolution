package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Magnetic Force Between Two Balls",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/magnetic-force-between-two-balls/"
)
public class Q1552 {
    /**
     * Time:  O(M * logN)
     * Space: O(logN)
     * */
    public int maxDistance(int[] position, int m) {
        int n = position.length;

        Arrays.sort(position);

        int lo = 1;
        int hi = position[n - 1] - position[0];
        int res = 0;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(canDistribute(position, m, mid)) {
                res = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return res;
    }

    private boolean canDistribute(int[] p, int m, int f) {
        int n = p.length;

        int b = p[0];
        int i = 0;
        m--;

        while(i < n && m > 0) {
            b = b + f;
            while(i < n && p[i] < b) {
                i++;
            }

            if(i < n) {
                b = p[i];
                m--;
            }
        }

        return m == 0;
    }
}
