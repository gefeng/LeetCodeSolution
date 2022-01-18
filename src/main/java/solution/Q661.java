package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Image Smoother",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/image-smoother/"
)
public class Q661 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    int[][] directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    public int[][] imageSmoother(int[][] img) {
        int m = img.length;
        int n = img[0].length;
        int[][] ans = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int sum = img[i][j];
                int cnt = 1;
                for(int[] dir : directions) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];
                    if(ni >= 0 && nj >= 0 && ni < m && nj < n) {
                        sum += img[ni][nj];
                        cnt += 1;
                    }
                }
                ans[i][j] = sum / cnt;
            }
        }

        return ans;
    }
}
