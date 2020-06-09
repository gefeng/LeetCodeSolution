package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Delete Node in a BST",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/delete-node-in-a-bst/"
)
public class Q450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null)
            return null;

        if(key < root.val)
            root.left = deleteNode(root.left, key);
        else if(key > root.val)
            root.right = deleteNode(root.right, key);
        else {
            if(root.left == null)
                return root.right;
            if(root.right == null)
                return root.left;

            // find new root which will be the smallest(leftmost) node in right subtree a.k.a inorder successor.
            TreeNode newRoot = root.right;
            while(newRoot.left != null)
                newRoot = newRoot.left;

            // do the replacement and delete the new root in its old place.
            root.val = newRoot.val;
            root.right = deleteNode(root.right, newRoot.val);
        }
        return root;
    }

    /*不用递归的解法面试时候能写对？*/
    private TreeNode deleteRoot(TreeNode root) {
        if(root == null)
            return null;
        if(root.left == null)
            return root.right;
        if(root.right == null)
            return root.left;

        TreeNode newRoot = root.right;
        TreeNode prev = root;
        while(newRoot.left != null) {
            prev = newRoot;
            newRoot = newRoot.left;
        }

        newRoot.left = root.left;

        if(prev != root) {
            prev.left = newRoot.right;
            newRoot.right = root.right;
        }

        return newRoot;
    }
    public TreeNode deleteNodeIterative(TreeNode root, int key) {
        TreeNode curr = root;
        TreeNode prev = null;
        while(curr != null && curr.val != key) {
            prev = curr;
            if(key < curr.val) curr = curr.left;
            else curr = curr.right;
        }

        if(prev == null) {
            return deleteRoot(curr);
        }
        if(prev.left == curr) {
            prev.left = deleteRoot(curr);
        }
        if(prev.right == curr) {
            prev.right = deleteRoot(curr);
        }
        return root;
    }
}
