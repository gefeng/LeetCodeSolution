package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Rank Teams by Votes",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/rank-teams-by-votes/"
)
public class Q1366 {
    /**
     * Time:  O(M * N)
     * Space: O(M * M)
     * */
    public String rankTeams(String[] votes) {
        int n = votes.length;
        int m = votes[0].length();

        List<Integer> arr = new ArrayList<>();
        for(int i = 0; i < 26; i++) {
            arr.add(i);
        }

        int[][] rank = new int[26][26];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                rank[i][votes[j].charAt(i) - 'A']++;
            }
        }

        //rank[i]
        Collections.sort(arr, (a, b) -> {
            int ret = 0;
            for(int i = 0; i < m; i++) {
                int res = Integer.compare(rank[i][b], rank[i][a]);
                if(res != 0) {
                    return res;
                }
            }

            return Integer.compare(a, b);
        });

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 26; i++) {
            sb.append((char)(arr.get(i) + 'A'));
        }

        return sb.toString().substring(0, m);
    }
}
