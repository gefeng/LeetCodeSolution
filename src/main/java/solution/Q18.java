package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "4Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/4sum/"
)
public class Q18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++) {
            for(List<Integer> x : threeSum(nums, i + 1, target - nums[i])) {
                ans.add(new ArrayList<>(x));
                ans.get(ans.size() - 1).add(nums[i]);
            }
            while(i + 1 < nums.length && nums[i] == nums[i + 1])
                i++;
        }
        return ans;
    }

    private List<List<Integer>> threeSum(int[] nums, int start, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = start; i < nums.length; i++) {
            for(List<Integer> x : twoSum(nums, i + 1, target - nums[i])) {
                ans.add(new ArrayList<>(x));
                ans.get(ans.size() - 1).add(nums[i]);
            }
            while(i + 1 < nums.length && nums[i] == nums[i + 1])
                i++;
        }
        return ans;
    }

    /*
        0 1 1 1 2 2 2 3 4   target = 3
                h
                t
    */
    private List<List<Integer>> twoSum(int[] nums, int start, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int head = start;
        int tail = nums.length - 1;
        while(head < tail) {
            int x = nums[head];
            int y = nums[tail];
            int sum = x + y;
            if(sum == target) {
                ans.add(Arrays.asList(x, y));
                while(head < tail && nums[head] == x)
                    head++;
                while(head < tail && nums[tail] == y)
                    tail--;
            } else if(sum < target) {
                head++;
            } else {
                tail--;
            }
        }
        return ans;
    }
}
