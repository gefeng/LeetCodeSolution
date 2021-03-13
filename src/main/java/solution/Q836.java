package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Rectangle Overlap",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH;
        url = "https://leetcode.com/problems/rectangle-overlap/"
)
public class Q836 {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int[] px1 = new int[] { rec1[0], rec1[2] };
        int[] px2 = new int[] { rec2[0], rec2[2] };
        int[] py1 = new int[] { rec1[1], rec1[3] };
        int[] py2 = new int[] { rec2[1], rec2[3] };

        return Math.min(px1[1], px2[1]) > Math.max(px1[0], px2[0]) &&
                Math.min(py1[1], py2[1]) > Math.max(py1[0], py2[0]);
    }
}
