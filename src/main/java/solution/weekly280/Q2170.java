package solution.weekly280;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Operations to Make the Array Alternating",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/weekly-contest-280/problems/minimum-operations-to-make-the-array-alternating/"
)
public class Q2170 {
    /**
     * Time:  O(N + max(nums))
     * Space: O(max(nums))
     * */
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        int[] cnt1 = new int[100001];
        int[] cnt2 = new int[100001];
        for(int i = 0; i < n; i += 2) {
            cnt1[nums[i]]++;
        }

        for(int i = 1; i < n; i += 2) {
            cnt2[nums[i]]++;
        }

        int[] eve = maxFreq(cnt1);
        int[] odd = maxFreq(cnt2);

        if(eve[1] != odd[1]) return n - (eve[0] + odd[0]);

        return Math.min(n - (eve[0] + odd[2]), n - (eve[2] + odd[0]));
    }

    private int[] maxFreq(int[] cnt) {
        int m1 = 0, x1 = -1;
        int m2 = 0, x2 = -1;
        for(int i = 0; i < cnt.length; i++) {
            if(cnt[i] > 0 && cnt[i] > m1) {
                m2 = m1;
                x2 = x1;
                m1 = cnt[i];
                x1 = i;
            } else if(cnt[i] > m2) {
                m2 = cnt[i];
                x2 = i;
            }
        }
        return new int[] {m1, x1, m2, x2};
    }
}
