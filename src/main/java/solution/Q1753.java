package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Score From Removing Stones",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/maximum-score-from-removing-stones/"
)
public class Q1753 {
    public int maximumScore(int a, int b, int c) {
        return recursiveSimulation(a, b, c);
    }

    private int recursiveSimulation(int a, int b, int c) {
        if(a > b) {
            return recursiveSimulation(b, a, c);
        }
        if(b > c) {
            return recursiveSimulation(a, c, b);
        }

        return b == 0 ? 0 : recursiveSimulation(a, b - 1, c - 1) + 1;
    }
}
