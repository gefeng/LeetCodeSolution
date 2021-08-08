package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Product of the Length of Two Palindromic Substrings",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-product-of-the-length-of-two-palindromic-substrings/"
)
public class Q1960 {
    /**
     * 抄了一个manacher algorithm的implementation。
     * 这题constraints比较大，需要一个效率高的找palindrome的算法。
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public long maxProduct(String s) {
        long res = 0;
        int n = s.length();
        int[] lps = manacher(s);

        // longest palindrome within s[0, i]
        int[] maxEnd = new int[n];
        // longest palindrome within s[i, n - 1]
        int[] maxBegin = new int[n];

        for(int i = 0; i < n; i++) {
            int right = i + lps[i] - 1;
            maxEnd[right] = Math.max(maxEnd[right], lps[i] * 2 - 1);
        }

        // compare the subpalindrome as well
        for(int i = n - 2; i >= 0; i--) {
            maxEnd[i] = Math.max(maxEnd[i], maxEnd[i + 1] - 2);
        }

        for(int i = 1; i < n; i++) {
            maxEnd[i] = Math.max(maxEnd[i - 1], maxEnd[i]);
        }

        for(int i = n - 1; i >= 0; i--) {
            int left = i - (lps[i] - 1);
            maxBegin[left] = Math.max(maxBegin[left], lps[i] * 2 - 1);
        }

        for(int i = 1; i < n; i++) {
            maxBegin[i] = Math.max(maxBegin[i], maxBegin[i - 1] - 2);
        }

        for(int i = n - 2; i >= 0; i--) {
            maxBegin[i] = Math.max(maxBegin[i + 1], maxBegin[i]);
        }

        for(int i = 0; i < n - 1; i++) {
            res = Math.max(res, (long)maxEnd[i] * maxBegin[i + 1]);
        }

        return res;
    }

    private int[] manacher(String s) {
        int n = s.length();
        int[] d1 = new int[n];
        for (int i = 0, l = 0, r = -1; i < n; i++) {
            int k = (i > r) ? 1 : Math.min(d1[l + r - i], r - i + 1);
            while (i+k < n && i-k >= 0 && s.charAt(i - k) == s.charAt(i + k)) {
                ++k;
            }
            d1[i] = k;
            if (i+k-1 > r) {
                l = i-k+1;
                r = i+k-1;
            }
        }
        return d1;
    }
}
