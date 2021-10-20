package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Remove Interval",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/remove-interval/"
)
public class Q1272 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> ans = new ArrayList<>();

        int n = intervals.length;
        int l = toBeRemoved[0];
        int r = toBeRemoved[1];
        for(int[] itv : intervals) {
            if(itv[1] <= l || itv[0] >= r) {
                ans.add(Arrays.asList(itv[0], itv[1]));
            } else {
                if(itv[0] < l) {
                    ans.add(Arrays.asList(itv[0], l));
                }
                if(itv[1] > r) {
                    ans.add(Arrays.asList(r, itv[1]));
                }
            }
        }

        return ans;
    }
}
