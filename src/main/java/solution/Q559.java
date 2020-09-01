package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Problem(
        title = "Maximum Depth of N-ary Tree",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/maximum-depth-of-n-ary-tree/"
)
public class Q559 {
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
    }
    public int maxDepth(Node root) {
        if(root == null)
            return 0;

        int maxDepth = 0;
        for(Node child : root.children)
            maxDepth = Math.max(maxDepth, maxDepth(child));

        return maxDepth + 1;
    }

    public int maxDepthBFS(Node root) {
        if(root == null)
            return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int maxDepth = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Node curr = queue.poll();
                for (Node child : curr.children)
                    queue.offer(child);
            }
            maxDepth++;
        }

        return maxDepth;
    }
}
