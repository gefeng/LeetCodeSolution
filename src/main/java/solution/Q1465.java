package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts",
        difficulty =  QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/"
)
public class Q1465 {
    private static final int FACTOR = 1000000007;
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int maxW = 1;
        int maxH = 1;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        long maxArea = (long)findMaxCut(horizontalCuts, h) * findMaxCut(verticalCuts, w);
        return (int)(maxArea % FACTOR);
    }

    private long findMaxCut(int[] cuts, int len) {
        if(cuts.length == 0)
            return len;

        int max = 0;

        // be careful with 1 cut.
        max = Math.max(max, cuts[0] - 0);
        max = Math.max(max, len - cuts[cuts.length - 1]);
        for(int i = 1; i < cuts.length; i++) {
            max = Math.max(max, cuts[i] - cuts[i - 1]);
        }

        return max;
    }
}
