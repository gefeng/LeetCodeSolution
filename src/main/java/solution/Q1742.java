package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Number of Balls in a Box",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/maximum-number-of-balls-in-a-box/"
)
public class Q1742 {
    public int countBalls(int lowLimit, int highLimit) {
        int[] cnt = new int[46];
        for(int i = lowLimit; i <= highLimit; i++) {
            cnt[getSum(i)]++;
        }

        int ans = 0;
        for(int i = 0; i < cnt.length; i++) {
            ans = Math.max(ans, cnt[i]);
        }
        return ans;
    }

    private int getSum(int n) {
        int sum = 0;
        while(n != 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}
