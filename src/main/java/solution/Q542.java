package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "01 Matrix",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/01-matrix/"
)
public class Q542 {
    private int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public int[][] updateMatrix(int[][] matrix) {
        Queue<Integer> queue = new LinkedList<>();
        int w = matrix[0].length;
        int h = matrix.length;

        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(matrix[i][j] == 0)
                    queue.add(i * w + j);
                else
                    matrix[i][j] = Integer.MAX_VALUE;
            }
        }

        int dist = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int index = queue.poll();
                int row = index / w;
                int col = index % w;
                for(int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    if(newRow >= 0 && newCol >= 0 && newRow < h && newCol < w) {
                        if(matrix[newRow][newCol] > dist + 1) {
                            matrix[newRow][newCol] = dist + 1;
                            queue.offer(newRow * w + newCol);
                        }
                    }
                }
            }
            dist++;
        }

        return matrix;
    }
}
