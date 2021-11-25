package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Delete Columns to Make Sorted II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/delete-columns-to-make-sorted-ii/"
)
public class Q955 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public int minDeletionSize(String[] strs) {
        int ans = 0;
        int n = strs.length;
        int len = strs[0].length();
        String[] ss = new String[n];
        Arrays.fill(ss, "");

        for(int i = 0; i < len; i++) {
            boolean delete = false;
            for(int j = 0; j < n - 1; j++) {
                if(ss[j].equals(ss[j + 1]) && strs[j].charAt(i) > strs[j + 1].charAt(i)) {
                    ans++;
                    delete = true;
                    break;
                }
            }

            if(!delete) {
                for(int j = 0; j < n; j++) {
                    ss[j] += strs[j].charAt(i);
                }
            }
        }

        return ans;
    }
}
