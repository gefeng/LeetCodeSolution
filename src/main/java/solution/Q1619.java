package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Mean of Array After Removing Some Elements",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/mean-of-array-after-removing-some-elements/"
)
public class Q1619 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public double trimMean(int[] arr) {
        int n = arr.length;
        int trim = (int)(n * 0.05);

        Arrays.sort(arr);

        int sum = 0;
        for(int i = trim; i < n - trim; i++) {
            sum += arr[i];
        }

        return ((double)sum / (n - 2 * trim));
    }
}
