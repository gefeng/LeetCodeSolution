package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Count Elements With Strictly Smaller and Greater Elements",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/count-elements-with-strictly-smaller-and-greater-elements/"
)
public class Q2148 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int countElements(int[] nums) {
        int ans = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int x : nums) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }

        for(int x : nums) {
            if(x > min && x < max) ans++;
        }

        return ans;
    }
}
