package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Three Consecutive Odds",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/three-consecutive-odds/"
)
public class Q1550 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean threeConsecutiveOdds(int[] arr) {
        int n = arr.length;
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(arr[i] % 2 == 1) {
                cnt++;
            } else {
                cnt = 0;
            }

            if(cnt == 3) {
                return true;
            }
        }

        return false;
    }
}
