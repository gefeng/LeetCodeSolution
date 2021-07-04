package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

@Problem(
        title = "Maximal Rectangle",
        difficulty = QDifficulty.HARD,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/maximal-rectangle/"
)
public class Q85 {
    public int maximalRectangle(char[][] matrix) {
        //return checkAllSubmatrix(matrix);
        return histogramSolution(matrix);
    }

    // O(N^3)
    private int checkAllSubmatrix(char[][] matrix) {
        if(matrix.length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int ans = 0;

        int[] rows = new int[m];
        for(int l = 0; l < n; l++) {
            Arrays.fill(rows, 0);
            for(int r = l; r < n; r++) {
                int sum = 0;
                for(int i = 0; i < m; i++) {
                    rows[i] += matrix[i][r] - '0';
                    if(rows[i] != r - l + 1) {
                        rows[i] = 0;
                    }

                    sum = rows[i] == 0 ? 0 : sum + rows[i];
                    ans = Math.max(ans, sum);
                }
            }
        }

        return ans;
    }

    // O(n^2)
    private int histogramSolution(char[][] matrix) {
        if(matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = 0;

        int[] h = new int[n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                h[j] = matrix[i][j] == '0' ? 0 : h[j] + 1;
            }

            ans = Math.max(ans, getMaxArea(h));
        }

        return ans;
    }

    /*
    * find max area of histogram using monotonic stack (increasing stack + -1 sentinel)
    * */
    private int getMaxArea(int[] h) {
        int n = h.length;
        int max = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        for(int i = 0; i < n; i++) {
            while(stack.peek() != -1 && h[stack.peek()] >= h[i]) {
                max = Math.max(max, h[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }

        while(stack.size() > 1) {
            max = Math.max(max, h[stack.pop()] * (n - stack.peek() - 1));
        }

        return max;
    }
}
