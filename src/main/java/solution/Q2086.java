package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Number of Buckets Required to Collect Rainwater from Houses",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/minimum-number-of-buckets-required-to-collect-rainwater-from-houses/"
)
public class Q2086 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int minimumBuckets(String street) {
        char[] s = street.toCharArray();
        int n = s.length;
        int ans = 0;

        for(int i = 0; i < n; i++) {
            if(s[i] != 'H') continue;
            if(i > 0 && s[i - 1] == '.') continue;
            if(i < n - 1 && s[i + 1] == '.') continue;
            return -1;
        }

        for(int i = 0; i < n; i++) {
            if(s[i] == 'H') {
                if(i > 0 && s[i - 1] == 'B') continue;
                if(i < n - 1 && s[i + 1] == 'B') continue;
                if(i < n - 1 && s[i + 1] == '.') {
                    s[i + 1] = 'B';
                }
                ans++;
            }
        }

        return ans;
    }
}
