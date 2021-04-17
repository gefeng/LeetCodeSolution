package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Edit Distance",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/edit-distance/"
)
public class Q72 {
    public int minDistance(String word1, String word2) {
        return dfsMinDistance(word1, word2, 0, 0, new Integer[word1.length()][word2.length()]);
    }

    private int dfsMinDistance(String word1, String word2, int i, int j, Integer[][] memo) {
        if(i == word1.length())
            return word2.length() - j;
        if(j == word2.length())
            return word1.length() - i;

        if(memo[i][j] != null)
            return memo[i][j];

        int min = Integer.MAX_VALUE;
        if(word1.charAt(i) == word2.charAt(j))
            min = dfsMinDistance(word1, word2, i + 1, j + 1, memo);
        else {
            int insert = dfsMinDistance(word1, word2, i, j + 1, memo);
            int delete = dfsMinDistance(word1, word2, i + 1, j, memo);
            int replace = dfsMinDistance(word1, word2, i + 1, j + 1, memo);
            min = Math.min(Math.min(insert, delete), replace) + 1;
        }

        memo[i][j] = min;
        return min;
    }
}
