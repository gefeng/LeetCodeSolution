package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Unique Number of Occurrences",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/unique-number-of-occurrences/"
)
public class Q1207 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean uniqueOccurrences(int[] arr) {
        int[] cnt = new int[2001];
        for(int x : arr) {
            cnt[x + 1000] += 1;
        }

        boolean[] seen = new boolean[2001];
        for(int i = 0; i < 2001; i++) {
            if(cnt[i] > 0) {
                if(seen[cnt[i]]) {
                    return false;
                }
                seen[cnt[i]] = true;
            }
        }

        return true;
    }
}
