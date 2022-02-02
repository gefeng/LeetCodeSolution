package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Problem(
        title = "Average of Levels in Binary Tree",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/average-of-levels-in-binary-tree/"
)
public class Q637 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();

        q.offer(root);
        while(!q.isEmpty()) {
            int sz = q.size();
            double sum = 0;
            for(int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();

                sum += cur.val;

                if(cur.left != null) q.offer(cur.left);
                if(cur.right != null) q.offer(cur.right);
            }

            ans.add(sum / sz);
        }

        return ans;
    }
}
