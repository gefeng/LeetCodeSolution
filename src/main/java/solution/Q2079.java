package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Watering Plants",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/watering-plants/"
)
public class Q2079 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int wateringPlants(int[] plants, int capacity) {
        int ans = 1;
        int n = plants.length;

        int cnt = capacity;
        for(int i = 0; i < n - 1; i++) {
            cnt -= plants[i];
            if(cnt < plants[i + 1]) {
                ans += 2 * (i + 1);
                cnt = capacity;
            }

            ans++;
        }

        return ans;
    }
}
