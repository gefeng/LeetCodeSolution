package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "1-bit and 2-bit Characters",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/1-bit-and-2-bit-characters/"
)
public class Q717 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;

        int i = 0;
        for(; i < n - 1; i++) {
            if(bits[i] == 1) {
                i++;
            }
        }

        return i == n - 1;
    }
}
