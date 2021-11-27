package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Count Common Words With One Occurrence",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/count-common-words-with-one-occurrence/"
)
public class Q2085 {
    /**
     * Time:  O(M + N)
     * Space: O(M + N)
     * */
    public int countWords(String[] words1, String[] words2) {
        int ans = 0;
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        for(String s : words1) {
            map1.put(s, map1.getOrDefault(s, 0) + 1);
        }

        for(String s : words2) {
            map2.put(s, map2.getOrDefault(s, 0) + 1);
        }

        for(String s : words1) {
            if(map2.containsKey(s) && map1.get(s) == 1 && map2.get(s) == 1) {
                ans++;
            }
        }

        return ans;
    }
}
