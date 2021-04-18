package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Find K Pairs with Smallest Sums",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/find-k-pairs-with-smallest-sums/"
)
public class Q373 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        return heapSolution2(nums1, nums2, k);
    }

    /*
        O(m*n*logK)
    */
    private List<List<Integer>> heapSolution1(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<int[]> maxHeap = new PriorityQueue<>((a, b) -> (b[0] + b[1]) - (a[0] + a[1]));

        for(int i = 0; i < nums1.length; i++) {
            for(int j = 0; j < nums2.length; j++) {
                maxHeap.offer(new int[] {nums1[i], nums2[j]});
                if(maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }

        while(!maxHeap.isEmpty()) {
            int[] pair = maxHeap.poll();
            ans.add(Arrays.asList(pair[0], pair[1]));
        }

        return ans;
    }

    /*
        [1,1,2,3]
        [3,4,5,7]

        transfer to a matrix problem which row and col are sorted

          3 4 5 7
        1 4 5 6 8
        1 4
        2 5
        3 6

        O(m * logK)
    */
    private List<List<Integer>> heapSolution2(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[2] - b[2]));

        for(int i = 0; i < nums1.length; i++) {
            minHeap.offer(new int[] {i, 0, nums1[i] + nums2[0]});
        }

        while(ans.size() != k && !minHeap.isEmpty()) {
            int[] min = minHeap.poll();
            ans.add(Arrays.asList(nums1[min[0]], nums2[min[1]]));

            if(min[1] != nums2.length - 1) {
                min[1]++;
                min[2] = nums1[min[0]] + nums2[min[1]];
                minHeap.offer(min);
            }
        }

        return ans;
    }
}
