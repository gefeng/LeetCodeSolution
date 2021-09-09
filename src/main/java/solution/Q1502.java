package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Can Make Arithmetic Progression From Sequence",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/"
)
public class Q1502 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public boolean canMakeArithmeticProgression(int[] arr) {
        int n = arr.length;

        Arrays.sort(arr);

        int pre = -1;
        for(int i = 1; i < n; i++) {
            int cur = arr[i] - arr[i - 1];

            if(pre != -1) {
                if(cur != pre) {
                    return false;
                }
            }

            pre = cur;
        }

        return true;
    }
}
