package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Stack;

@Problem(
        title = "Largest Rectangle in Histogram",
        difficulty = QDifficulty.HARD,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/largest-rectangle-in-histogram/"
)
public class Q84 {
    public int largestRectangleArea(int[] heights) {
        return findMaxArea(heights, 0, heights.length - 1);
    }

    private int findMaxArea(int[] heights, int left, int right) {
        if(left > right)
            return 0;

        int minIndex = findMinimum(heights, left, right);
        int maxLeft = findMaxArea(heights, left, minIndex - 1);
        int maxRight = findMaxArea(heights, minIndex + 1, right);

        return Math.max(heights[minIndex] * (right - left + 1), Math.max(maxLeft, maxRight));
    }

    private int findMinimum(int[] heights, int left, int right) {
        int index = left;
        for(int i = left; i < right + 1; i++) {
            if(heights[i] < heights[index]) {
                index = i;
            }
        }
        return index;
    }

    public int largestRectangleAreaStack(int[] heights) {
        if(heights.length == 0)
            return 0;

        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for(int i = 0; i < heights.length; i++) {
            while(!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i - (-1) - 1 : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, w * h);
            }
            stack.push(i);
        }

        while(!stack.isEmpty()) {
            int h = heights[stack.pop()];
            int w = stack.isEmpty() ? heights.length - (-1) - 1 : heights.length - stack.peek() - 1;
            maxArea = Math.max(maxArea, w * h);
        }

        return maxArea;
    }
}
