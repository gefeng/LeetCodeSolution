package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Range Frequency Queries",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/range-frequency-queries/"
)
public class Q2080 {
    /**
     * Time:  O(logN)
     * Space: O(N)
     * */
    Map<Integer, List<Integer>> map = new HashMap<>();
    public Q2080(int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int query(int left, int right, int value) {
        List<Integer> l = map.get(value);

        if(l == null) {
            return 0;
        }

        int x = Collections.binarySearch(l, left);
        int y = Collections.binarySearch(l, right + 1);

        if(x < 0) {
            x = ~x;
        }
        if(y < 0) {
            y = ~y;
        }

        return y - x;
    }
}
