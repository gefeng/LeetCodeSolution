package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Flood Fill",
        difficulty = QDifficulty.EASY,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/flood-fill/"
)
public class Q733 {
    private void shade(int[][] image, int row, int col, int targetColor, int newColor) {
        /*if(row < 0 || col < 0 || row > image.length - 1 || col > image[0].length - 1 || image[row][col] != targetColor)
            return;*/

        image[row][col] = newColor;

        if(row > 0 && image[row - 1][col] == targetColor)
            shade(image, row - 1, col, targetColor, newColor);
        if(row < image.length - 1 && image[row + 1][col] == targetColor)
            shade(image, row + 1, col, targetColor, newColor);
        if(col > 0 && image[row][col - 1] == targetColor)
            shade(image, row, col - 1, targetColor, newColor);
        if(col < image[0].length - 1 && image[row][col + 1] == targetColor)
            shade(image, row, col + 1, targetColor, newColor);
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] == newColor)
            return image;
        shade(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
}
