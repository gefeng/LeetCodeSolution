package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Sub-arrays With Odd Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/"
)
public class Q1524 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int numOfSubarrays(int[] arr) {
        int n = arr.length;
        long res = 0;
        long sum = 0;
        int cntOdd = 0;
        int cntEven = 1;

        for(int i = 0; i < n; i++) {
            sum += arr[i];

            if(sum % 2 == 0) {
                cntEven++;
                res = (res + cntOdd) % MOD;
            } else {
                cntOdd++;
                res = (res + cntEven) % MOD;
            }
        }
        return (int)res;
    }
}
