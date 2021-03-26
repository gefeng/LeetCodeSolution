package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Rectangle Area",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/rectangle-area/"
)
public class Q223 {
    /*
    * calculate intersection first
    * */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int width = Math.min(C, G) - Math.max(A, E);
        int height = Math.min(D, H) - Math.max(B, F);
        int intersection = (width > 0 && height > 0) ? width * height : 0;

        return (C - A) * (D - B) + (G - E) * (H - F) - intersection;
    }
}
