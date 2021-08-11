package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Detect Pattern of Length M Repeated K or More Times",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/detect-pattern-of-length-m-repeated-k-or-more-times/"
)
public class Q1566 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean containsPattern(int[] arr, int m, int k) {
        int n = arr.length;
        int cnt = 0;
        for(int i = 0, j = i + m; i < n - m && j < n; j++, i++) {
            if(arr[i] == arr[j]) {
                cnt++;
                if(cnt == (k - 1) * m) {
                    return true;
                }
            } else {
                cnt = 0;
            }
        }
        return false;
    }
}
