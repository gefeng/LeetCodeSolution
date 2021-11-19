package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Domino Rotations For Equal Row",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/"
)
public class Q1007 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int n = tops.length;
        int ans = n + 1;

        for(int i = 1; i <= 6; i++) {
            boolean isOk1 = true;
            int cnt1 = 0;
            // top
            for(int j = 0; j < n; j++) {
                if(tops[j] == i) {
                    continue;
                }

                if(bottoms[j] != i) {
                    isOk1 = false;
                    break;
                }

                cnt1++;
            }

            // bot
            boolean isOk2 = true;
            int cnt2 = 0;
            for(int j = 0; j < n; j++) {
                if(bottoms[j] == i) {
                    continue;
                }

                if(tops[j] != i) {
                    isOk2 = false;
                    break;
                }

                cnt2++;
            }

            if(isOk1) {
                ans = Math.min(ans, cnt1);
            }

            if(isOk2) {
                ans = Math.min(ans, cnt2);
            }
        }

        return ans == n + 1 ? -1 : ans;
    }
}
