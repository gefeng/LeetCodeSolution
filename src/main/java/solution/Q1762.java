package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Buildings With an Ocean View",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/buildings-with-an-ocean-view/"
)
public class Q1762 {
    public int[] findBuildings(int[] heights) {
        int n = heights.length;
        List<Integer> ovb = new ArrayList<>();
        int max = 0;

        for(int i = n - 1; i >= 0; i--) {
            if(heights[i] > max) {
                max = heights[i];
                ovb.add(i);
            }
        }

        int len = ovb.size();
        int[] ans = new int[len];
        for(int i = 0; i < len; i++) {
            ans[i] = ovb.get(len - i - 1);
        }

        return ans;
    }
}
