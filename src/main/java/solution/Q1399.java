package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Largest Group",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/count-largest-group/"
)
public class Q1399 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int countLargestGroup(int n) {
        int[] cnt = new int[50];
        int ans = 0;
        int maxLen = 0;
        for(int i = 1; i <= n; i++) {
            int num = i;
            int sum = 0;
            while(num != 0) {
                sum += num % 10;
                num /= 10;
            }

            cnt[sum]++;
            maxLen = Math.max(maxLen, cnt[sum]);
        }

        for(int i = 0; i < 50; i++) {
            if(cnt[i] == maxLen) {
                ans++;
            }
        }

        return ans;
    }
}
