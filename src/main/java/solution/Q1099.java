package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Two Sum Less Than K",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/two-sum-less-than-k/"
)
public class Q1099 {
    public int twoSumLessThanK(int[] nums, int k) {
        return countingSort(nums, k);
    }

    private int sortAndTwoPointer(int[] nums, int k) {
        int maxSum = -1;
        int sum = 0;
        int left = 0;
        int right = nums.length - 1;
        Arrays.sort(nums);

        while(left < right) {
            sum = nums[left] + nums[right];
            if(sum < k) {
                maxSum = Math.max(maxSum, sum);
                left++;
            } else {
                right--;
            }
        }
        return maxSum;
    }

    // since nums[i] is from 1 to 1000 can use counting sort
    private int countingSort(int[] nums, int k) {
        int[] count = new int[1001];

        for(int n : nums)
            count[n]++;

        int maxSum = -1;
        int sum = 0;
        int left = 0;
        int right = count.length - 1;
        while(left <= right) {
            if(count[left] != 0 && count[right] != 0) {
                if(left == right && count[left] == 1)
                    break;
                sum = left + right;
                if(sum < k) {
                    maxSum = Math.max(maxSum, sum);
                    left++;
                } else {
                    right--;
                }
            } else {
                if(count[left] == 0)
                    left++;
                if(count[right] == 0)
                    right--;
            }
        }

        return maxSum;
    }
}
