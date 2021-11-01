package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Kth Distinct String in an Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/kth-distinct-string-in-an-array/"
)
public class Q2053 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> map = new HashMap<>();

        for(String s : arr) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        int cnt = 0;
        for(String s : arr) {
            if(map.get(s) == 1) {
                cnt++;
            }

            if(cnt == k) {
                return s;
            }
        }
        return "";
    }
}
