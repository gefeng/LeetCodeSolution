package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Ships in a Rectangle",
        difficulty = QDifficulty.HARD,
        tag = QTag.DIVIDE_AND_CONQUER,
        url = "https://leetcode.com/problems/number-of-ships-in-a-rectangle/"
)
public class Q1274 {
    /**
     * Time:  O(log(M * N))
     * Space: O(log(M * N))
     * */
    private class Sea {
        boolean hasShips(int[] top, int[] bot) {
            return true;
        }
    }
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        int res = dfs(sea, topRight, bottomLeft);

        return res;
    }

    private int dfs(Sea sea, int[] top, int[] bot) {
        if(top[0] == bot[0] && top[1] == bot[1]) {
            return sea.hasShips(top, bot) ? 1 : 0;
        }
        if(top[0] < bot[0] || top[1] < bot[1]) {
            return 0;
        }
        if(!sea.hasShips(top, bot)) {
            return 0;
        }

        int midX = bot[0] + top[0] >> 1;
        int midY = bot[1] + top[1] >> 1;

        return dfs(sea, new int[] {midX, midY}, new int[] {bot[0], bot[1]}) +
                dfs(sea, new int[] {top[0], midY}, new int[] {midX + 1, bot[1]}) +
                dfs(sea, new int[] {midX, top[1]}, new int[] {bot[0], midY + 1}) +
                dfs(sea, new int[] {top[0], top[1]}, new int[] {midX + 1, midY + 1});
    }
}
