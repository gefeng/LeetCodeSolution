package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Bulb Switcher III",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/bulb-switcher-iii/"
)
public class Q1375 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int numTimesAllBlue(int[] light) {
        int ans = 0;
        int sum1 = 0;
        int sum2 = 0;
        int n = light.length;

        for(int i = 0; i < n; i++) {
            sum1 += i + 1;
            sum2 += light[i];
            if(sum1 == sum2) {
                ans++;
            }
        }

        return ans;
    }
}
