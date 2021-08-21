package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Heaters",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/heaters/"
)
public class Q475 {
    /**
     * M denotes # of houses, N denotes # of heaters
     *
     * Time:  O(N * logN + M * logN)
     * Space: O(logN)
     * */
    public int findRadius(int[] houses, int[] heaters) {
        int m = houses.length;
        int n = heaters.length;
        int res = 0;

        Arrays.sort(heaters);

        for(int i = 0; i < m; i++) {
            int h = houses[i];
            int close = 0;
            int idx = Arrays.binarySearch(heaters, h);
            if(idx >= 0) {
                close = heaters[idx];
            } else {
                idx = -idx - 1;
                if(idx == n) {
                    close = heaters[idx - 1];
                } else if(idx == 0) {
                    close = heaters[idx];
                } else {
                    close = heaters[idx] - h < h - heaters[idx - 1] ? heaters[idx] : heaters[idx - 1];
                }
            }

            res = Math.max(res, Math.abs(close - h));
        }
        return res;
    }
}
