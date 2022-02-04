package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Design Log Storage System",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/design-log-storage-system/"
)
public class Q635 {
    Map<Integer, String> map;
    Map<String, Integer> last;
    public Q635() {
        map = new HashMap<>();
        last = new HashMap<>() {
            {
                put("Year", 4);
                put("Month", 7);
                put("Day", 10);
                put("Hour", 13);
                put("Minute", 16);
                put("Second", 19);
            }
        };
    }

    /**
     * Time: O(1)
     * */
    public void put(int id, String timestamp) {
        map.put(id, timestamp);
    }

    /**
     * Time: O(N)
     * */
    public List<Integer> retrieve(String start, String end, String granularity) {
        List<Integer> res = new ArrayList<>();
        int len = last.get(granularity);
        String st = start.substring(0, len);
        String ed = end.substring(0, len);

        for(int id : map.keySet()) {
            String t = map.get(id).substring(0, len);

            if(st.compareTo(t) <= 0 && ed.compareTo(t) >= 0) {
                res.add(id);
            }
        }

        return res;
    }
}
