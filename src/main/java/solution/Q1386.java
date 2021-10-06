package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Cinema Seat Allocation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/cinema-seat-allocation/"
)
public class Q1386 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int m = reservedSeats.length;

        long total = (long)n * 2;

        Map<Integer, Integer> map = new HashMap<>();
        for(int[] r : reservedSeats) {
            map.put(r[0], map.getOrDefault(r[0], 0) | (1 << (10 - r[1])));
        }

        int L = 15 << 5;
        int R = 15 << 1;
        int M = 15 << 3;
        for(int mask : map.values()) {
            if((mask & L) == 0 && (mask & R) == 0) {
                continue;
            }

            if((mask & L) == 0 || (mask & R) == 0 || (mask & M) == 0) {
                total -= 1;
            } else {
                total -= 2;
            }

        }

        return (int)total;
    }
}
