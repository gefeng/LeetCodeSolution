package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@Problem(
        title = "Same Tree",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/same-tree/"
)
public class Q100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        if(p == null || q == null)
            return false;

        if(p.val != q.val)
            return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSameTreeIterativeDFS(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        if(p == null || q == null)
            return false;

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(p);
        s2.push(q);
        while(!s1.isEmpty() && !s2.isEmpty()) {
            TreeNode n1 = s1.pop();
            TreeNode n2 = s2.pop();
            if(n1.val != n2.val)
                return false;

            if(n1.left != null && n2.left != null) {
                s1.push(n1.left);
                s2.push(n2.left);
            }
            else if(n1.left != n2.left)
                return false;

            if(n1.right != null && n2.right != null) {
                s1.push(n1.right);
                s2.push(n2.right);
            }
            else if(n1.right != n2.right)
                return false;
        }

        return s1.isEmpty() && s2.isEmpty();
    }

    public boolean isSameTreeIterativeBFS(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        if(p == null || q == null)
            return  false;

        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.offer(p);
        q2.offer(q);
        while(!q1.isEmpty() && !q2.isEmpty()) {
            int size = q1.size();
            if(size != q2.size())
                return false;

            for(int i = 0; i < size; i++) {
                TreeNode n1 = q1.poll();
                TreeNode n2 = q2.poll();
                if(n1.val != n2.val)
                    return false;

                if(n1.left != null && n2.left != null) {
                    q1.offer(n1.left);
                    q2.offer(n2.left);
                }
                else if(n1.left != n2.left)
                    return false;

                if(n1.right != null && n2.right != null) {
                    q1.offer(n1.right);
                    q2.offer(n2.right);
                }
                else if(n1.right != n2.right)
                    return false;
            }
        }

        return true;
    }
}
