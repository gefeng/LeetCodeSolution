package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Number of Ways to Build Sturdy Brick Wall",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-ways-to-build-sturdy-brick-wall/"
)
public class Q2184 {
    /**
     * Time:  O(2 ^ width * 2 ^ width * height)
     * Space: O(2 ^ width * height)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int buildWall(int height, int width, int[] bricks) {
        int n = bricks.length;
        int ans = 0;
        Set<Integer> pat = new HashSet<>();
        Set<Integer> set = new HashSet<>();

        for(int b : bricks) set.add(b);

        for(int i = 1; i < (1 << width + 1); i++) {
            if(((1 << width) & i) != 0 && (1 & i) != 0) {
                int pre = 0;
                boolean isOk = true;
                for(int cur = 1; cur <= width; cur++) {
                    if(((1 << cur) & i) != 0) {
                        if(!set.contains(cur - pre)) {
                            isOk = false;
                            break;
                        }
                        pre = cur;
                    }
                }

                if(isOk) {
                    pat.add(i);
                }
            }
        }

        int[][] dp = new int[height + 1][1 << width + 1];
        for(int p : pat) {
            dp[1][p] = 1;
        }

        for(int i = 2; i <= height; i++) {
            for(int p1 : pat) {
                for(int p2 : pat) {
                    boolean isOk = true;
                    if((p1 & p2) == (1 << width | 1)) {
                        dp[i][p1] = (dp[i][p1] + dp[i - 1][p2]) % MOD;
                    }
                }
            }
        }

        for(int i = 1; i < (1 << width + 1); i++) {
            ans = (ans + dp[height][i]) % MOD;
        }

        return ans;
    }
}
