package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Number of Teams",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/count-number-of-teams/"
)
public class Q1395 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int numTeams(int[] rating) {
        int n = rating.length;
        int ans = 0;
        int[] less = new int[n];
        int[] great = new int[n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(rating[j] < rating[i]) {
                    less[i]++;
                } else {
                    great[i]++;
                }
            }
        }

        for(int i = n - 1; i >= 0; i--) {
            int cntL = 0;
            int cntG = 0;
            for(int j = n - 1; j > i; j--) {
                if(rating[j] < rating[i]) {
                    cntL++;
                } else {
                    cntG++;
                }
            }

            ans += less[i] * cntG + great[i] * cntL;
        }

        return ans;
    }
}
