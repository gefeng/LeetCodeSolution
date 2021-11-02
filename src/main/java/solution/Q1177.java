package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Can Make Palindrome from Substring",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/can-make-palindrome-from-substring/"
)
public class Q1177 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> ans = new ArrayList<>();
        int n = s.length();

        int[][] cnt = new int[n + 1][26];
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < 26; j++) {
                cnt[i][j] = cnt[i - 1][j];
            }
            cnt[i][s.charAt(i - 1) - 'a']++;
        }

        for(int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int k = q[2];
            int[] c = new int[26];
            int odd = 0;
            for(int i = 0; i < 26; i++) {
                c[i] = cnt[r + 1][i] - cnt[l][i];
                if(c[i] % 2 == 1) {
                    odd++;
                }
            }

            ans.add(odd / 2 <= k);
        }

        return ans;
    }
}
