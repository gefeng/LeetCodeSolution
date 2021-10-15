package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Maximum Number of Occurrences of a Substring",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring/"
)
public class Q1297 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int ans = 0;
        int n = s.length();
        Map<String, Integer> map = new HashMap<>();

        for(int i = minSize; i <= maxSize; i++) {

            int[] cnt = new int[26];

            for(int l = 0, r = 0; r < n; r++) {
                cnt[s.charAt(r) - 'a'] += 1;

                if(r - l + 1 > i) {
                    cnt[s.charAt(l++) - 'a'] -= 1;
                }

                if(r - l + 1 == i) {
                    int m = 0;
                    String ss = s.substring(l, r + 1);
                    for(int j = 0; j < r - l + 1; j++) {
                        m |= 1 << ss.charAt(j) - 'a';
                    }
                    if(Integer.bitCount(m) <= maxLetters) {
                        map.put(ss, map.getOrDefault(ss, 0) + 1);
                    }
                }
            }
        }

        for(int freq : map.values()) {
            ans = Math.max(ans, freq);
        }

        return ans;
    }
}
