package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sort Even and Odd Indices Independently",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/sort-even-and-odd-indices-independently/"
)
public class Q2164 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] sortEvenOdd(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int[] cntOdd = new int[101];
        int[] cntEve = new int[101];

        for(int i = 0; i < n; i += 2) {
            cntEve[nums[i]]++;
        }

        for(int i = 1; i < n; i += 2) {
            cntOdd[nums[i]]++;
        }

        for(int i = 0, j = 0; i <= 100; i++) {
            while(cntEve[i] != 0) {
                ans[j] = i;
                cntEve[i]--;
                j += 2;
            }
        }

        for(int i = 100, j = 1; i >= 0; i--) {
            while(cntOdd[i] != 0) {
                ans[j] = i;
                cntOdd[i]--;
                j += 2;
            }
        }

        return ans;
    }
}
