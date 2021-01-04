package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Largest Number",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/largest-number/"
)
public class Q179 {
    public String largestNumber(int[] nums) {
        quickSort(nums, 0, nums.length - 1);

        StringBuilder ans = new StringBuilder();
        for(int n : nums) {
            if(ans.length() != 0 || n != 0)
                ans.append(n);
        }

        return ans.length() == 0 ? "0" : ans.toString();
    }

    private void quickSort(int[] nums, int start, int end) {
        if(start >= end)
            return;

        int pivot = partition(nums, start, end);
        quickSort(nums, start, pivot - 1);
        quickSort(nums, pivot + 1, end);
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = end;
        int j = start;
        for(int i = start; i < end; i++) {
            StringBuilder ab = new StringBuilder();
            StringBuilder ba = new StringBuilder();
            ab.append(nums[i]).append(nums[pivot]);
            ba.append(nums[pivot]).append(nums[i]);
            if(ab.toString().compareTo(ba.toString()) > 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
        int temp = nums[pivot];
        nums[pivot] = nums[j];
        nums[j] = temp;

        return j;
    }
}
