package solution.weekly283;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Create Binary Tree From Descriptions",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/contest/weekly-contest-283/problems/create-binary-tree-from-descriptions/"
)
public class Q2196 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, int[]> g = new HashMap<>();
        Map<Integer, Integer> ind = new HashMap<>();

        for(int[] d : descriptions) {
            if(d[2] == 1) {
                int[] c = g.computeIfAbsent(d[0], k -> new int[] {-1, -1});
                c[0] = d[1];
            } else {
                int[] c = g.computeIfAbsent(d[0], k -> new int[] {-1, -1});
                c[1] = d[1];
            }
            ind.put(d[1], ind.getOrDefault(d[1], 0) + 1);
        }

        int root = 0;
        for(int[] d : descriptions) {
            if(ind.getOrDefault(d[0], 0) == 0) {
                root = d[0];
                break;
            }
        }

        return dfs(g, root);
    }

    public TreeNode dfs(Map<Integer, int[]> g, int cur) {

        if(cur == -1) return null;

        TreeNode root = new TreeNode(cur);

        int[] c = g.get(cur);

        if(c != null) {
            root.left = dfs(g, c[0]);
            root.right = dfs(g, c[1]);
        }


        return root;
    }
}
