package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Find Latest Group of Size M",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/find-latest-group-of-size-m/"
)
public class Q1562 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private int cntm = 0;
    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        int res = -1;
        int[] p = new int[n + 1];
        int[] w = new int[n + 1];

        Arrays.fill(p, -1);

        for(int i = 0; i < n; i++) {
            int node = arr[i];

            p[node] = node;
            w[node]++;
            if(w[node] == m) {
                cntm++;
            }

            if(node - 1 >= 1 && p[node - 1] != -1) {
                union(p, w, node - 1, node, m);
            }
            if(node + 1 <= n && p[node + 1] != -1) {
                union(p, w, node + 1, node, m);
            }

            if(cntm > 0) {
                res = i + 1;
            }
        }

        return res;
    }

    private int find(int[] p, int i) {
        if(p[i] != i) {
            p[i] = find(p, p[i]);
        }
        return p[i];
    }

    private void union(int[] p, int[] w, int i, int j, int m) {
        int x = find(p, i);
        int y = find(p, j);

        if(x == y) {
            return;
        }

        if(w[x] == m) {
            cntm = Math.max(0, cntm - 1);
        }
        if(w[y] == m) {
            cntm = Math.max(0, cntm - 1);
        }

        if(w[x] >= w[y]) {
            p[y] = x;
            w[x] += w[y];
            if(w[x] == m) {
                cntm++;
            }
        } else {
            p[x] = y;
            w[y] += w[x];

            if(w[y] == m) {
                cntm++;
            }
        }
    }
}
