package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Similar String Groups",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/similar-string-groups/"
)
public class Q839 {
    /**
     * Time:  O(N ^ 3)
     * Space: O(N)
     * */
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        DSU dsu = new DSU(n);

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(similar(strs[i], strs[j])) {
                    dsu.union(i, j);
                }
            }
        }

        return dsu.groups();
    }

    private boolean similar(String s1, String s2) {
        int n = s1.length();

        int d1 = -1;
        int d2 = -1;
        for(int i = 0; i < n; i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                if(d1 != -1 && d2 != -1) return false;
                if(d1 == -1) d1 = i;
                else d2 = i;
            }
        }

        if(d1 == -1 && d2 == -1) return true;
        if(d1 == -1 || d2 == -1) return false;
        if(s1.charAt(d1) == s2.charAt(d2) && s1.charAt(d2) == s2.charAt(d1)) return true;
        return false;
    }

    private class DSU {
        int[] p;
        int[] w;
        DSU(int n) {
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
