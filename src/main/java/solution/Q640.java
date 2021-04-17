package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Solve the Equation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/solve-the-equation/"
)
public class Q640 {
    private static final String NO_SOLUTION = "No solution";
    private static final String INFINITE_SOLUTIONS = "Infinite solutions";
    public String solveEquation(String equation) {
        String[] parts = equation.split("=");
        int[] left = resolve(parts[0]);
        int[] right = resolve(parts[1]);

        if(left[0] == right[0] && left[1] == right[1])
            return INFINITE_SOLUTIONS;
        if(left[0] == right[0] && left[1] != right[1])
            return NO_SOLUTION;

        return "x=" + (right[1] - left[1]) / (left[0] - right[0]);
    }

    /*
        int[0]: coefficient
        int[1]: constant
    */
    private int[] resolve(String s) {
        int[] ans = new int[2];
        int currSign = 1;
        Integer currNum = null;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                currNum = currNum == null ? c - '0' : currNum * 10 + c - '0';
            } else if(c == '+' || c == '-') {
                if(currNum != null)
                    ans[1] += (currSign * currNum);

                currSign = c == '+' ? 1 : -1;
                currNum = null;
            } else if(c == 'x') {
                currNum = currNum == null ? 1 : currNum;
                ans[0] += (currSign * currNum);

                currSign = 1;
                currNum = null;
            }
        }

        if(currNum != null)
            ans[1] += (currSign * currNum);
        return ans;
    }
}
