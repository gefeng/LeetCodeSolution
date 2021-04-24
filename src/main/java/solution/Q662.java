package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Maximum Width of Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/maximum-width-of-binary-tree/"
)
public class Q662 {
    private class Pair {
        TreeNode node;
        int index;
        Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        return bfsSolution(root);
    }

    private int bfsSolution(TreeNode root) {
        if(root == null)
            return 0;

        int maxWidth = 0;
        List<Integer> pDist = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        pDist.add(0);

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> cDist = new ArrayList<>();

            maxWidth = Math.max(maxWidth, pDist.get(size - 1) - pDist.get(0) + 1);

            for(int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                int dist = pDist.get(i);
                if(curr.left != null) {
                    queue.offer(curr.left);
                    cDist.add(dist * 2);
                }
                if(curr.right != null) {
                    queue.offer(curr.right);
                    cDist.add(dist *  2 + 1);
                }
            }

            pDist = cDist;
        }

        return maxWidth;
    }

    int max = 0;
    private int dfsSolution(TreeNode root) {
        preorder(new Pair(root, 0), 0, new HashMap<>());
        return max;
    }

    private void preorder(Pair root, int depth, Map<Integer, Pair> leftMost) {
        if(root == null)
            return;

        if(!leftMost.containsKey(depth)) {
            leftMost.put(depth, root);
        } else {
            max = Math.max(max, root.index - leftMost.get(depth).index + 1);
        }

        Pair left = root.node.left == null ? null : new Pair(root.node.left, root.index * 2);
        Pair right = root.node.right == null ? null : new Pair(root.node.right, root.index * 2 + 1);

        preorder(left, depth + 1, leftMost);
        preorder(right, depth + 1, leftMost);
    }
}
