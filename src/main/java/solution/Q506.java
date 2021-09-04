package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Relative Ranks",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/relative-ranks/"
)
public class Q506 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        int[][] s = new int[n][2];
        String[] res = new String[n];

        for(int i = 0; i < n; i++) {
            s[i][0] = score[i];
            s[i][1] = i;
        }

        Arrays.sort(s, Comparator.comparing(a -> a[0], Comparator.reverseOrder()));

        for(int i = 0; i < n; i++) {
            String rank = "";
            if(i == 0) {
                rank = "Gold Medal";
            } else if(i == 1) {
                rank = "Silver Medal";
            } else if(i == 2) {
                rank = "Bronze Medal";
            } else {
                rank = Integer.toString(i + 1);
            }
            res[s[i][1]] = rank;
        }

        return res;
    }
}
