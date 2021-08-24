package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Find Array Given Subset Sums",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/find-array-given-subset-sums/"
)
public class Q1982 {
    /**
     * let a be the largest number in sums
     * let b be the second largest number in sums
     *
     * x = a - b
     * x could be,
     * 1. 0 which means original array contains 0.  i.e. [0, 1, 2]  a = 3, b =3
     * 2. smallest positive number in original array. i.e. [-5, 2, 3]  a = 5, b = 3
     * 3. largest negative number in original array * -1. i.e. [-1, 2, 5] a = 7, b = 6
     *
     * Time:  O(2 ^ N * log(2 ^ N))
     * Space: O(2 ^ N)
     * */
    public int[] recoverArray(int n, int[] sums) {
        Arrays.sort(sums);

        int[] res = new int[n];

        dfs(sums, res, 0);

        return res;
    }

    private void dfs(int[] sums, int[] res, int cur) {
        int len = sums.length;
        if(len < 2) {
            return;
        }

        int x = sums[len - 1] - sums[len - 2];

        int[] cnt = new int[20001];
        for(int sum : sums) {
            cnt[sum + 10000]++;
        }

        int[] l = new int[len / 2];
        int[] r = new int[len / 2];
        for(int i = 0, j = 0, k = len - 1; k >= 0; k--) {
            if(cnt[sums[k] + 10000] == 0) {
                continue;
            }
            if(cnt[sums[k] + 10000] > 0) {
                r[j++] = sums[k];
                l[i++] = sums[k] - x;
                cnt[sums[k] + 10000]--;
                cnt[sums[k] - x + 10000]--;
            }
        }

        int[] next = null;
        for(int i = 0; i < len / 2; i++) {
            if(r[i] == x && l[i] == 0) {
                res[cur] = x;
                next = reverse(l);
                break;
            }
        }

        if(next == null) {
            res[cur] = -x;
            next = reverse(r);
        }

        dfs(next, res, cur + 1);
    }

    private int[] reverse(int[] a) {
        int n = a.length;
        int[] ret = new int[n];
        for(int i = n - 1; i >= 0; i--) {
            ret[n - 1 - i] = a[i];
        }
        return ret;
    }
}
