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
        title = "Find Largest Value in Each Tree Row",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/find-largest-value-in-each-tree-row/"
)
public class Q515 {
    public List<Integer> largestValues(TreeNode root) {
        //return bfsSolution(root);
        return dfsSolution(root);
    }

    private List<Integer> bfsSolution(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if(root == null)
            return ans;

        queue.offer(root);
        while(!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if(curr.val > max)
                    max = curr.val;
                if(curr.left != null)
                    queue.offer(curr.left);
                if(curr.right != null)
                    queue.offer(curr.right);
            }
            ans.add(max);
        }

        return ans;
    }

    private List<Integer> dfsSolution(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfsHelper(root, 0, ans);
        return ans;
    }

    private void dfsHelper(TreeNode root, int level, List<Integer> ans) {
        if(root == null)
            return;

        if(level > ans.size() - 1)
            ans.add(root.val);
        else {
            int currMax = ans.get(level);
            if(root.val > currMax)
                ans.set(level, root.val);
        }

        dfsHelper(root.left, level + 1, ans);
        dfsHelper(root.right, level + 1, ans);
    }
}
