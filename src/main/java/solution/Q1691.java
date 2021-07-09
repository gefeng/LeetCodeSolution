package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Maximum Height by Stacking Cuboids",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-height-by-stacking-cuboids/"
)
public class Q1691 {
    public int maxHeight(int[][] cuboids) {
        int n = cuboids.length;

        for(int[] c : cuboids) {
            Arrays.sort(c);
        }

        Arrays.sort(cuboids, (a, b) -> {
            if(a[2] != b[2]) {
                return Integer.compare(b[2], a[2]);
            }
            if(a[1] != b[1]) {
                return Integer.compare(b[1], a[1]);
            }

            return Integer.compare(b[0], a[0]);
        });


        int[] dp = new int[n];

        int max = 0;
        for(int i = 0; i < n; i++) {
            dp[i] = cuboids[i][2];
            for(int j = 0; j < i; j++) {
                if(canStack(cuboids[i], cuboids[j])) {
                    dp[i] = Math.max(dp[i], dp[j] + cuboids[i][2]);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    private boolean canStack(int[] box1, int[] box2) {
        return box1[0] <= box2[0] && box1[1] <= box2[1] && box1[2] <= box2[2];
    }
}
