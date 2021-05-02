package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Longest Substring with At Most K Distinct Characters",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/"
)
public class Q340 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> seenMap = new HashMap<>();
        int maxLen = 0;
        for(int l = 0, r = 0; r < s.length(); r++) {
            char c = s.charAt(r);

            seenMap.put(c, seenMap.getOrDefault(c, 0) + 1);

            while(seenMap.size() > k) {
                char seen = s.charAt(l);
                int count = seenMap.get(seen);
                if(count == 1) {
                    seenMap.remove(seen);
                } else {
                    seenMap.put(seen, count - 1);
                }
                l++;
            }

            maxLen = Math.max(maxLen, r - l + 1);
        }

        return maxLen;
    }
}
