package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "String Compression II",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/string-compression-ii/"
)
public class Q1531 {
    /**
     * Group consecutive letters as [a,3], [b,2]...
     * Perform dp on groups list, where for each recursion call we can,
     * 1. keep the whole group.
     * 2. or partially remove some letter to reduce the length after compression.
     * 3. or remove any groups between groups[i] and groups[j] where
     *    groups[i] and groups[j] shares the same letter. i.e. aaabbaa,
     *    we can remove bb to achieve better length.
     *
     * Q: can we do both option2 and option3?
     * A: we can but no benefit. we can remove bb and let aaaaa handle the same situation
     *
     * Q: how to achieve option3
     * A: we need to save the length of the previous "same letter group". That's the
     *    third parameters in dp tables.
     *
     * Time:  O(N ^ 2 * K)
     * Space: O(N ^ 2 * K)
     * */
    public int getLengthOfOptimalCompression(String s, int k) {
        int n = s.length();

        List<int[]> groups = new ArrayList<>();
        int l = 0, r = 0;
        while(r < n) {
            if(s.charAt(l) != s.charAt(r)) {
                groups.add(new int[] {s.charAt(l) - 'a', r - l});
                l = r;
            }
            r++;
        }
        groups.add(new int[] {s.charAt(l) - 'a', r - l});

        return dfs(groups, k, 0, 0, new Integer[n][k + 1][n]);
    }

    private int dfs(List<int[]> groups, int k, int i, int pre, Integer[][][] memo) {
        int n = groups.size();

        if(i == n) {
            return 0;
        }

        if(memo[i][k][pre] != null) {
            return memo[i][k][pre];
        }

        int[] group = groups.get(i);
        int cnt = pre + group[1];
        int len = cnt == 1 ? 1 : (cnt < 10 ? 2 : (cnt < 100 ? 3 : 4));

        // keep the group
        int min = dfs(groups, k, i + 1, 0, memo) + len;

        // partially remove
        for(int t : new int[] {0, 1, 9, 99}) {
            int rv = cnt - t;
            if(rv > 0 && rv <= k) {
                min = Math.min(min, dfs(groups, k - rv, i + 1, 0, memo) + (t <= 1 ? t : (t == 9 ? 2 : 3)));
            }
        }

        for(int j = i + 1, left = k; j < n && left >= 0; j++) {
            int[] next = groups.get(j);
            if(group[0] == next[0]) {
                min = Math.min(min, dfs(groups, left, j, cnt, memo));
                break;
            }
            left -= next[1];
        }

        return memo[i][k][pre] = min;
    }
}
