package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Uncommon Words from Two Sentences",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/uncommon-words-from-two-sentences/"
)
public class Q884 {
    /**
     * Time:  O(M + N)
     * Space: O(M + N)
     * */
    public String[] uncommonFromSentences(String s1, String s2) {
        List<String> ans = new ArrayList<>();
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        String[] arr1 = s1.split(" ");
        String[] arr2 = s2.split(" ");

        for(String s : arr1) {
            map1.put(s, map1.getOrDefault(s, 0) + 1);
        }
        for(String s : arr2) {
            map2.put(s, map2.getOrDefault(s, 0) + 1);
        }

        for(String s : map1.keySet()) {
            if(!map2.containsKey(s) && map1.get(s) == 1) {
                ans.add(s);
            }
        }

        for(String s : map2.keySet()) {
            if(!map1.containsKey(s) && map2.get(s) == 1) {
                ans.add(s);
            }
        }

        return ans.toArray(new String[ans.size()]);
    }
}
