package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Equal Tree Partition",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/equal-tree-partition/"
)
public class Q663 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean checkEqualTree(TreeNode root) {
        List<Integer> preSum = new ArrayList<>();
        postorder(root, preSum);

        int n = preSum.size();
        int total = preSum.get(n - 1);

        for(int i = 0; i < n - 1; i++) {
            int sum = preSum.get(i);
            if(sum == total - sum) {
                return true;
            }
        }

        return false;
    }

    private int postorder(TreeNode root, List<Integer> preSum) {
        if(root == null) {
            return 0;
        }

        int l = postorder(root.left, preSum);
        int r = postorder(root.right, preSum);
        int sum = l + r + root.val;

        preSum.add(sum);

        return sum;
    }
}
