package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Generalized Abbreviation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/generalized-abbreviation/"
)
public class Q320 {
    public List<String> generateAbbreviations(String word) {
        return bitMaskSol(word);
    }

    /**
     * Time:  O(2 ^ N * N)
     * Space: O(2 ^ N)
     * */
    private List<String> backTrackSol(String word) {
        List<String> res = new ArrayList<>();

        dfs(word, 0, 0, "", res);

        return res;
    }
    private void dfs(String word, int i, int cnt, String cand, List<String> res) {
        int n = word.length();

        if(i == n) {
            if(cnt > 0) {
                res.add(cand + Integer.toString(cnt));
            } else {
                res.add(cand);
            }

            return;
        }

        // keep
        if(cnt > 0) {
            dfs(word, i + 1, 0, cand + Integer.toString(cnt) + word.charAt(i), res);
        } else {
            dfs(word, i + 1, 0, cand + word.charAt(i), res);
        }

        // abbreviate
        dfs(word, i + 1, cnt + 1, cand, res);
    }

    /**
     * Time:  O(2 ^ N * N)
     * Space: O(2 ^ N)
     * */
    private List<String> bitMaskSol(String word) {
        List<String> res = new ArrayList<>();
        dfs(word, 0, 0, res);
        return res;
    }

    private void dfs(String word, int i, int mask, List<String> res) {
        int n = word.length();

        if(i == n) {
            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            for(int j = 0; j < n; j++) {
                if((mask & (1 << j)) == 0) {
                    if(cnt != 0) {
                        sb.append(cnt);
                        cnt = 0;
                    }
                    sb.append(word.charAt(j));
                } else {
                    cnt++;
                }
            }
            if(cnt > 0) {
                sb.append(cnt);
            }

            res.add(sb.toString());

            return;
        }

        dfs(word, i + 1, mask, res);
        dfs(word, i + 1, mask | (1 << i), res);
    }
}
