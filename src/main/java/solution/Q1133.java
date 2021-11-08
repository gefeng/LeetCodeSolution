package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Largest Unique Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/largest-unique-number/"
)
public class Q1133 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int largestUniqueNumber(int[] nums) {
        int[] cnt = new int[1001];

        for(int x : nums) {
            cnt[x] += 1;
        }

        for(int i = 1000; i >= 0; i--) {
            if(cnt[i] == 1) {
                return i;
            }
        }
        return -1;
    }
}
