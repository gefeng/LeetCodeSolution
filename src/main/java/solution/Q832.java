package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Flipping an Image",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/flipping-an-image/"
)
public class Q832 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(1)
     * */
    public int[][] flipAndInvertImage(int[][] image) {
        int n = image.length;
        for(int i = 0; i < n; i++) {
            for(int l = 0, r = n - 1; l < r; l++, r--) {
                int d = image[i][l];
                image[i][l] = image[i][r];
                image[i][r] = d;
            }

            for(int j = 0; j < n; j++) {
                image[i][j] ^= 1;
            }
        }

        return image;
    }
}
