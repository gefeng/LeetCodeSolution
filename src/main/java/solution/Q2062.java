package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Vowel Substrings of a String",
        difficulty = QDifficulty.EASY,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/count-vowel-substrings-of-a-string/"
)
public class Q2062 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int countVowelSubstrings(String word) {
        int n = word.length();
        int ans = 0;
        String v = "aeiou";
        int[] cnt = new int[5];
        for(int l = 0, r = 0, k = 0; r < n; r++) {
            int i = v.indexOf(word.charAt(r));

            if(i < 0) {
                l = r + 1;
                k = l;
                cnt = new int[5];
            } else {
                cnt[i] += 1;
                while(hasAll(cnt)) {
                    int j = v.indexOf(word.charAt(k));
                    if(cnt[j] > 1) {
                        cnt[j] -= 1;
                        k++;
                    } else {
                        ans += k - l + 1;
                        break;
                    }
                }
            }
        }

        return ans;
    }

    private boolean hasAll(int[] cnt) {
        for(int i = 0; i < 5; i++) {
            if(cnt[i] == 0) {
                return false;
            }
        }

        return true;
    }
}
