package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Split a String Into the Max Number of Unique Substring",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/"
)
public class Q1593 {
    /**
     * Time:  O(2 ^ N * N)
     * Space: O(N)
     * */
    public int maxUniqueSplit(String s) {
        return dfs(s, 0, new HashSet<>());
    }

    private int dfs(String s, int start, Set<String> seen) {
        int n = s.length();

        if(start == n) {
            return 0;
        }

        int max = 0;
        for(int end = start + 1; end <= n; end++) {
            String ss = s.substring(start, end);
            if(!seen.contains(ss)) {
                seen.add(ss);
                max = Math.max(max, dfs(s, end, seen) + 1);
                seen.remove(ss);
            }
        }

        return max;
    }
}
