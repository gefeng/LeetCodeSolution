package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Destroying Asteroids",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/destroying-asteroids/"
)
public class Q2126 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        int n = asteroids.length;
        Arrays.sort(asteroids);
        long cur = mass;
        for(int i = 0; i < n; i++) {
            if(asteroids[i] > cur) {
                return false;
            }

            cur += asteroids[i];
        }

        return true;
    }
}
