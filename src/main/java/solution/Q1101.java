package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "The Earliest Moment When Everyone Become Friends",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/"
)
public class Q1101 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
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

        boolean allCon() {
            int head = find(0);
            for(int i = 1; i < p.length; i++) {
                if(find(i) != head) {
                    return false;
                }
            }

            return true;
        }
    }
    public int earliestAcq(int[][] logs, int n) {
        int ans = 0;
        DSU dsu = new DSU(n);

        Arrays.sort(logs, Comparator.comparingInt(a -> a[0]));

        for(int[] log : logs) {
            int a = log[1];
            int b = log[2];
            int x = dsu.find(a);
            int y = dsu.find(b);

            if(x != y) {
                dsu.union(x, y);
                ans = log[0];
            }
        }

        return dsu.allCon() ? ans : -1;
    }
}
