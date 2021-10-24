package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Next Greater Numerically Balanced Number",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/next-greater-numerically-balanced-number/"
)
public class Q2048 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int nextBeautifulNumber(int n) {
        int ans = 0;
        while(true) {
            n += 1;
            int[] cnt = new int[10];
            int x = n;
            while(x > 0) {
                cnt[x % 10]++;
                x /= 10;
            }

            boolean isOk = true;

            for(int i = 1; i < 10; i++) {
                if(cnt[i] > 0 && cnt[i] != i) {
                    isOk = false;
                    break;
                }
            }
            if(isOk && cnt[0] == 0) {
                ans = n;
                break;
            }
        }

        return ans;
    }
}
