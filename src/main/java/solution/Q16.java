package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "3Sum Closest",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/3sum-closest/"
)
public class Q16 {
    public int threeSumClosest(int[] nums, int target) {
        int ans = 0;
        int minDist = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++) {
            int head = i + 1;
            int tail = nums.length - 1;
            while(head < tail) {
                int sum = nums[head] + nums[tail] + nums[i];
                int dist = Math.abs(sum - target);
                if(dist < minDist) {
                    minDist = dist;
                    ans = sum;
                }
                if(sum == target)
                    return sum;
                if(sum < target)
                    head++;
                else
                    tail--;
            }
        }
        return ans;
    }
}
