package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.TreeMap;

@Problem(
        title = "My Calendar II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/my-calendar-ii/"
)
public class Q731 {
    /**
     * Count the boundary, similar to sweep line
     *
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    TreeMap<Integer, Integer> map;

    public Q731() {
        map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);

        int sum = 0;
        for(int v : map.values()) {
            sum += v;
            if(sum > 2) {
                map.put(start, map.get(start) - 1);
                map.put(end, map.get(end) + 1);
                return false;
            }
        }

        return true;
    }
}
