package solution.weekly286;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Palindrome With Fixed Length",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/contest/weekly-contest-286/problems/find-palindrome-with-fixed-length/"
)
public class Q2217 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public long[] kthPalindrome(int[] queries, int intLength) {
        int n = queries.length;
        long[] ans = new long[n];
        long base = (long)Math.pow(10, (intLength - 1) / 2);
        long lo = base;
        long hi = base * 10 - 1;
        long tot = hi - lo + 1;
        for(int i = 0; i < n; i++) {
            int q = queries[i];
            if(q > tot) {
                ans[i] = -1;
            } else {
                long l = lo + q - 1;
                if(intLength % 2 == 1) {
                    ans[i] = l * base + reverse(l / 10);
                } else {
                    ans[i] = l * base * 10 + reverse(l);
                }
            }
        }
        return ans;
    }

    private long reverse(long x) {
        char[] arr = Long.toString(x).toCharArray();
        for(int l = 0, r = arr.length - 1; l <= r; l++, r--) {
            char c = arr[l];
            arr[l] = arr[r];
            arr[r] = c;
        }

        return Long.parseLong(new String(arr));
    }
}
