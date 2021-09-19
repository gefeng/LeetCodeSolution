package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Final Value of Variable After Performing Operations",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/final-value-of-variable-after-performing-operations/"
)
public class Q2011 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int finalValueAfterOperations(String[] operations) {
        int ans = 0;
        for(String op : operations) {
            if(op.contains("--")) {
                ans--;
            } else {
                ans++;
            }
        }

        return ans;
    }
}
