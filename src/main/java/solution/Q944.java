package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Delete Columns to Make Sorted",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/delete-columns-to-make-sorted/"
)
public class Q944 {
    /**
     * Time:  O(N * L)
     * Space: O(1)
     * */
    public int minDeletionSize(String[] strs) {
        int ans = 0;
        int n = strs.length;
        int len = strs[0].length();

        for(int i = 0; i < len; i++) {
            boolean del = false;
            for(int j = 1; j < n; j++) {
                if(strs[j].charAt(i) < strs[j - 1].charAt(i)) {
                    del = true;
                    break;
                }
            }
            if(del) {
                ans++;
            }
        }

        return ans;
    }
}
