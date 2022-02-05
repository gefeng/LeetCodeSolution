package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Lexicographically Smallest Equivalent String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/lexicographically-smallest-equivalent-string/"
)
public class Q1061 {
    /**
     * Time:  O(M + N)
     * Space: O(N)
     * */
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        DJSet djs = new DJSet(26);
        int m = s1.length();
        int n = baseStr.length();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < m; i++) {
            djs.union(s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }

        for(int i = 0; i < n; i++) {
            sb.append((char)('a' + djs.min(baseStr.charAt(i) - 'a')));
        }

        return sb.toString();
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
        int min(int i) {
            int x = find(i);
            int min = 0;
            for(int j = 0; j < p.length; j++) {
                if(find(j) == x) {
                    min = j;
                    break;
                }
            }
            return min;
        }
    }
}
