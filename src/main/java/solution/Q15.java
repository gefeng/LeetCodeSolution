package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "3Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/3sum/"
)
public class Q15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if(nums.length < 3)
            return ans;

        Arrays.sort(nums);
        Integer prev = null;
        for(int i = 0; i < nums.length - 2; i++) {
            if(prev == null || prev != nums[i])
                twoSum(nums, i, ans);
            prev = nums[i];
        }
        return ans;
    }

    private void twoSum(int[] nums, int targetIdx, List<List<Integer>> ans) {
        int target = -nums[targetIdx];
        int head = targetIdx + 1;
        int tail = nums.length - 1;
        while(head < tail) {
            int x = nums[head];
            int y = nums[tail];
            int sum = x + y;
            if(sum == target) {
                ans.add(Arrays.asList(-target, x, y));
                while(head < tail && nums[head] == x)
                    head++;
                while(head < tail && nums[tail] == y)
                    tail--;
            } else if(sum > target)
                tail--;
            else
                head++;
        }
    }
}
