package solution.biweekly81;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Count Unreachable Pairs of Nodes in an Undirected Graph",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/contest/biweekly-contest-81/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/"
)
public class Q2316 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public long countPairs(int n, int[][] edges) {
        UF uf = new UF(n);
        long ans = 0;

        for(int[] e : edges) {
            uf.unite(e[0], e[1]);
        }

        for(int i = 0; i < n; i++) {
            int x = uf.size(i);
            int y = n - x;

            ans += y;
        }

        return ans / 2;
    }

    private class UF {
        int[] p;
        int[] w;
        UF(int n) {
            p = new int[n];
            w = new int[n];
            Arrays.fill(p, -1);
            Arrays.fill(w, 1);
        }
        int find(int i) {
            if(p[i] < 0) return i;
            return p[i] = find(p[i]);
        }
        void unite(int i, int j) {
            int x = find(i);
            int y = find(j);
            if(x == y) {
                return;
            }
            if(w[x] >= w[y]) {
                p[y] = x;
                w[x] += w[y];
            } else {
                p[x] = y;
                w[y] += w[x];
            }
        }
        int size(int i) {
            return w[find(i)];
        }
        int count() {
            int cnt = 0;
            int n = p.length;
            for(int i = 0; i < n; i++) {
                if(p[i] < 0) {
                    cnt++;
                }
            }
            return cnt;
        }
    }
}
