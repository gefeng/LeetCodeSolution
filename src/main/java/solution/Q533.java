package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Lonely Pixel II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/lonely-pixel-ii/"
)
public class Q533 {
    /**
     * Time:  O(M ^ 2 * N)
     * Space: O(M * N)
     * */
    public int findBlackPixel(char[][] picture, int target) {
        int m = picture.length;
        int n = picture[0].length;
        int res = 0;
        int[] cntRows = new int[m];
        int[] cntCols = new int[n];
        String[] rows = new String[m];

        for(int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < n; j++) {
                if(picture[i][j] == 'B') {
                    cntRows[i]++;
                    cntCols[j]++;
                }

                sb.append(picture[i][j]);
            }
            rows[i] = sb.toString();
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(picture[i][j] == 'W' || (cntRows[i] != target || cntCols[j] != target)) {
                    continue;
                }

                boolean same = true;
                for(int k = 0; k < m; k++) {
                    if(k == i || picture[k][j] == 'W') {
                        continue;
                    }

                    if(!rows[k].equals(rows[i])) {
                        same = false;
                        break;
                    }
                }

                if(same) {
                    res++;
                }
            }
        }

        return res;
    }
}
