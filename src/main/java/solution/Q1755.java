package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Closest Subsequence Sum",
        difficulty = QDifficulty.HARD,
        tag = QTag.DIVIDE_AND_CONQUER,
        url = "https://leetcode.com/problems/closest-subsequence-sum/"
)
public class Q1755 {
    /*
    * 这题思路非常有启发性，题目给定n <= 40，
    * 如果计算所有subset sum需要O(2^40)的时间复杂度 -> TLE
    * 把nums一分为二，分别计算前后两个half的subset sum，
    * 时间复杂度降到O(2^20 * 2)。
    * */
    public int minAbsDifference(int[] nums, int goal) {
        int n = nums.length;
        int mid = n / 2;
        List<Integer> ssum1 = new ArrayList<>();
        List<Integer> ssum2 = new ArrayList<>();
        subsetSum(nums, 0, mid, 0, 0, ssum1);
        subsetSum(nums, mid + 1, n - 1, 0, 0, ssum2);

        Collections.sort(ssum1);

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < ssum2.size(); i++) {
            int idx = Collections.binarySearch(ssum1, goal - ssum2.get(i));
            int f = -1;
            int c = -1;
            if(idx >= 0) { // found
                return 0;
            } else {
                // -(insertion point) - 1
                if(-idx - 1 < ssum1.size()) {
                    c = -idx - 1;
                }
                if(-idx - 1 > 0) {
                    f = -idx - 2;
                }
            }

            if(c != -1) {
                min = Math.min(min, Math.abs(ssum1.get(c) + ssum2.get(i) - goal));
            }
            if(f != -1) {
                min = Math.min(min, Math.abs(ssum1.get(f) + ssum2.get(i) - goal));
            }
        }

        return min;
    }

    private void subsetSum(int[] nums, int start, int end, int i, int sum, List<Integer> ans) {
        if(start + i > end) {
            ans.add(sum);
            return;
        }

        subsetSum(nums, start, end, i + 1, sum, ans);
        subsetSum(nums, start, end, i + 1, sum + nums[start + i], ans);
    }
}
