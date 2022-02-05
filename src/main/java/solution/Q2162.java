package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Cost to Set Cooking Time",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ENUMERATION,
        url = "https://leetcode.com/problems/minimum-cost-to-set-cooking-time/"
)
public class Q2162 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int ans = Integer.MAX_VALUE;
        for(int m = 0; m * 60 <= targetSeconds; m++) {
            if(m <= 99 && targetSeconds - m * 60 <= 99) {
                ans = Math.min(ans, getCost(m, targetSeconds - m * 60, startAt, moveCost, pushCost));
            }
        }

        return ans;
    }

    private int getCost(int min, int sec, int start, int mCost, int pCost) {
        int cost = 0;
        int[] d = new int[4];
        d[0] = min / 10;
        d[1] = min % 10;
        d[2] = sec / 10;
        d[3] = sec % 10;
        int i = 0;
        while(i < 4 && d[i] == 0) {
            i++;
        }

        int pre = start;
        for(int p = i; p < 4; p++) {

            if(d[p] == pre) {
                cost += pCost;
            } else {
                cost += pCost + mCost;
            }
            pre = d[p];
        }

        return cost;
    }
}
