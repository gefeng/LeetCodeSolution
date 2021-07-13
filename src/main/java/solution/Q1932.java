package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Merge BSTs to Create Single BST",
        difficulty = QDifficulty.HARD,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/merge-bsts-to-create-single-bst/"
)
public class Q1932 {
    public TreeNode canMerge(List<TreeNode> trees) {
        Map<Integer, TreeNode> rootMap = new HashMap<>();
        Map<Integer, Integer> freqMap = new HashMap<>();
        TreeNode root = null;

        for(TreeNode tree : trees) {
            rootMap.put(tree.val, tree);
            freqMap.put(tree.val, freqMap.getOrDefault(tree.val, 0) + 1);

            if(tree.left != null) {
                freqMap.put(tree.left.val, freqMap.getOrDefault(tree.left.val, 0) + 1);
            }
            if(tree.right != null) {
                freqMap.put(tree.right.val, freqMap.getOrDefault(tree.right.val, 0) + 1);
            }
        }

        for(int key : rootMap.keySet()) {
            if(freqMap.get(key) == 1) {
                root = rootMap.get(key);
                break;
            }
        }

        if(merge(root, rootMap, Integer.MAX_VALUE, Integer.MIN_VALUE) && rootMap.size() == 1) {
            return root;
        }
        return null;
    }

    private boolean merge(TreeNode root, Map<Integer, TreeNode> rootMap, Integer upper, Integer lower) {
        if(root == null) {
            return true;
        }

        if(root.val >= upper || root.val <= lower) {
            return false;
        }

        if(root.left == root.right) {
            TreeNode node = rootMap.get(root.val);
            if(node != null && root != node) {
                root.left = node.left;
                root.right = node.right;
                rootMap.remove(root.val);
            }
        }

        return merge(root.left, rootMap, root.val, lower) && merge(root.right, rootMap, upper, root.val);
    }
}
