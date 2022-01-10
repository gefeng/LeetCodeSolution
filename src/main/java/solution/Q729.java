package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.TreeMap;

@Problem(
        title = "My Calendar I",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/my-calendar-i/"
)
public class Q729 {
    /**
     * Time:  O(logN)
     * Space: O(N)
     * */
    private TreeMap<Integer, Integer> events;
    public Q729() {
        events = new TreeMap();
    }

    public boolean book(int start, int end) {
        Integer floor = events.floorKey(start);
        Integer ceiling = events.ceilingKey(start);

        boolean overlapPrev = floor == null ? false : start < events.get(floor);
        boolean overlapNext = ceiling == null ? false : end > ceiling;

        if(overlapPrev || overlapNext) {
            return false;
        }

        events.put(start, end);
        return true;
    }
}
