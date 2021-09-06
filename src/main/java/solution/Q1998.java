package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "GCD Sort of an Array",
        difficulty = QDifficulty.HARD,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/gcd-sort-of-an-array/"
)
public class Q1998 {
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
    public boolean gcdSort(int[] nums) {
        int n = nums.length;
        DSU dsu = new DSU(100001);

        for(int i = 0; i < n; i++) {
            int x = nums[i];
            for(int j = 2; j * j <= x; j++) {
                if(x % j == 0) {
                    dsu.union(j, nums[i]);
                }
                while(x % j == 0) {
                    x /= j;
                }
            }
            if(x > 1) {
                dsu.union(x, nums[i]);
            }
        }

        int[] copy = Arrays.copyOf(nums, n);
        Arrays.sort(copy);

        for(int i = 0; i < n; i++) {
            if(copy[i] != nums[i]) {
                if(dsu.find(nums[i]) != dsu.find(copy[i])) {
                    return false;
                }
            }
        }

        return true;
    }
}
