package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Minimum Absolute Difference",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/minimum-absolute-difference/"
)
public class Q1200 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = arr.length;

        Arrays.sort(arr);

        int min = Integer.MAX_VALUE;
        for(int i = 1; i < n; i++) {
            min = Math.min(min, arr[i] - arr[i - 1]);
        }

        for(int i = 1; i < n; i++) {
            if(arr[i] - arr[i - 1] == min) {
                ans.add(Arrays.asList(arr[i - 1], arr[i]));
            }
        }

        return ans;
    }
}
