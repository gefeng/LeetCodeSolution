package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Number of Taps to Open to Water a Garden",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/"
)
public class Q1326 {
    /**
     * Convert to jump game and apply greedy
     * 
     * Time:  O(N)
     * Space: O(N)
     * */
    public int minTaps(int n, int[] ranges) {
        int ans = 0;
        int[] jumps = new int[n + 1];

        for(int i = 0; i <= n; i++) {
            int l = Math.max(0, i - ranges[i]);
            int r = Math.min(n, i + ranges[i]);

            jumps[l] = Math.max(jumps[l], r - l);
        }

        int end = 0;
        int far = 0;
        for(int i = 0; i < n + 1; i++) {
            if(i > far) {
                return -1;
            }

            far = Math.max(far, jumps[i] + i);
            //System.out.println(far);
            if(i == end) {
                ans++;
                end = far;
            }
        }

        return ans - 1;
    }
}
