package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Intersection of Three Sorted Arrays",
        difficulty = QDifficulty.EASY,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/intersection-of-three-sorted-arrays/"
)
public class Q1213 {
    /**
     * Time:  O(N * (logM + logK))
     * Space: O(min(M, N, K))
     * */
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> ans = new ArrayList<>();

        for(int x : arr1) {
            if(Arrays.binarySearch(arr2, x) >= 0 && Arrays.binarySearch(arr3, x) >= 0) {
                ans.add(x);
            }
        }

        return ans;
    }
}
