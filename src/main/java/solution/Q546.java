package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Remove Boxes",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/remove-boxes/"
)
public class Q546 {
    /**
     * Time:  O(N ^ 4)
     * Space: O(N ^ 3)
     * */
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        List<int[]> groups = new ArrayList<>();
        int l = 0;
        int r = 0;
        while(r < n) {
            if(boxes[l] != boxes[r]) {
                groups.add(new int[] {boxes[l], r - l});
                l = r;
            }
            r++;
        }
        groups.add(new int[] {boxes[l], r - l});

        int m = groups.size();
        return  dfs(groups, 0, m - 1, 0, new Integer[m][m][n]);
    }

    private int dfs(List<int[]> groups, int start, int end, int lastCnt, Integer[][][] memo) {
        int n = groups.size();

        if(start == end) {
            return (groups.get(start)[1] + lastCnt) * (groups.get(start)[1] + lastCnt);
        }

        if(memo[start][end][lastCnt] != null) {
            return memo[start][end][lastCnt];
        }

        int[] group = groups.get(start);
        int cnt = group[1] + lastCnt;
        int max = 0;

        // remove the group
        max = dfs(groups, start + 1, end, 0, memo) + cnt * cnt;

        // merge
        for(int j = start + 1; j <= end; j++) {
            int[] next = groups.get(j);
            if(next[0] == group[0]) {
                max = Math.max(max, dfs(groups, start + 1, j - 1, 0, memo) + dfs(groups, j, end, cnt, memo));
            }
        }

        return memo[start][end][lastCnt] = max;
    }
}
