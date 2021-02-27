package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Container With Most Water",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/container-with-most-water/"
)
public class Q11 {
    public int maxArea(int[] height) {
        int head = 0;
        int tail = height.length - 1;
        int maxArea = 0;
        while(head <= tail) {
            int h = Math.min(height[head], height[tail]);
            int w = tail - head;
            maxArea = Math.max(maxArea, w * h);
            if(height[head] < height[tail])
                head++;
            else
                tail--;
        }
        return maxArea;
    }
}
