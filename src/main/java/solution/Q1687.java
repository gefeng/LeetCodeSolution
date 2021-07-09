package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Delivering Boxes from Storage to Ports",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/delivering-boxes-from-storage-to-ports/"
)
public class Q1687 {
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        int[] dp = new int[n + 1];

        int cntWeight = 0;
        int cntTrips = 1;
        int l = 0;
        int r = 0;
        for(int i = 1; i < n + 1; i++) {
            cntWeight += boxes[r][1];
            if(r == 0 || boxes[r][0] != boxes[r - 1][0]) {
                cntTrips++;
            }

            while(cntWeight > maxWeight || r - l + 1 > maxBoxes || (l < r && dp[l] == dp[l + 1])) {
                cntWeight -= boxes[l][1];
                if(l < r && boxes[l][0] != boxes[l + 1][0]) {
                    cntTrips--;
                }
                l++;
            }
            r++;

            dp[i] = dp[l] + cntTrips;
        }

        return dp[n];
    }
}
