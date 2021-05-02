package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Continuous Subarray Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/continuous-subarray-sum/"
)
public class Q523 {
    /*
    * 这题蛮技巧的,数学原理是,
    *   x % k = a
    *   y % k = a
    *   (y - x) % k = 0
    *   y - x is sum of nums[i + 1] + nums[i + 2] + .. + nums[j]
    *   根据题目意思 j - i - 1 > 0
    * */
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> rMap = new HashMap<>();
        rMap.put(0, -1);
        int sum = 0;
        int rem = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            rem = sum % k;
            rMap.putIfAbsent(rem, i);
            if(i - rMap.get(rem) > 1) {
                return true;
            }
        }
        return false;
    }
}
