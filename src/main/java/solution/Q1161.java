package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Maximum Level Sum of a Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/"
)
public class Q1161 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int maxLevelSum(TreeNode root) {
        int ans = 1;
        int max = Integer.MIN_VALUE;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        int level = 1;
        while(!q.isEmpty()) {
            int len = q.size();
            int sum = 0;
            for(int i = 0; i < len; i++) {
                TreeNode cur = q.poll();
                sum += cur.val;

                if(cur.left != null) {
                    q.offer(cur.left);
                }
                if(cur.right != null) {
                    q.offer(cur.right);
                }
            }

            if(sum > max) {
                max = sum;
                ans = level;
            }
            level++;
        }

        return ans;
    }
}
