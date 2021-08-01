package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Number of Achievable Transfer Requests",
        difficulty = QDifficulty.HARD,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/maximum-number-of-achievable-transfer-requests/"
)
public class Q1601 {
    /**
     * Time:  O(2 ^ M * (M + N))
     * Space: O(N)
     * */
    public int maximumRequests(int n, int[][] requests) {
        int m = requests.length;
        int res = 0;

        for(int i = 0; i < (1 << m); i++) {
            int[] info = canAchieve(n, requests, i);

            if(info[0] == 1) {
                res = Math.max(res, info[1]);
            }
        }

        return res;
    }

    private int[] canAchieve(int n, int[][] requests, int mask) {
        int m = requests.length;
        int cntBits = 0;
        int[] degree = new int[n];

        for(int i = 0; i < m; i++) {
            if(((1 << i) & mask) == 0) {
                continue;
            }
            cntBits++;
            degree[requests[i][0]]--;
            degree[requests[i][1]]++;
        }

        for(int i = 0; i < n; i++) {
            if(degree[i] != 0) {
                return new int[] {0, cntBits};
            }
        }

        return new int[] {1, cntBits};
    }
}
