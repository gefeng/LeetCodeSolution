package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Problem(
        title = "N-ary Tree Level Order Traversal",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/n-ary-tree-level-order-traversal/"
)
public class Q429 {
    class Node {
        int val;
        List<Node> children;
        Node(int val) {
            this.val = val;
        }
        Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    };
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null)
            return ans;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                Node curr = queue.poll();
                level.add(curr.val);
                for(Node child : curr.children) {
                    if(child != null)
                        queue.offer(child);
                }
            }
            ans.add(level);
        }
        return ans;
    }
}
