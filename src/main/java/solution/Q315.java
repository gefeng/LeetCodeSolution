package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Count of Smaller Numbers After Self",
        difficulty = QDifficulty.HARD,
        tag = QTag.DIVIDE_AND_CONQUER,
        url = "https://leetcode.com/problems/count-of-smaller-numbers-after-self/"
)
public class Q315 {
    /*
        Amazon OA
    */
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        int[] counters = new int[len];
        int[] indices = new int[len];
        for(int i = 0; i < len; i++)
            indices[i] = i;

        mergeSort(nums, indices, 0, len - 1, counters);

        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < len; i++)
            ans.add(counters[i]);
        return ans;
    }

    private void mergeSort(int[] nums, int[] indices, int start, int end, int[] counters) {
        if(start >= end)
            return;

        int mid = (start + end) / 2;
        mergeSort(nums, indices, start, mid, counters);
        mergeSort(nums, indices, mid + 1, end, counters);
        merge(nums, indices, start, mid, end, counters);
    }

    private void merge(int[] nums, int[] indices, int start, int mid, int end, int[] counters) {
        int rCount = 0;
        int i = start;
        int j = mid + 1;
        int k = 0;
        int[] m = new int[end - start + 1];

        while(i <= mid || j <= end) {
            if(i > mid)
                m[k++] = indices[j++];
            else if(j > end) {
                m[k++] = indices[i++];
                counters[indices[i - 1]] += rCount;
            } else {
                if(nums[indices[i]] <= nums[indices[j]]) {
                    m[k++] = indices[i++];
                    counters[indices[i - 1]] += rCount;
                } else {
                    m[k++] = indices[j++];
                    rCount++;
                }
            }
        }

        for(i = start; i <= end; i++)
            indices[i] = m[i - start];
    }
}
