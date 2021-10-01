package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Design Underground System",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/design-underground-system/"
)
public class Q1396 {
    /**
     * Time:  O(1) for all the operations
     * Space: O(N)
     * */
    Map<Integer, String> checkInS;
    Map<Integer, Integer> checkInT;
    Map<String, int[]> trips;
    public Q1396() {
        checkInS = new HashMap<>();
        checkInT = new HashMap<>();
        trips = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInS.put(id, stationName);
        checkInT.put(id, t);
    }

    public void checkOut(int id, String stationName, int t) {
        String from = checkInS.get(id);
        int startTime = checkInT.get(id);

        int[] pair = trips.computeIfAbsent(from + "-" + stationName, k -> new int[2]);
        pair[0] += t - startTime;
        pair[1] += 1;

        checkInS.remove(id);
        checkInT.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        int[] pair = trips.get(startStation + "-" + endStation);

        return (double)pair[0] / pair[1];
    }
}
