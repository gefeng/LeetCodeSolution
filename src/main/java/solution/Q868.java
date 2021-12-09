package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Binary Gap",
        difficulty = QDifficulty.EASY,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/binary-gap/"
)
public class Q868 {
    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    public int binaryGap(int n) {
        int ans = 0;
        int pre = -1;
        int cur = 0;
        while(n != 0) {
            int b = n % 2;
            if(b == 1) {
                if(pre != -1) {
                    ans = Math.max(ans, cur - pre);
                }
                pre = cur;
            }
            cur++;
            n /= 2;
        }

        return ans;
    }
}
