package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Smallest Value of the Rearranged Number",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/smallest-value-of-the-rearranged-number/"
)
public class Q2165 {
    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    public long smallestNumber(long num) {
        int[] cnt = new int[10];
        long x = Math.abs(num);
        long ans = 0;
        while(x != 0) {
            int d = (int)(x % 10);
            cnt[d]++;
            x /= 10;
        }

        if(num > 0) {
            for(int i = 1; i < 10; i++) {
                if(cnt[i] != 0) {
                    ans = i;
                    cnt[i]--;
                    break;
                }
            }
            for(int i = 0; i < 10; i++) {
                while(cnt[i] != 0) {
                    ans = ans * 10 + i;
                    cnt[i]--;
                }
            }
        } else {
            for(int i = 9; i >= 0; i--) {
                while(cnt[i] != 0) {
                    ans = ans * 10 + i;
                    cnt[i]--;
                }
            }
            ans = ans * -1;
        }

        return ans;
    }
}
