package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Problem(
        title = "Two Sum III - Data structure design",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/two-sum-iii-data-structure-design/"
)
public class Q170 {
    HashMap<Integer, Integer> map;
    /** Initialize your data structure here. */
    public Q170() {
        map = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for(int n : map.keySet()) {
            int r = value - n;
            if((r == n && map.get(n) > 1) || ((r != n) && map.containsKey(r)))
                return true;
        }
        return false;
    }
}
