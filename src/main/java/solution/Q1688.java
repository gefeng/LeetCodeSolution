package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Of Matches in Tournament",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/contest/weekly-contest-219/problems/count-of-matches-in-tournament/"
)
public class Q1688 {
    public int numberOfMatches(int n) {
        int cnt = 0;
        while(n != 1) {
            cnt += n / 2;
            n = (n + 1) / 2;
        }

        return cnt;
    }
}
