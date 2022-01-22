package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Ways to Divide a Long Corridor",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-ways-to-divide-a-long-corridor/"
)
public class Q2147 {
    /**
     * Solved it using dp in contest. But it's just simple math problem.
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    private static final long MOD = (long)1e9 + 7;
    public int numberOfWays(String corridor) {
        int n = corridor.length();
        int tot = 0;
        long ans = 1L;

        for(int i = 0; i < n; i++) {
            if(corridor.charAt(i) == 'S') {
                tot++;
            }
        }

        if(tot == 0 || tot % 2 != 0) {
            return 0;
        }

        int pre = 0;
        for(int i = 0; i < n; i++) {
            int j = i;
            while(j < n && corridor.charAt(j) != 'S') {
                j++;
            }
            j++;
            while(j < n && corridor.charAt(j) != 'S') {
                j++;
            }

            int cnt = 0;
            while(j + 1 < n && corridor.charAt(j + 1) == 'P') {
                j++;
                cnt++;
            }

            if(j != n - 1) {
                ans = ans * (cnt + 1) % MOD;
            }

            i = j;
        }

        return (int)ans;
    }
}
