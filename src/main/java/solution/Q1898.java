package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Maximum Number Of Removable Characters",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/maximum-number-of-removable-characters/"
)
public class Q1898 {
    /*
    * use binary search for k
    * */
    public int maximumRemovals(String s, String p, int[] removable) {
        int m = s.length();
        int n = removable.length;

        int[] rem = new int[m];
        Arrays.fill(rem, -1);
        for(int i = 0; i < n; i++) {
            rem[removable[i]] = i;
        }

        int lo = 0;
        int hi = n;
        int k = 0;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(isSubseqOptimized(s, p, mid, rem)) {
                k = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return k;
    }

    /*
    * this is pretty naive approach to build subsequence and check
    * p is a subsequence of modified s.
    * time complexity is O(3 * n)
    * */
    private String getSubseq(String s, int[] removable, int k) {
        int m = s.length();
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < k; i++) {
            set.add(removable[i]);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            if(set.contains(i)) {
                continue;
            }

            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    private boolean isSubseq(String s, String p) {
        int m = s.length();
        int n = p.length();
        for(int i = 0, j = 0; i < n; i++) {
            char c = p.charAt(i);

            while(j < m && s.charAt(j) != c) {
                j++;
            }
            if(j == m) {
                return false;
            }

            j++;
        }
        return true;
    }

    /*
    * we can mark each removable character by it's position in removable
    * so we can easily check if the character needs to be removed by giving k
    * */
    private boolean isSubseqOptimized(String s, String p, int k, int[] rem) {
        int m = s.length();
        int n = p.length();
        for(int i = 0, j = 0; i < m; i++) {
            if(s.charAt(i) == p.charAt(j) && (rem[i] == -1 || rem[i] > k - 1)) {
                j++;
            }

            if(j == n) {
                return true;
            }
        }
        return false;
    }
}
