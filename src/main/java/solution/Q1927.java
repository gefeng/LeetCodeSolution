package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum Game",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/biweekly-contest-56/problems/sum-game/"
)
public class Q1927 {
    public boolean sumGame(String num) {
        int n = num.length();
        int sumL = 0;
        int sumR = 0;
        int cntL = 0;
        int cntR = 0;
        for(int i = 0; i < n / 2; i++) {
            char c1 = num.charAt(i);
            char c2 = num.charAt(i + n / 2);
            cntL = c1 == '?' ? cntL + 1 : cntL;
            cntR = c2 == '?' ? cntR + 1 : cntR;
            sumL = c1 == '?' ? sumL : sumL + c1 - '0';
            sumR = c2 == '?' ? sumR : sumR + c2 - '0';
        }

        if((cntL + cntR) % 2 != 0) {
            return true;
        }

        if(sumL == sumR) {
            return false;
        }

        if(cntL == cntR) {
            return true;
        }

        if((cntL > cntR && sumL > sumR) || (cntR > cntL && sumR > sumL)) {
            return true;
        }

        int diff = Math.abs(sumL - sumR);
        return Math.abs(cntL - cntR) / 2 * 9 != diff;
    }
}
