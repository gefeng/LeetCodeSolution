package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check Array Formation Through Concatenation",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/check-array-formation-through-concatenation/"
)
public class Q1640 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean canFormArray(int[] arr, int[][] pieces) {
        int[] idxMap = new int[101];
        for(int i = 0; i < arr.length; i++) {
            idxMap[arr[i]] = i;
        }

        for(int[] p : pieces) {
            int i = 0;
            int j = idxMap[p[0]];

            while(i < p.length && j < arr.length) {
                if(p[i] != arr[j]) {
                    return false;
                }
                i++;
                j++;
            }

            if(i != p.length) {
                return false;
            }
        }

        return true;
    }
}
