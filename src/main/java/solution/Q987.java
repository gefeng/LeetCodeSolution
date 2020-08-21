package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Vertical Order Traversal of a Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/"
)
public class Q987 {
    class Node {
        int x;
        int y;
        int val;
        Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if(root == null)
            return ans;

        List<Node> nodes = new ArrayList<>();
        dfs(root, 0, 0, nodes);

        Collections.sort(nodes, (a, b) -> {
            if(a.x == b.x) {
                if(a.y == b.y)
                    return a.val - b.val;
                else
                    return a.y - b.y;
            }
            else
                return a.x - b.x;
        });

        Node prev = nodes.get(0);
        ans.add(new ArrayList<>());
        ans.get(0).add(prev.val);
        for(int i = 1; i < nodes.size(); i++) {
            Node curr = nodes.get(i);
            if(curr.x != prev.x)
                ans.add(new ArrayList<>());
            ans.get(ans.size() - 1).add(curr.val);
            prev = curr;
        }

        return ans;
    }

    private void dfs(TreeNode root, int x, int y, List<Node> nodes) {
        if(root == null)
            return;

        nodes.add(new Node(x, y, root.val));

        dfs(root.left, x - 1, y + 1, nodes);
        dfs(root.right, x + 1, y + 1, nodes);
    }
}