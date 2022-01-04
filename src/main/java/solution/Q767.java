package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Reorganize String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/reorganize-string/"
)
public class Q767 {
    /**
     * aaabbbcc
     * -> a_a_a_b_
     * -> ababacbc
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public String reorganizeString(String s) {
        int n = s.length();
        int[] f = new int[26];

        for(int i = 0; i < n; i++) {
            f[s.charAt(i) - 'a']++;
        }

        int[][] fi = new int[26][2];
        for(int i = 0; i < 26; i++) {
            fi[i][0] = i;
            fi[i][1] = f[i];
        }

        Arrays.sort(fi, Comparator.comparing(a -> a[1], Comparator.reverseOrder()));

        if(fi[0][1] > (n + 1) / 2) return "";

        char[] ans = new char[n];
        int p = 0;
        for(int i = 0; i < 26; i++) {
            while(fi[i][1] != 0) {
                ans[p] = (char)(fi[i][0] + 'a');
                fi[i][1]--;
                p += 2;

                if(p > n - 1) p = 1;
            }
        }

        return new String(ans);
    }
}
