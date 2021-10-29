package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Find Smallest Common Element in All Rows",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/find-smallest-common-element-in-all-rows/"
)
public class Q1198 {
    /**
     * Time:  O(N * M * logN)
     * Space: O(1)
     * */
    public int smallestCommonElement(int[][] mat) {
        int m = mat.length;

        for(int x : mat[0]) {
            boolean ok = true;
            for(int i = 1; i < m; i++) {
                if(Arrays.binarySearch(mat[i], x) < 0) {
                    ok = false;
                    break;
                }
            }
            if(ok) {
                return x;
            }
        }

        return -1;
    }
}
