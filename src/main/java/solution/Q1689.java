package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Partitioning Into Minimum Number Of Deci-Binary Numbers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/"
)
public class Q1689 {
    public int minPartitions(String n) {
        int len = n.length();
        int max = 0;
        for(int i = 0; i < len; i++) {
            max = Math.max(max, n.charAt(i) - '0');
        }
        return max;
    }
}
