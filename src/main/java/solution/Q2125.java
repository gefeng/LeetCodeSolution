package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Laser Beams in a Bank",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/number-of-laser-beams-in-a-bank/"
)
public class Q2125 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int numberOfBeams(String[] bank) {
        int m = bank.length;
        int n = bank[0].length();
        int ans = 0;

        int pre = 0;
        for(int i = 0; i < m; i++) {
            int cnt = 0;
            for(int j = 0; j < n; j++) {
                cnt += bank[i].charAt(j) == '1' ? 1 : 0;
            }

            if(cnt == 0) continue;

            ans += pre * cnt;

            pre = cnt;
        }

        return ans;
    }
}
