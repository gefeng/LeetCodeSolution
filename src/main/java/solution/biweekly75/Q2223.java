package solution.biweekly75;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum of Scores of Built Strings",
        difficulty = QDifficulty.HARD,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/biweekly-contest-75/problems/sum-of-scores-of-built-strings/"
)
public class Q2223 {
    /**
     * Z algorithm
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public long sumScores(String s) {
        char[] arr = s.toCharArray();
        int n = s.length();
        int[] z = new int[n];
        long ans = n;

        int x = 0, y = 0;
        for(int i = 1; i < n; i++) {
            z[i] = Math.max(0, Math.min(z[i - x], y - i + 1));
            while(i + z[i] < n && arr[z[i]] == arr[i + z[i]]) {
                x = i;
                y = i + z[i];
                z[i]++;
            }
        }

        for(int i = 1; i < n; i++) {
            ans += z[i];
        }

        return ans;
    }

    private long rollingHashSol(String s) {
        int n = s.length();
        long ans = n;
        long mod = (long)1e9 + 7;
        long base = 26;
        long d = 1;
        long[] p = new long[n];
        long[] h = new long[n];

        long hash = 0;
        for(int i = 0; i < n; i++) {
            hash = (hash * base % mod + s.charAt(i) - 'a') % mod;

            h[i] = hash;
            p[i] = d;

            d = d * base % mod;
        }

        for(int i = 1; i < n; i++) {
            int lo = i;
            int hi = n - 1;
            int j = i - 1;
            hash = 0;
            while(lo <= hi) {
                int mid = lo + hi >> 1;

                hash = (h[mid] - h[i - 1] * p[mid - i + 1] % mod + mod) % mod;

                if(hash == h[mid - i]) {
                    lo = mid + 1;
                    j = mid;
                } else {
                    hi = mid - 1;
                }
            }

            ans += (j - i + 1);
        }

        return ans;
    }
}
