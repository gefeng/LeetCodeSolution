package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find the Distance Value Between Two Arrays",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/find-the-distance-value-between-two-arrays/"
)
public class Q1385 {
    /**
     * Time:  O(M * N)
     * Space: O(1)
     * */
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int ans = 0;
        int m = arr1.length;
        int n = arr2.length;

        for(int i = 0; i < m; i++) {
            boolean isOk = true;
            for(int j = 0; j < n; j++) {
                if(Math.abs(arr1[i] - arr2[j]) <= d) {
                    isOk = false;
                    break;
                }
            }

            if(isOk) {
                ans++;
            }
        }

        return ans;
    }
}
