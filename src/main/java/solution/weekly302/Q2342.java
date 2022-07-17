package solution.weekly302;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Max Sum of a Pair With Equal Sum of Digits",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/contest/weekly-contest-302/problems/max-sum-of-a-pair-with-equal-sum-of-digits/"
)
public class Q2342 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int maximumSum(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = -1;

        for(int x : nums) {
            int sum = 0;
            int y = x;
            while(y > 0) {
                sum += y % 10;
                y /= 10;
            }

            if(map.containsKey(sum)) {
                ans = Math.max(ans, map.get(sum) + x);
                if(x > map.get(sum)) {
                    map.put(sum, x);
                }
            } else {
                map.put(sum, x);
            }
        }

        return ans;
    }
}
