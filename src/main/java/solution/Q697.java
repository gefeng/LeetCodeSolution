package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Degree of an Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/degree-of-an-array/"
)
public class Q697 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int findShortestSubArray(int[] nums) {
        int n = nums.length;
        int d = 0;
        int ans = n;
        int[] cnt = new int[50000];
        int[] first = new int[50000];
        int[] last = new int[50000];

        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for(int i = 0; i < n; i++) {
            cnt[nums[i]]++;
            d = Math.max(d,  cnt[nums[i]]);

            if(first[nums[i]] == -1) {
                first[nums[i]] = i;
            }
            last[nums[i]] = i;
        }

        for(int i = 0; i < 50000; i++) {
            if(cnt[i] == d) {
                ans = Math.min(ans, last[i] - first[i] + 1);
            }
        }

        return ans;
    }
}
