package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Slowest Key",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/slowest-key/"
)
public class Q1629 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int n = releaseTimes.length;
        int[] cnt = new int[26];

        int prev = 0;
        for(int i = 0; i < n; i++) {
            int c = keysPressed.charAt(i) - 'a';
            int t = releaseTimes[i];
            cnt[c] = Math.max(cnt[c], t - prev);
            prev = t;
        }

        int max = 0;
        char key = 'a';
        for(int i = 0; i < 26; i++) {
            if(cnt[i] >= max) {
                max = cnt[i];
                key = (char)(i + 'a');
            }
        }

        return key;
    }
}
