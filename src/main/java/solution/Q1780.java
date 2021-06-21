package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Check if Number is a Sum of Powers of Three",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/check-if-number-is-a-sum-of-powers-of-three/"
)
public class Q1780 {
    public boolean checkPowersOfThree(int n) {
        return mathSolution(n);
    }

    private boolean backtrackSolution(int n) {
        if(n % 3 > 1) {
            return false;
        }

        n = n % 3 == 1 ? n - 1 : n;

        return dfs(n, 3);
    }

    private boolean dfs(int n, int p) {
        if(n == 0) {
            return true;
        }
        if(n < 0 || p > n) {
            return false;
        }

        return dfs(n - p, p * 3) || dfs(n, p * 3);
    }

    private boolean mathSolution(int n) {
        while(n > 0 && n % 3 != 2) {
            n = n % 3 == 1 ? n - 1 : n / 3;
        }
        return n == 0;
    }
}
