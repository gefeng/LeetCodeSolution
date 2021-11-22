package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Problem(
        title = "Time Based Key-Value Store",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/time-based-key-value-store/"
)
public class Q981 {
    /**
     * Time:  O(logN) for set() and get()
     * Space: O(N)
     * */
    Map<String, TreeMap<Integer, String>> map;
    public Q981() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> e = map.computeIfAbsent(key, k -> new TreeMap<>());
        e.put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> e = map.get(key);

        if(e == null) {
            return "";
        }

        Integer fk = e.floorKey(timestamp);
        if(fk == null) {
            return  "";
        }
        return e.get(fk);
    }
}
