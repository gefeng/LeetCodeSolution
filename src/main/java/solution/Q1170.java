package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Compare Strings by Frequency of the Smallest Character",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/compare-strings-by-frequency-of-the-smallest-character/"
)
public class Q1170 {
    /**
     * Time:  O(M * L + N * L)
     * Space: O(M + N)
     * */
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int m = queries.length;
        int n = words.length;
        int[] map = new int[n];
        int[] ans = new int[m];

        for(int i = 0; i < n; i++) {
            String w = words[i];
            int[] cnt = new int[26];
            for(int j = 0; j < w.length(); j++) {
                cnt[w.charAt(j) - 'a']++;
            }
            for(int j = 0; j < 26; j++) {
                if(cnt[j] > 0) {
                    map[i] = cnt[j];
                    break;
                }
            }
        }

        for(int i = 0; i < m; i++) {
            String q = queries[i];

            int[] cnt = new int[26];
            int f = 0;
            for(int j = 0; j < q.length(); j++) {
                cnt[q.charAt(j) - 'a']++;
            }
            for(int j = 0; j < 26; j++) {
                if(cnt[j] > 0) {
                    f = cnt[j];
                    break;
                }
            }

            int tot = 0;
            for(int x : map) {
                if(x > f) {
                    tot++;
                }
            }

            ans[i] = tot;
        }

        return ans;
    }
}
