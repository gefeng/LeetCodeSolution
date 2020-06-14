package solution;

import annotations.Problem;
import com.sun.source.tree.ReturnTree;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Convert Sorted Array to Binary Search Tree",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/"
)
public class Q108 {
    private TreeNode createBST(int[] nums, int start, int end) {
        if(start > end)
            return null;
        int middle = (start + end) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = createBST(nums, start, middle - 1);
        root.right = createBST(nums, middle + 1, end);
        return root;

    }
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0)
            return null;
        TreeNode root = createBST(nums, 0, nums.length - 1);
        return root;
    }
}
