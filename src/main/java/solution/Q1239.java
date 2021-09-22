package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.List;

@Problem(
        title = "Maximum Length of a Concatenated String with Unique Characters",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/"
)
public class Q1239 {
    /**
     * Time:  O(2 ^ N)
     * Space: O(N)
     * */
    public int maxLength(List<String> arr) {
        return dfs(arr, 0, "");
    }

    private int dfs(List<String> arr, int curr, String cand) {
        if(cand.length() > 26) {
            return 0;
        }
        if(curr == arr.size()) {
            if(isUnique(cand)) {
                return cand.length();
            }
            return 0;
        }

        int skip = dfs(arr, curr + 1, cand);

        int pick = dfs(arr, curr + 1, cand + arr.get(curr));

        return Math.max(skip, pick);
    }

    private boolean isUnique(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for(int i = 0; i < n; i++) {
            if(++cnt[s.charAt(i) - 'a'] > 1) {
                return false;
            }
        }
        return true;
    }
}
