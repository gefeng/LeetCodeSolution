package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Single Number II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/single-number-ii/"
)
public class Q137 {
    public int singleNumber(int[] nums) {
        int ans = 0;

        for(int i = 0; i < 32; i++) {
            int cnt = 0;
            for(int num : nums) {
                if((num & (1 << i)) != 0) {
                    cnt++;
                }
            }
            if(cnt % 3 != 0) {
                ans |= (1 << i);
            }
        }

        return ans;
    }
}
