package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Sort an Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/sort-an-array/"
)
public class Q912 {
    public int[] sortArray(int[] nums) {
        return quickSort(nums);
    }

    /**
     * Merge Sort
     */
    private int[] mergeSortIterative(int[] nums) {
        if(nums.length < 2)
            return nums;

        for(int size = 1; size < nums.length; size *= 2) {
            for(int i = 0; i <= nums.length - size; i += (2 * size)) {
                int end = Math.min(i + 2 * size - 1, nums.length - 1);
                int mid = i + size - 1;
                merge(nums, i, mid, end);
            }
        }
        return nums;
    }

    private int[] mergeSort(int[] nums) {
        if(nums.length <= 1)
            return nums;
        int pivot = nums.length / 2;
        int[] left = mergeSort(Arrays.copyOfRange(nums, 0, pivot));
        int[] right = mergeSort(Arrays.copyOfRange(nums, pivot, nums.length));
        int[] sorted = merge(left, right);
        return sorted;
    }

    private int[] merge(int[] a1, int[] a2) {
        if(a1.length == 0)
            return a2;
        if(a2.length == 0)
            return a1;

        int[] sorted = new int[a1.length + a2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < a1.length || j < a2.length) {
            if (i == a1.length)
                sorted[k++] = a2[j++];
            else if (j == a2.length)
                sorted[k++] = a1[i++];
            else {
                if (a1[i] < a2[j])
                    sorted[k++] = a1[i++];
                else
                    sorted[k++] = a2[j++];
            }
        }
        return sorted;
    }

    private void merge(int[] nums, int start, int mid, int end) {
        int[] sorted = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        while(i <= mid || j <= end) {
            if(i > mid)
                sorted[k++] = nums[j++];
            else if(j > end)
                sorted[k++] = nums[i++];
            else
                sorted[k++] = nums[i] < nums[j] ? nums[i++] : nums[j++];
        }

        for(i = 0; i < sorted.length; i++) {
            nums[start + i] = sorted[i];
        }
    }

    /**
     * Quick Sort
     */
    private int[] quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int lo, int hi) {
        if(lo >= hi)
            return;

        int pivot = partition(nums, lo, hi);
        quickSort(nums, lo, pivot - 1);
        quickSort(nums, pivot + 1, hi);
    }

    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[hi];
        int j = lo;
        for(int i = lo; i < hi; i++) {
            if(nums[i] < pivot) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }

        nums[hi] = nums[j];
        nums[j] = pivot;

        return j;
    }
}
