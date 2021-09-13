package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Number of Balloons",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/maximum-number-of-balloons/"
)
public class Q1189 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maxNumberOfBalloons(String text) {
        int n = text.length();
        int[] cnt = new int[26];

        for(int i = 0; i < n; i++) {
            cnt[text.charAt(i) - 'a']++;
        }

        String s = "balloon";

        int cntOne = Math.min(Math.min(cnt['a' - 'a'], cnt['b' - 'a']), cnt['n' - 'a']);
        int cntTwo = Math.min(cnt['l' - 'a'], cnt['o' - 'a']);

        return Math.min(cntOne, cntTwo / 2);
    }
}
