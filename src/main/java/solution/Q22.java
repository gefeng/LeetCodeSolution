package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Problem(
        title = "Generate Parentheses",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/generate-parentheses/"
)
public class Q22 {
    /**
     * Time:  O(2 ^ N * N)
     * Space: O(2 ^ N * N)
     * */
    List<String> ans = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    public List<String> generateParenthesis(int n) {
        if(n == 0)
            return ans;
        backTrack(n, 0, 0);
        return ans;
    }

    private void backTrack(int n, int open, int close) {
        if(sb.length() == 2 * n)
            ans.add(sb.toString());

        if(open < n) {
            sb.append('(');
            backTrack(n, open + 1, close);
            sb.deleteCharAt(sb.length() - 1);
        }
        if(close < open) {
            sb.append(')');
            backTrack(n, open, close + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
