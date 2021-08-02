package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Garden Perimeter to Collect Enough Apples",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/minimum-garden-perimeter-to-collect-enough-apples/"
)
public class Q1954 {
    /**
     * 这题要自己推导计算总苹果数的公式。
     * 切记如果constraints很大的时候一定要考虑用BINARY SEARCH!
     *
     * Time:  O(logN)
     * Space: O(1);
     * */
    public long minimumPerimeter(long neededApples) {
        long lo = 1;
        long hi = (long)1e6;
        long res = 1;

        while(lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            long total = 4 * mid * mid * (mid + 1) + 2 * (mid + 1) * mid;
            if(total >= neededApples) {
                res = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return res * 8;
    }
}
