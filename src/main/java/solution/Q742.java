package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Closest Leaf in a Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/closest-leaf-in-a-binary-tree/"
)
public class Q742 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    Map<TreeNode, List<TreeNode>> g = new HashMap<>();
    TreeNode t = null;
    public int findClosestLeaf(TreeNode root, int k) {
        toGraph(root, null, k);

        Queue<TreeNode> q = new ArrayDeque<>();
        Set<TreeNode> visited = new HashSet<>();

        q.offer(t);
        visited.add(t);

        while(!q.isEmpty()) {
            int sz = q.size();
            for(int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();

                if(cur.left == null && cur.right == null) return cur.val;

                List<TreeNode> neis = g.get(cur);
                for(TreeNode nei : neis) {
                    if(!visited.contains(nei)) {
                        visited.add(nei);
                        q.offer(nei);
                    }
                }
            }
        }

        return -1;
    }

    private void toGraph(TreeNode cur, TreeNode p, int k) {
        if(cur == null) return;

        List<TreeNode> neis = g.computeIfAbsent(cur, key -> new ArrayList<>());
        if(p != null) neis.add(p);
        if(cur.left != null) neis.add(cur.left);
        if(cur.right != null) neis.add(cur.right);

        if(cur.val == k) {
            t = cur;
        }

        toGraph(cur.left, cur, k);
        toGraph(cur.right, cur, k);
    }
}
