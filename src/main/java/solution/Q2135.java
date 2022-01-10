package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Count Words Obtained After Adding a Letter",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/count-words-obtained-after-adding-a-letter/"
)
public class Q2135 {
    /**
     * Time:  O(N + M)
     * Space: O(M)
     * */
    public int wordCount(String[] startWords, String[] targetWords) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;

        for(String w : targetWords) {
            int key = 0;
            for(int i = 0; i < w.length(); i++) {
                key |= (1 << w.charAt(i) - 'a');
            }
            map.put(key, map.getOrDefault(key, 0) + 1);
        }


        for(String w : startWords) {
            int key = 0;
            for(int i = 0; i < w.length(); i++) {
                key |= (1 << w.charAt(i) - 'a');
            }

            for(int i = 0; i < 26; i++) {
                if((key & (1 << i)) == 0) {
                    int nkey = key | (1 << i);
                    if(map.containsKey(nkey)) {
                        ans += map.get(nkey);
                        map.remove(nkey);
                    }
                }
            }
        }

        return ans;
    }
}
