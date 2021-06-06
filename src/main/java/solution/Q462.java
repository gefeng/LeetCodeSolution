package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Random;

@Problem(
        title = "Minimum Moves to Equal Array Elements II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/"
)
public class Q462 {
    public int minMoves2(int[] nums) {
        return quickSelectSolution(nums);
    }

    private int mathSolution(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int mid = nums[n / 2];

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            cnt += Math.abs(nums[i] - mid);
        }
        return cnt;
    }

    Random rand = new Random();
    private int quickSelectSolution(int[] nums) {
        int k = nums.length / 2 + 1;
        int mid = quickSelect(nums, 0, nums.length - 1, k);

        int cnt = 0;
        for(int i = 0; i < nums.length; i++) {
            cnt += Math.abs(nums[i] - mid);
        }
        return cnt;
    }

    private int quickSelect(int[] nums, int start, int end, int k) {
        int pivot = findPivot(nums, start, end);

        if(pivot == k - 1) {
            return nums[pivot];
        } else if(pivot > k - 1) {
            return quickSelect(nums, start, pivot - 1, k);
        } else {
            return quickSelect(nums, pivot + 1, end, k);
        }
    }

    private int findPivot(int[] nums, int start, int end) {
        int idx = rand.nextInt(end - start + 1) + start;

        swap(nums, idx, end);
        int pivot = start;
        for(int i = start; i < end; i++) {
            if(nums[i] < nums[end]) {
                swap(nums, i, pivot);
                pivot++;
            }
        }
        swap(nums, pivot, end);
        return pivot;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
