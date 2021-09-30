package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "The k-th Lexicographical String of All Happy Strings of Length n",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/"
)
public class Q1415 {
    /**
     * Time:  O(3 ^ N * N)
     * Space: O(3 ^ N * N)
     * */
    private static final char[] charSet = new char[] {'a', 'b', 'c'};
    public String getHappyString(int n, int k) {
        List<String> happy = new ArrayList<>();

        dfs(n, 0, 'd', new StringBuilder(), happy);

        return happy.size() < k ? "" : happy.get(k - 1);
    }

    private void dfs(int n, int curr, char prev, StringBuilder cand, List<String> happy) {
        if(curr == n) {
            happy.add(cand.toString());
            return;
        }

        for(int i = 0; i < 3; i++) {
            char c = charSet[i];
            if(c != prev) {
                cand.append(c);
                dfs(n, curr + 1, c, cand, happy);
                cand.deleteCharAt(cand.length() - 1);
            }
        }
    }
}
