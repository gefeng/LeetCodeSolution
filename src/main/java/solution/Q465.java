package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Optimal Account Balancing",
        difficulty = QDifficulty.HARD,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/optimal-account-balancing/"
)
public class Q465 {
    /**
     * Time:  O(N!)
     * Space: O(N)
     * */
    public int minTransfers(int[][] transactions) {
        int[] bal = new int[21];

        for(int[] t : transactions) {
            bal[t[0]] -= t[2];
            bal[t[1]] += t[2];
        }

        return dfs(bal, 0);
    }

    private int dfs(int[] bal, int cur) {
        int n = bal.length;
        if(cur == n) return 0;

        if(bal[cur] == 0) return dfs(bal, cur + 1);

        int min = Integer.MAX_VALUE;

        for(int j = cur + 1; j < n; j++) {
            if(bal[j] * bal[cur] < 0) {
                int x = bal[cur];
                int y = bal[j];
                bal[cur] = 0;
                bal[j] += x;

                min = Math.min(dfs(bal, cur + 1) + 1, min);

                bal[cur] = x;
                bal[j] = y;
            }
        }
        return min;
    }
}
