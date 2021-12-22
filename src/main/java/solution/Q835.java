package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Problem(
        title = "Image Overlap",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/image-overlap/"
)
public class Q835 {
    /**
     * Time:  O(N ^ 4)
     * Space: O(1)
     * */
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int ans = 0;
        //-n + 1 - n - 1
        for(int dx = -n + 1; dx <= n - 1; dx++) {
            for(int dy = -n + 1; dy <= n - 1; dy++) {
                int area = 0;
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < n; j++) {
                        if(i + dx >= 0 && j + dy >= 0 && i + dx < n && j + dy < n && img1[i][j] == 1 && img2[i + dx][j + dy] == 1) {
                            area++;
                        }
                    }
                }
                ans = Math.max(ans, area);
            }
        }

        return ans;
    }
}
