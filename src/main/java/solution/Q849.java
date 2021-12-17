package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximize Distance to Closest Person",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/maximize-distance-to-closest-person/"
)
public class Q849 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int ans = 0;

        int pre = -1;
        for(int i = 0; i < n; i++) {
            if(seats[i] == 1) {
                if(pre == -1) ans = Math.max(ans, i);
                else ans = Math.max(ans, (i - pre) / 2);

                pre = i;
            }
        }

        if(seats[n - 1] == 0) ans = Math.max(ans, n - 1 - pre);

        return ans;
    }
}
