package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Problem(
        title = "",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/binary-tree-right-side-view/"
)
public class Q199 {
    public List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> rValues = new ArrayList<>();
        if(root == null)
            return rValues;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if(i == size - 1)
                    rValues.add(curr.val);

                if(curr.left != null)
                    queue.offer(curr.left);
                if(curr.right != null)
                    queue.offer(curr.right);
            }
        }
        return rValues;
    }

    /*DFS解法有一定技巧性，level == arrayList.size()*/
    private void rightTraversal(TreeNode node, int level, ArrayList<Integer> rValues) {
        if(node == null)
            return;
        if(level == rValues.size())
            rValues.add(node.val);
        rightTraversal(node.right, level + 1, rValues);
        rightTraversal(node.left, level + 1, rValues);
    }
    public List<Integer> rightSideViewDFS(TreeNode root) {
        ArrayList<Integer> rValues = new ArrayList<>();
        rightTraversal(root, 0, rValues);
        return rValues;
    }
}
