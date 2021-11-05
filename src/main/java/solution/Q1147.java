package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Chunked Palindrome Decomposition",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/longest-chunked-palindrome-decomposition/"
)
public class Q1147 {
    /**
     * dp is ok. But greedy is much optimized.
     *
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int longestDecomposition(String text) {
        int n = text.length();

        return solve(text, 0, n - 1);
    }

    private int solve(String s, int l, int r) {
        int n = r - l + 1;
        for(int i = 0; i < n / 2; i++) {
            if(s.substring(l, l + i + 1).equals(s.substring(r - i, r + 1))) {
                return solve(s, l + i + 1, r - i - 1) + 2;
            }
        }
        return l <= r ? 1 : 0;
    }
}
