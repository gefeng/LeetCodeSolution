package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Queens That Can Attack the King",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/queens-that-can-attack-the-king/"
)
public class Q1222 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] k) {
        List<List<Integer>> ans = new ArrayList<>();

        List<int[]> row = new ArrayList<>();
        List<int[]> col = new ArrayList<>();
        List<int[]> dia1 = new ArrayList<>();
        List<int[]> dia2 = new ArrayList<>();

        for(int[] q : queens) {
            if(q[0] == k[0]) {
                row.add(q);
            }
            if(q[1] == k[1]) {
                col.add(q);
            }
            if(q[0] + q[1] == k[0] + k[1]) {
                dia1.add(q);
            }
            if(q[0] - q[1] == k[0] - k[1]) {
                dia2.add(q);
            }
        }

        findClose(row, k, ans);
        findClose(col, k, ans);
        findClose(dia1, k, ans);
        findClose(dia2, k, ans);

        return ans;
    }

    private void findClose(List<int[]> queens, int[] k, List<List<Integer>> ans) {
        int[] l = null;
        int[] r = null;
        int minL = 8;
        int minR = 8;
        for(int[] q : queens) {
            if(q[0] == k[0]) {
                int d = Math.abs(q[1] - k[1]);
                if(q[1] > k[1] && d < minR) {
                    minR = d;
                    r = q;
                }
                if(q[1] < k[1] && d < minL) {
                    minL = d;
                    l = q;
                }
            } else {
                int d = Math.abs(q[0] - k[0]);
                if(q[0] > k[0] && d < minR) {
                    minR = d;
                    r = q;
                }
                if(q[0] < k[0] && d < minL) {
                    minL = d;
                    l = q;
                }
            }
        }

        if(l != null) {
            ans.add(Arrays.asList(l[0], l[1]));
        }
        if(r != null) {
            ans.add(Arrays.asList(r[0], r[1]));
        }
    }
}
