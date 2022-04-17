package solution.weekly289;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Rounds to Complete All Tasks",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/weekly-contest-289/problems/minimum-rounds-to-complete-all-tasks/"
)
public class Q2244 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int minimumRounds(int[] tasks) {
        int n = tasks.length;
        int ans = 0;

        Arrays.sort(tasks);

        for(int i = 0; i < n; ) {
            int j = i;
            while(i < n && tasks[i] == tasks[j]) {
                i++;
            }

            if(i - j == 1) {
                return -1;
            }

            ans += (i - j + 2) / 3;
        }

        return ans;
    }
}
