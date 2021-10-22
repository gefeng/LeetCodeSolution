package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Smallest Common Region",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/smallest-common-region/"
)
public class Q1257 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, String> map = new HashMap<>();

        for(List<String> l : regions) {
            String p = l.get(0);
            for(int i = 1; i < l.size(); i++) {
                map.put(l.get(i), p);
            }
        }

        Set<String> set = new HashSet<>();
        String cur = region1;
        set.add(cur);
        while(map.containsKey(cur)) {
            cur = map.get(cur);
            set.add(cur);
        }

        cur = region2;
        while(map.containsKey(cur)) {
            if(set.contains(cur)) {
                break;
            }
            cur = map.get(cur);
        }

        return cur;
    }
}
