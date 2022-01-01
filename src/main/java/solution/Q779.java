package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "K-th Symbol in Grammar",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.RECURSION,
        url = "https://leetcode.com/problems/k-th-symbol-in-grammar/"
)
public class Q779 {
    /**
     * Convert to binary tree.
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public int kthGrammar(int n, int k) {
        if(n == 1) return 0;

        int pre = kthGrammar(n - 1, (k + 1) / 2);

        return k % 2 == 0 ? pre ^ 1 : pre;
    }
}
