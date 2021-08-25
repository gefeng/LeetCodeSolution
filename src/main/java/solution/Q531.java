package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Lonely Pixel I",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/lonely-pixel-i/"
)
public class Q531 {
    /**
     * Time:  O(M * N)
     * Space: O(M + N)
     * */
    public int findLonelyPixel(char[][] picture) {
        int m = picture.length;
        int n = picture[0].length;
        int res = 0;
        int[] rows = new int[m];
        int[] cols = new int[n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(picture[i][j] == 'B') {
                    rows[i]++;
                    cols[j]++;
                }
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(picture[i][j] == 'B' && rows[i] == 1 && cols[j] == 1) {
                    res++;
                }
            }
        }

        return res;
    }
}
