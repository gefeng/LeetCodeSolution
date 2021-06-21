package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "The Number of Full Rounds You Have Played",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/the-number-of-full-rounds-you-have-played/"
)
public class Q1904 {
    public int numberOfRounds(String startTime, String finishTime) {
        int cnt = 0;
        int st = toMin(startTime);
        int et = toMin(finishTime);
        et = et < st ? et + 24 * 60 : et;

        return (et / 60 - st / 60) * 4 + (et % 60) / 15 - (st % 60 + 15 - 1) / 15;
    }

    private int toMin(String t) {
        String[] hhmm = t.split(":");
        return Integer.parseInt(hhmm[0]) * 60 + Integer.parseInt(hhmm[1]);
    }
}
