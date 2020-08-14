package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Height Checker",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/height-checker/"
)
public class Q1051 {
    public int heightChecker(int[] heights) {
        int count = 0;
        int[] ordered = new int[heights.length];
        for(int i = 0; i < heights.length; i++) {
            ordered[i] = heights[i];
        }
        Arrays.sort(ordered);
        for(int i = 0; i < heights.length; i++) {
            if(heights[i] != ordered[i])
                count++;
        }
        return count;
    }
}
