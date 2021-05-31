package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Longest Substring with At Most Two Distinct Characters",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/"
)
public class Q159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        int n = s.length();
        int l = 0;
        int r = 0;
        int maxLen = 0;

        while(r < n) {
            char c = s.charAt(r);

            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);

            while(freqMap.size() > 2) {
                int cnt = freqMap.get(s.charAt(l));
                if(cnt == 1) {
                    freqMap.remove(s.charAt(l));
                } else {
                    freqMap.put(s.charAt(l), cnt - 1);
                }
                l++;
            }

            maxLen = Math.max(maxLen, r - l + 1);
            r++;
        }

        return maxLen;
    }
}
