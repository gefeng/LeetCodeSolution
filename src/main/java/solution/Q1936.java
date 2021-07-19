package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Add Minimum Number of Rungs",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/add-minimum-number-of-rungs/"
)
public class Q1936 {
    public int addRungs(int[] rungs, int dist) {
        int cnt = 0;
        int prev = 0;
        for(int i = 0; i < rungs.length; i++) {
            int diff = rungs[i] - prev;

            if(diff > dist) {
                cnt += (diff + dist - 1) / dist - 1;
            }

            prev = rungs[i];
        }
        return cnt;
    }
}
