package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Minimum Cost Tree From Leaf Values",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/"
)
public class Q1130 {
    public int mctFromLeafValues(int[] arr) {
        return stackSolution(arr);
    }

    /**
     * Time:  O(N ^ 3)
     * Space: O(N ^ 2)
     * */
    /*
        state:
            dp[i][j] means min sum of subarray s[i, j]
        transition:
            dp[i][j] = min(max(s[i]...s[k]) * max(s[k+1]..,s[j]) + dp[i][k] + dp[k+1][j]) for k : [i, j)
    */
    private int bottomUpDp(int[] arr) {
        int n = arr.length;

        int[][] dp = new int[n][n];
        int[][] max = new int[n][n];

        for(int i = 0; i < n; i++) {
            max[i][i] = arr[i];
            for(int j = i + 1; j < n; j++) {
                max[i][j] = arr[j] > max[i][j - 1] ? arr[j] : max[i][j - 1];
            }
        }

        for(int i = n - 2; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                int minSum = Integer.MAX_VALUE;
                for(int k = i; k < j; k++) {
                    minSum = Math.min(minSum, max[i][k] * max[k + 1][j] + dp[i][k] + dp[k+1][j]);
                }

                dp[i][j] = minSum;
            }
        }

        return dp[0][n - 1];
    }

    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private int stackSolution(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(Integer.MAX_VALUE);

        int sum = 0;
        for(int i = 0; i < arr.length; i++) {
            while(!stack.isEmpty() && stack.peek() <= arr[i]) {
                int mid = stack.pop();
                sum += mid * Math.min(stack.peek(), arr[i]);
            }
            stack.push(arr[i]);
        }

        while(stack.size() > 2) {
            sum += stack.pop() * stack.peek();
        }

        return sum;
    }
}
