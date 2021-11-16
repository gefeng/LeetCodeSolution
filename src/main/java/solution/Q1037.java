package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Valid Boomerang",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/valid-boomerang/"
)
public class Q1037 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public boolean isBoomerang(int[][] points) {
        for(int i = 0; i < 3; i++) {
            for(int j = i + 1; j < 3; j++) {
                if(points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    return false;
                }
            }
        }

        int[] v1 = new int[] {points[1][0] - points[0][0], points[1][1] - points[0][1]};
        int[] v2 = new int[] {points[2][0] - points[0][0], points[2][1] - points[0][1]};

        return v1[0] * v2[1] - v1[1] * v2[0] != 0;
    }
}
