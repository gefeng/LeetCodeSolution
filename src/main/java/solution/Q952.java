package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Largest Component Size by Common Factor",
        difficulty = QDifficulty.HARD,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/largest-component-size-by-common-factor/"
)
public class Q952 {
    /**
     * Prime factorization.
     *
     * Time:  O(N * sqrt(N))
     * Space: O(max(nums))
     * */
    private class DSU {
        private int[] p;
        private int[] w;
        DSU(int n) {
            p = new int[n];
            w = new int[n];
            for(int i = 0; i < n; i++) {
                p[i] = i;
            }
        }
        int find(int i) {
            if(p[i] != i) {
                p[i] = find(p[i]);
            }
            return p[i];
        }
        void union(int i, int j) {
            int x = find(i);
            int y = find(j);
            if(x == y) {
                return;
            }

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
    public int largestComponentSize(int[] nums) {
        int n = nums.length;
        int res = 0;
        DSU dsu = new DSU(100001);

        for(int num : nums) {
            int x = num;
            for(int i = 2; i * i <= x; i++) {
                if(num % i == 0) {
                    dsu.union(i, num);
                }
                while(x % i == 0) {
                    x /= i;
                }
            }
            if(x > 1) {
                dsu.union(x, num);
            }
        }

        int[] cnt = new int[100001];
        for(int i = 0; i < n; i++) {
            int x = dsu.find(nums[i]);
            cnt[x]++;
            res = Math.max(res, cnt[x]);
        }

        return res;
    }
}
