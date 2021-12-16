package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "All Nodes Distance K in Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/"
)
public class Q863 {
    /*
    * The idea is to covert a tree to a graph by creating parent map
    * Do a dfs to find and mark each node's parent
    * Do a bfs to find all nodes with distance K from target node
    * */
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        HashMap<TreeNode, TreeNode> map = new HashMap<>();
        dfsFindParent(root, null, map);
        return bfsFindNodes(target, K, map);
    }

    private void dfsFindParent(TreeNode root, TreeNode parent, HashMap<TreeNode, TreeNode> map) {
        if(root == null)
            return;

        map.put(root, parent);
        dfsFindParent(root.left, root, map);
        dfsFindParent(root.right, root, map);
    }

    private List<Integer> bfsFindNodes(TreeNode root, int K, HashMap<TreeNode, TreeNode> map) {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        HashSet<TreeNode> visited = new HashSet<>();
        queue.offer(root);
        visited.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if(K == 0)
                    ans.add(curr.val);
                else {
                    if(curr.left != null && !visited.contains(curr.left)) {
                        queue.offer(curr.left);
                        visited.add(curr.left);
                    }
                    if(curr.right != null && !visited.contains(curr.right)) {
                        queue.offer(curr.right);
                        visited.add(curr.right);
                    }
                    TreeNode parent = map.get(curr);
                    if(parent != null && !visited.contains(parent)) {
                        queue.offer(parent);
                        visited.add(parent);
                    }
                }
            }
            K--;
        }
        return ans;
    }
}
