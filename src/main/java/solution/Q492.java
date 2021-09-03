package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Construct the Rectangle",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/construct-the-rectangle/"
)
public class Q492 {
    /**
     * Time:  O(sqrt(N))
     * Space: O(1)
     * */
    public int[] constructRectangle(int area) {
        int w = (int)Math.sqrt(area);
        while(area % w != 0) {
            w--;
        }
        return new int[] {area / w, w};
    }
}
