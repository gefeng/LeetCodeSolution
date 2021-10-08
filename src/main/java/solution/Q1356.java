package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Sort Integers by The Number of 1 Bits",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits/"
)
public class Q1356 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int[] sortByBits(int[] arr) {
        List<Integer> l = new ArrayList<>();
        for(int num : arr) {
            l.add(num);
        }

        Collections.sort(l, (a, b) -> {
            int x = Integer.bitCount(a);
            int y = Integer.bitCount(b);
            if(x == y) {
                return Integer.compare(a, b);
            }
            return Integer.compare(x, y);
        });

        return l.stream().mapToInt(a -> a.intValue()).toArray();
    }
}
