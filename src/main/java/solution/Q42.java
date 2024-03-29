package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Stack;

@Problem(
        title = "Trapping Rain Water",
        difficulty = QDifficulty.HARD,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/trapping-rain-water/"
)
public class Q42 {
    public int trap(int[] height) {
        return stackSolution(height);
    }

    private int stackSolution(int[] height) {
        int n = height.length;
        if(n < 3)
            return 0;

        int waterArea = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++) {
            int h = height[i];
            while(!stack.isEmpty() && h > height[stack.peek()]) {
                int prevHeight = height[stack.pop()];
                if(!stack.isEmpty())
                    waterArea += ((Math.min(height[stack.peek()], h) - prevHeight) * (i - stack.peek() - 1));
            }
            stack.push(h);
        }
        return  waterArea;
    }

    private int twoPointersSolution(int[] height) {
        int waterArea = 0;
        int l = 0;
        int r = height.length - 1;
        int maxL = 0;
        int maxR = 0;
        while(l < r) {
            if(height[l] <= height[r]) {
                if(height[l] > maxL)
                    maxL = height[l];
                else
                    waterArea += (maxL - height[l]);
                l++;
            } else {
                if(height[r] > maxR)
                    maxR = height[r];
                else
                    waterArea += (maxR - height[r]);
                r--;
            }
        }
        return waterArea;
    }

    private int dpSol(int[] height) {
        if(height.length == 0) {
            return 0;
        }
        int n = height.length;
        int[] dpL = new int[n];
        int[] dpR = new int[n];

        int max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, height[i]);
            dpL[i] = max;
        }

        max = 0;
        for(int i = n - 1; i >= 0; i--) {
            max = Math.max(max, height[i]);
            dpR[i] = max;
        }

        int total = 0;
        for(int i = 0; i < n; i++) {
            int area = Math.min(dpL[i], dpR[i]) - height[i];
            total += area;
        }

        return total;
    }
}
