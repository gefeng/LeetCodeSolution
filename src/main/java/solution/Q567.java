package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Permutation in String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/permutation-in-string/"
)
public class Q567 {
    public boolean checkInclusion(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        if(m > n) {
            return false;
        }

        int[] s1countMap = new int[26];
        for(int i = 0; i < m; i++) {
            char c = s1.charAt(i);
            s1countMap[c - 'a']++;
        }

        int[] s2countMap = new int[26];
        int l = 0;
        int r = 0;
        for(; r < n; r++) {
            s2countMap[s2.charAt(r) - 'a']++;
            if(r - l + 1 == m) {
                if(isSubstring(s1countMap, s2countMap)) {
                    return true;
                }
                s2countMap[s2.charAt(l++) - 'a']--;
            }
        }

        return false;
    }

    private boolean isSubstring(int[] countMap1, int[] countMap2) {
        for(int i = 0; i < countMap1.length; i++) {
            if(countMap1[i] != countMap2[i]) {
                return false;
            }
        }

        return true;
    }
}
