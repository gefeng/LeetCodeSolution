package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Online Majority Element In Subarray",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/online-majority-element-in-subarray/"
)
public class Q1157 {
    /**
     * Time:  O(logN) for query
     * Space: O(N)
     * */
    Map<Integer, List<Integer>> map;
    int[] arr;

    Random rand;
    int tryBound;
    public Q1157(int[] arr) {
        this.arr = arr;
        this.map = new HashMap<>();

        rand = new Random();
        tryBound = 20; // the propability of not finding a majority elements with 20 attampt drops to lower than 1e-6.

        for(int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int query(int left, int right, int threshold) {
        for(int i = 0; i < tryBound; i++) {
            int x = arr[left + rand.nextInt(right - left + 1)];
            List<Integer> l = map.get(x);

            int lower = Collections.binarySearch(l, left);
            int upper = Collections.binarySearch(l, right);

            lower = lower < 0 ? -lower - 1 : lower;
            upper = upper < 0 ? -upper - 1 - 1 : upper;

            if(upper - lower + 1 >= threshold) {
                return x;
            }
        }
        return -1;
    }
}
