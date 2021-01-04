package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Map;
import java.util.TreeMap;

@Problem(
        title = "Car Pooling",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/car-pooling/"
)
public class Q1094 {
    public boolean carPoolingTreeMap(int[][] trips, int capacity) {
        Map<Integer, Integer> timestamp = new TreeMap<>();
        for(int i = 0; i < trips.length; i++) {
            timestamp.put(trips[i][1], timestamp.getOrDefault(trips[i][1], 0) + trips[i][0]);
            timestamp.put(trips[i][2], timestamp.getOrDefault(trips[i][2], 0) - trips[i][0]);
        }

        int amount = 0;
        for(int value : timestamp.values()) {
            amount += value;
            if(amount > capacity)
                return false;
        }

        return true;
    }

    public boolean carPoolingBucket(int[][] trips, int capacity) {
        int[] buckets = new int[1001];
        for(int[] trip : trips) {
            buckets[trip[1]] += trip[0];
            buckets[trip[2]] -= trip[0];
        }

        int amount = 0;
        for(int i = 1; i < 1001; i++) {
            amount += buckets[i];
            if(amount > capacity)
                return false;
        }

        return true;
    }
}
