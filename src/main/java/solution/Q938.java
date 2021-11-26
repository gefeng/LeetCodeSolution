package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Range Sum of BST",
        difficulty = QDifficulty.EASY,
        tag = QTag.RECURSION,
        url = "https://leetcode.com/problems/range-sum-of-bst/"
)
public class Q938 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int rangeSumBST(TreeNode root, int L, int R) {
        if(root == null)
            return 0;

        int sum = 0;

        if(root.val < R)
            sum += rangeSumBST(root.right, L, R);
        if(root.val > L)
            sum += rangeSumBST(root.left, L, R);

        if(root.val >= L && root.val <= R)
            sum += root.val;

        return sum;
    }
}

