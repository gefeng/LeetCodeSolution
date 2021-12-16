package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Lemonade Change",
        difficulty = QDifficulty.EASY,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/lemonade-change/"
)
public class Q860 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean lemonadeChange(int[] bills) {
        int[] cnt = new int[21];

        for(int b : bills) {
            cnt[b]++;

            int c = b - 5;
            if(c == 15) {
                if(cnt[10] > 0 && cnt[5] > 0) {
                    cnt[10]--;
                    cnt[5]--;
                } else if(cnt[5] >= 3) {
                    cnt[5] -= 3;
                } else {
                    return false;
                }
            } else if(c == 5) {
                if(cnt[5] > 0) cnt[5]--;
                else return false;
            }
        }

        return true;
    }
}
