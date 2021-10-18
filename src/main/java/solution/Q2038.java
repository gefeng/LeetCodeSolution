package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Remove Colored Pieces if Both Neighbors are the Same Color",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/"
)
public class Q2038 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean winnerOfGame(String colors) {
        int n = colors.length();
        int[] cnt = new int[2];

        for(int i = 1; i < n - 1; i++) {
            char c = colors.charAt(i);
            if(c == colors.charAt(i - 1) && c == colors.charAt(i + 1)) {
                cnt[c - 'A']++;
            }
        }

        return cnt[0] > cnt[1];
    }
}
