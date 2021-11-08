package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum of Absolute Value Expression",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/maximum-of-absolute-value-expression/"
)
public class Q1131 {
    /**
     * abs(a) + abs(b) = max(a + b + c, a - b + c, -a - b + c, -a + b + c)
     *
     * abs(a) + abs(b) + j - i
     *
     * a + b + j - i    a[i] - a[j] + b[i] - b[j]
     * a - b + j - i    a[i] - a[j] - b[i] + b[j]
     * -a + b + j - i   -a[i] + a[j] + b[i] - b[j]
     * -a - b + j - i   -a[i] + a[j] - b[i] + b[j]
     *
     * a[i] + b[i] - i  + -(a[j] + b[j]) + j
     * a[i] - b[i] - i + -(a[j] - b[j]) + j
     * -a[i] + b[i]- i + a[j] - b[j] + j
     * -a[i] - b[i]- i + a[j] + b[j] + j
     *
     * ime:  O(N)
     * Space: O(1)
     * */
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int ans = Integer.MIN_VALUE;

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int max4 = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++) {
            max1 = Math.max(max1, arr1[i] + arr2[i] - i);
            max2 = Math.max(max2, arr1[i] - arr2[i] - i);
            max3 = Math.max(max3, -arr1[i] + arr2[i] - i);
            max4 = Math.max(max4, -arr1[i] - arr2[i] - i);
            ans = Math.max(ans, max1 - arr1[i] - arr2[i] + i);
            ans = Math.max(ans, max2 - arr1[i] + arr2[i] + i);
            ans = Math.max(ans, max3 + arr1[i] - arr2[i] + i);
            ans = Math.max(ans, max4 + arr1[i] + arr2[i] + i);
        }

        return ans;
    }
}
