package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Beautiful Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DIVIDE_AND_CONQUER,
        url = "https://leetcode.com/problems/beautiful-array/"
)
public class Q932 {
    /**
     * If we can split array into two parts [odd][even] then if i,j fall
     * into different part, we can guarantee A[k] != A[i] + A[j]
     *
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int[] beautifulArray(int n) {
        int[] res = new int[n];
        int i = 0;

        if(n == 1) {
            res[0] = 1;
            return res;
        }

        for(int x : beautifulArray((n + 1) / 2)) {
            res[i++] = 2 * x - 1;
        }
        for(int x : beautifulArray(n / 2)) {
            res[i++] = 2 * x;
        }

        return res;
    }
}
