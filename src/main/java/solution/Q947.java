package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Most Stones Removed with Same Row or Column",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/"
)
public class Q947 {
    /**
     * Time:  O(N)
     * Space: O(max_coordinates)
     * */
    public int removeStones(int[][] stones) {
        int ans = 0;
        DSU dsu = new DSU(20005);

        for(int[] s : stones) {
            dsu.union(s[0], s[1] + 10001);
        }

        int[] cnt = new int[20005];
        for(int[] s : stones) {
            int x = dsu.find(s[0]);
            cnt[x]++;
            if(cnt[x] > 1) {
                ans++;
            }
        }

        return ans;
    }

    private class DSU {
        int[] p;
        int[] w;
        DSU(int n) {
            p = new int[n];
            w = new int[n];
            for(int i = 0; i < n; i++) {
                p[i] = i;
            }
        }

        int find(int i) {
            if(p[i] != i) p[i] = find(p[i]);
            return p[i];
        }

        void union(int i, int j) {
            int x = find(i);
            int y = find(j);
            if(x == y) return;
            if(w[x] == w[y]) {
                p[y] = x;
                w[x]++;
            } else if(w[x] > w[y]) {
                p[y] = x;
            } else {
                p[x] = y;
            }
        }
    }
}
