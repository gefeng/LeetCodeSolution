package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Equivalent Domino Pairs",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/number-of-equivalent-domino-pairs/"
)
public class Q1128 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int numEquivDominoPairs(int[][] dominoes) {
        int ans = 0;
        int n = dominoes.length;
        int[] cnt = new int[100];

        for(int[] d : dominoes) {
            if(d[0] > d[1]) {
                int temp = d[0];
                d[0] = d[1];
                d[1] = temp;
            }

            int hash = d[0] * 10 + d[1];

            ans += cnt[hash];

            cnt[hash] += 1;

        }

        return ans;
    }
}
