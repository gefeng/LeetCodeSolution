package solution.biweekly74;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.stream.IntStream;

@Problem(
        title = "Divide Array Into Equal Pairs",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/contest/biweekly-contest-74/problems/divide-array-into-equal-pairs/"
)
public class Q2206 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean divideArray(int[] nums) {
        int[] cnt = new int[501];

        for(int x : nums) {
            cnt[x]++;
        }

        for(int i = 1; i <= 500; i++) {
            if(cnt[i] % 2 == 1) {
                return false;
            }
        }

        return true;
    }
}
