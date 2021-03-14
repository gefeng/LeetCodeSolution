package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Stack;

@Problem(
        title = "Two Sum BSTs",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH_TREE,
        url = "https://leetcode.com/problems/two-sum-bsts/"
)
public class Q1214 {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        HashSet<Integer> set = new HashSet<>();
        buildSet(root1, set);
        return search(root2, set, target);
    }

    private void buildSet(TreeNode root, HashSet<Integer> set) {
        if(root == null)
            return;

        set.add(root.val);
        buildSet(root.left, set);
        buildSet(root.right, set);
    }

    private boolean search(TreeNode root, HashSet<Integer> set, int target) {
        if(root == null)
            return false;
        if(set.contains(target - root.val))
            return true;
        return search(root.left, set, target) || search(root.right, set, target);
    }

    private boolean iterativeInorderTraversal(TreeNode root1, TreeNode root2, int target) {
        if(root1 == null || root2 == null)
            return false;

        HashSet<Integer> set = new HashSet<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root1;
        while(!stack.isEmpty() || curr != null) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            set.add(curr.val);
            curr = curr.right;
        }

        curr = root2;
        while(!stack.isEmpty() || curr != null) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if(set.contains(target - curr.val))
                return true;
            curr = curr.right;
        }

        return false;
    }
}
