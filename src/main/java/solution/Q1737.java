package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Change Minimum Characters to Satisfy One of Three Conditions",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/change-minimum-characters-to-satisfy-one-of-three-conditions/"
)
public class Q1737 {
    public int minCharacters(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[] cnt1 = new int[27];
        int[] cnt2 = new int[27];

        for(int i = 0; i < m; i++) {
            cnt1[a.charAt(i) - 'a' + 1]++;
        }
        for(int i = 0; i < n; i++) {
            cnt2[b.charAt(i) - 'a' + 1]++;
        }

        for(int i = 1; i < 27; i++) {
            cnt1[i] += cnt1[i - 1];
            cnt2[i] += cnt2[i - 1];
        }

        return Math.min(achieveRuleLess(cnt2, cnt1), Math.min(achieveRuleLess(cnt1, cnt2), achieveRule3(cnt1, cnt2)));
    }

    private int achieveRuleLess(int[] cnt1, int[] cnt2) {
        int cnt = cnt1[26] + cnt2[26];
        for(int i = 1; i < 26; i++) {
            cnt = Math.min(cnt, cnt1[26] - cnt1[i] + cnt2[i]);
        }
        return cnt;
    }

    private int achieveRule3(int[] cnt1, int[] cnt2) {
        int min1 = cnt1[26];
        int min2 = cnt2[26];
        for(int i = 0; i < 26; i++) {
            min1 = Math.min(min1, cnt1[26] - (cnt1[i + 1] - cnt1[i]));
            min2 = Math.min(min2, cnt2[26] - (cnt2[i + 1] - cnt2[i]));
        }
        return min1 + min2;
    }
}
