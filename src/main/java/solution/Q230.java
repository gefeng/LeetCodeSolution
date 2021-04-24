package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Kth Smallest Element in a BST",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/kth-smallest-element-in-a-bst/"
)
public class Q230 {
    int count = 0;
    public int kthSmallest(TreeNode root, int k) {
        //return inorder(root, k);
        return iterative(root, k);
    }

    private int inorder(TreeNode root, int k) {
        if(root == null)
            return -1;

        int kth = inorder(root.left, k);

        if(kth != -1)
            return kth;

        count++;
        if(count == k)
            return root.val;

        kth = inorder(root.right, k);

        return kth;
    }

    private int iterative(TreeNode root, int k) {
        int kth = -1;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while(!stack.isEmpty() || curr != null) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            if(--k == 0) {
                kth = curr.val;
                break;
            }

            curr = curr.right;
        }

        return kth;
    }
}
