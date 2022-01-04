package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Couples Holding Hands",
        difficulty = QDifficulty.HARD,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/couples-holding-hands/"
)
public class Q765 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        DJSet djs = new DJSet(n);

        for(int i = 0; i < n; i += 2) {
            int x = row[i] / 2;
            int y = row[i + 1] / 2;
            djs.union(x, y);
        }

        return n - djs.groups();
    }

    private class DJSet {
        int[] p;
        int[] w;
        DJSet(int n) {
            p = new int[n];
            w = new int[n];
            Arrays.fill(p, -1);
            Arrays.fill(w, 1);
        }

        int find(int i) {
            if(p[i] < 0) return i;
            return p[i] = find(p[i]);
        }

        void union(int i, int j) {
            int x = find(i);
            int y = find(j);
            if(x == y) return;
            if(w[x] >= w[y]) {
                p[y] = x;
                w[x] += w[y];
            } else {
                p[x] = y;
                w[y] += w[x];
            }
        }

        int groups() {
            int cnt = 0;
            for(int i = 0; i < p.length; i++) {
                if(p[i] < 0) cnt++;
            }
            return cnt;
        }
    }
}
