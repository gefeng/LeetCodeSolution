package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;
import java.util.Random;

@Problem(
        title = "Kth Largest Element in an Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DIVIDE_AND_CONQUER,
        url = "https://leetcode.com/problems/kth-largest-element-in-an-array/"
)
public class Q215 {
    public int findKthLargest(int[] nums, int k) {
        return HeapSolution(nums, k);
    }

    /**
     * Heap solution
     */
    private int HeapSolution(int[] nums, int k) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>((a, b) -> (a - b));
        for(int n : nums) {
            minQueue.add(n);
            if(minQueue.size() > k)
                minQueue.poll();
        }

        return minQueue.poll();
    }

    /**
     * Quick select solution
     */
    Random rand = new Random();
    private int QuickSelectSolution(int[] nums, int k) {
        return selection(nums, 0, nums.length - 1, k);
    }
    private int partition(int[] nums, int lo, int hi) {
        int pIndex = lo + rand.nextInt(hi - lo + 1);
        int pivot = nums[pIndex];

        nums[pIndex] = nums[hi];
        nums[hi] = pivot;

        int j = lo;
        for(int i = lo; i < hi; i++) {
            if(nums[i] > pivot) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }

        nums[hi] = nums[j];
        nums[j] = pivot;
        return j;
    }

    private int selection(int[] nums, int lo, int hi, int k) {
        int pivot = partition(nums, lo, hi);
        if(k == pivot + 1)
            return nums[pivot];
        else if(k < pivot + 1)
            return selection(nums, lo, pivot - 1, k);
        else
            return selection(nums, pivot + 1, hi, k);

    }
}
