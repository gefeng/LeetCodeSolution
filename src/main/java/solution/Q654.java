package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/maximum-binary-tree/"
)
public class Q654 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int st, int ed) {
        if(st == ed) return new TreeNode(nums[st]);
        if(st > ed) return null;

        int pivot = st;
        for(int i = st; i <= ed; i++) {
            if(nums[i] > nums[pivot]) {
                pivot = i;
            }
        }

        TreeNode root = new TreeNode(nums[pivot]);
        root.left = dfs(nums, st, pivot - 1);
        root.right = dfs(nums, pivot + 1, ed);

        return root;
    }
}
