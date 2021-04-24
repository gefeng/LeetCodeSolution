package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Find K-Length Substrings With No Repeated Characters",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/"
)
public class Q1100 {
    public int numKLenSubstrNoRepeats(String S, int K) {
        int[] seen = new int[26];
        Arrays.fill(seen, -1);

        int count = 0;
        int l = 0;
        int r = 0;
        while(r < S.length()) {
            char c = S.charAt(r);
            if(seen[c - 'a'] != -1) {
                seen[S.charAt(l++) - 'a'] = -1;
            } else {
                seen[c - 'a'] = r;

                if(r - l + 1 == K) {
                    count++;
                    seen[S.charAt(l++) - 'a'] = -1;
                }

                r++;
            }

        }

        return count;
    }
}
