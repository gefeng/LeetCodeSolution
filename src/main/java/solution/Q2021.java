package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.TreeMap;

@Problem(
        title = "Brightest Position on Street",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/brightest-position-on-street/"
)
public class Q2021 {
    /**
     * line sweep
     *
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int brightestPosition(int[][] lights) {
        int n = lights.length;
        int ans = 0;

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for(int[] light : lights) {
            int l = light[0] - light[1];
            int r = light[0] + light[1];

            map.put(l, map.getOrDefault(l, 0) + 1);
            map.put(r + 1, map.getOrDefault(r + 1, 0) - 1);
        }

        int b = 0;
        int max = 0;
        for(int k : map.keySet()) {
            b += map.get(k);
            if(b > max) {
                ans = k;
                max = b;
            }
        }

        return ans;
    }
}
