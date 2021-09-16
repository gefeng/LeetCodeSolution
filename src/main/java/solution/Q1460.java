package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Make Two Arrays Equal by Reversing Sub-arrays",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/make-two-arrays-equal-by-reversing-sub-arrays/"
)
public class Q1460 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean canBeEqual(int[] target, int[] arr) {
        int n = arr.length;
        int[] a = new int[1001];
        int[] b = new int[1001];

        for(int i = 0; i < n; i++) {
            a[target[i]]++;
            b[arr[i]]++;
        }

        for(int i = 0; i < 1001; i++) {
            if(a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }
}
