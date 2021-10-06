package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Balance a Binary Search Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH_TREE,
        url = "https://leetcode.com/problems/balance-a-binary-search-tree/"
)
public class Q1382 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> nums = new ArrayList<>();

        inorder(root, nums);

        return build(nums, 0, nums.size() - 1);
    }

    private void inorder(TreeNode root, List<Integer> nums) {
        if(root == null) {
            return;
        }

        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    private TreeNode build(List<Integer> nums, int start, int end) {
        if(start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums.get(mid));
        root.left = build(nums, start, mid - 1);
        root.right = build(nums, mid + 1, end);
        return root;
    }
}
