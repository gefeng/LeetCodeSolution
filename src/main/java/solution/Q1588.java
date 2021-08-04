package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum of All Odd Length Subarrays",
        difficulty = QDifficulty.EASY,
        tag = QTag.PREFIX_SUM,
        url = "https://leetcode.com/problems/sum-of-all-odd-length-subarrays/"
)
public class Q1588 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(1)
     * */
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int res = 0;

        for(int len = 1; len <= n; len += 2) {
            int sum = 0;
            for(int l = 0, r = 0; r < n; r++) {
                sum += arr[r];

                if(r - l + 1 > len) {
                    sum -= arr[l++];
                }

                if(r - l + 1 == len) {
                    res += sum;
                }
            }
        }

        return res;
    }
}
