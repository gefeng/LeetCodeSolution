package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;

@Problem(
        title = "Number of Distinct Islands",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/number-of-distinct-islands/"
)
public class Q694 {
    /*
        这题如果单纯记录方向，会出现如下问题
        111         111
          1          1
        RRD         RRD

        要在backtrack的时候再加一个字符来保证unique,等于记录在哪里backtrack
        其他的就是简单的dfs遍历
    */
    private static final HashMap<Character, int[]> DIR = new HashMap<>() {
        {
            put('U', new int[] { -1, 0 });
            put('R', new int[] { 0, 1 });
            put('D', new int[] { 1, 0 });
            put('L', new int[] { 0, -1 });
        }
    };
    public int numDistinctIslands(int[][] grid) {
        int count = 0;
        HashSet<String> shapes = new HashSet<>();

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] != 0) {
                    StringBuilder shapeBuilder = new StringBuilder();
                    String shape = "";
                    dfs(grid, i, j, shapeBuilder);
                    shape = shapeBuilder.toString();
                    if(!shapes.contains(shape)) {
                        shapes.add(shape);
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private void dfs(int[][] grid, int row, int col, StringBuilder shapeBuilder) {
        grid[row][col] = 0;
        for(Character key : DIR.keySet()) {
            int[] dir = DIR.get(key);
            int r = row + dir[0];
            int c = col + dir[1];
            if(r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] != 0) {
                shapeBuilder.append(key);
                dfs(grid, r, c, shapeBuilder);
                shapeBuilder.append('-');
            }
        }
    }
}
