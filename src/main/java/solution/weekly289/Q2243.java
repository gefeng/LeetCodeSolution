package solution.weekly289;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Calculate Digit Sum of a String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/weekly-contest-289/problems/calculate-digit-sum-of-a-string/"
)
public class Q2243 {
    /**
     * Time:  O(N - K) * N
     * Space: O(N)
     * */
    public String digitSum(String s, int k) {
        int n = s.length();
        String cur = s;
        while(cur.length() > k) {
            String nxt = "";
            for(int i = 0; i < cur.length(); i += k) {
                int sum = 0;
                for(int j = i; j < cur.length() && j < i + k; j++) {
                    sum += cur.charAt(j) - '0';
                }

                nxt += Integer.toString(sum);
            }

            cur = nxt;
        }

        return cur;
    }
}
