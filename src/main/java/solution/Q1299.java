package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Replace Elements with Greatest Element on Right Side",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/"
)
public class Q1299 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int[] replaceElements(int[] arr) {
        int max = -1;
        for(int i = arr.length - 1; i >= 0; i--) {
            int temp = arr[i];
            arr[i] = max;
            max = Math.max(max, temp);
        }
        return arr;
    }
}
