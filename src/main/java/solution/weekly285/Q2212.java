package solution.weekly285;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Points in an Archery Competition",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ENUMERATION,
        url = "https://leetcode.com/contest/weekly-contest-285/problems/maximum-points-in-an-archery-competition/"
)
public class Q2212 {
    /**
     * Time:  O(2 ^ 12 * 12)
     * Space: O(N)
     * */
    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        int n = aliceArrows.length;
        int[] ans = new int[n];
        int max = 0;
        int best = 0;

        for(int m = 1; m < (1 << 12); m++) {
            int tot = 0;
            int score = 0;
            for(int i = 0; i < 12; i++) {
                if((m & (1 << i)) != 0) {
                    tot += aliceArrows[i] + 1;
                    score += i;
                }
            }

            if(tot <= numArrows) {
                if(score > max) {
                    max = score;
                    best = m;
                }
            }
        }

        int cnt = 0;
        for(int i = 0; i < 12; i++) {
            if((best & (1 << i)) != 0) {
                ans[i] = aliceArrows[i] + 1;
                cnt += ans[i];
            }
        }

        if(cnt < numArrows) {
            ans[0] += numArrows - cnt;
        }

        return ans;
    }
}
