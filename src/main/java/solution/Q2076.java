package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Process Restricted Friend Requests",
        difficulty = QDifficulty.HARD,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/process-restricted-friend-requests/"
)
public class Q2076 {
    /**
     * Time:  O(M * K)
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
    }
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        DSU dsu = new DSU(n);

        int m = requests.length;
        boolean[] ans = new boolean[m];

        for(int i = 0; i < m; i++) {
            int[] r = requests[i];
            int u = r[0];
            int v = r[1];

            if(dsu.find(u) == dsu.find(v)) {
                ans[i] = true;
                continue;
            }

            boolean isOk = true;
            for(int[] res : restrictions) {
                if(dsu.find(res[0]) == dsu.find(u) && dsu.find(res[1]) == dsu.find(v)) {
                    isOk = false;
                    break;
                }
                if(dsu.find(res[0]) == dsu.find(v) && dsu.find(res[1]) == dsu.find(u)) {
                    isOk = false;
                    break;
                }
            }
            if(isOk) {
                dsu.union(u, v);
                ans[i] = true;
            }
        }

        return ans;
    }
}
