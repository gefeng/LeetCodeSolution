package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Smallest Sufficient Team",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/smallest-sufficient-team/"
)
public class Q1125 {
    /**
     * Time:  O(N * * M * 2 ^ M)
     * Space: O(N * 2 ^ M)
     * */
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = people.size();
        int m = req_skills.length;

        int[] skills = new int[n];
        for(int i = 0; i < n; i++) {
            int mask = 0;
            for(String s : people.get(i)) {
                for(int j = 0; j < m; j++) {
                    if(s.equals(req_skills[j])) {
                        mask |= 1 << j;
                        break;
                    }
                }
            }

            skills[i] = mask;
        }

        int[][] dp = new int[n + 1][1 << m];
        for(int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][0] = 0;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j < (1 << m); j++) {
                dp[i][j] = dp[i - 1][j];

                int preM = 0;
                for(int k = 0; k < m; k++) {
                    if((j & (1 << k)) != 0 && (skills[i - 1] & (1 << k)) == 0) {
                        preM |= (1 << k);
                    }
                }

                if(dp[i - 1][preM] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][preM] + 1);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        int curM = (1 << m) - 1;
        for(int i = n; i >= 1; i--) {
            int sm = skills[i - 1];

            int preM = 0;
            for(int k = 0; k < m; k++) {
                if((curM & (1 << k)) != 0 && (sm & (1 << k)) == 0) {
                    preM |= (1 << k);
                }
            }

            if(dp[i - 1][preM] != Integer.MAX_VALUE && dp[i - 1][preM] + 1 == dp[i][curM]) {
                ans.add(i - 1);
                curM = preM;
            }
        }

        return ans.stream().mapToInt(a -> a.intValue()).toArray();
    }
}
