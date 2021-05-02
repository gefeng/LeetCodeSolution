package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Binary Tree Vertical Order Traversal",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/binary-tree-vertical-order-traversal/"
)
public class Q314 {
    class Node {
        int val;
        int x;
        int y;
        public Node(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y = y;
        }
    }
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null)
            return new ArrayList<>();

        List<Node> nodes = new ArrayList<>();
        preorder(root, 0, 0, nodes);

        Collections.sort(nodes, (a, b) -> {
            if(a.x != b.x) {
                return a.x - b.x;
            }
            else {
                return a.y - b.y;
            }
        });

        List<List<Integer>> ans = new ArrayList<>();
        int prevX = nodes.get(0).x - 1;
        int i = -1;
        for(Node n : nodes) {
            if(n.x != prevX) {
                i++;
                ans.add(new ArrayList<>());
            }
            ans.get(i).add(n.val);
            prevX = n.x;
        }
        return ans;
    }

    private void preorder(TreeNode root, int posX, int posY, List<Node> nodes) {
        if(root == null)
            return;

        nodes.add(new Node(root.val, posX, posY));

        preorder(root.left, posX - 1, posY + 1, nodes);
        preorder(root.right, posX + 1, posY + 1, nodes);
    }

    private List<List<Integer>> bfsSolution(TreeNode root) {
        if(root == null)
            return new ArrayList<>();

        Map<Integer, List<Integer>> colMap = new HashMap<>();
        Queue<TreeNode> nQueue = new ArrayDeque<>();
        Queue<Integer> cQueue = new ArrayDeque<>();
        nQueue.offer(root);
        cQueue.offer(0);
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;
        while(!nQueue.isEmpty()) {
            int size = nQueue.size();
            for(int i = 0; i < size; i++) {
                TreeNode n = nQueue.poll();
                int c = cQueue.poll();

                colMap.computeIfAbsent(c, k -> new ArrayList<>()).add(n.val);
                minCol = Math.min(minCol, c);
                maxCol = Math.max(maxCol, c);

                if(n.left != null) {
                    nQueue.offer(n.left);
                    cQueue.offer(c - 1);
                }
                if(n.right != null) {
                    nQueue.offer(n.right);
                    cQueue.offer(c + 1);
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for(int i = minCol; i <= maxCol; i++) {
            ans.add(colMap.get(i));
        }

        return ans;
    }
}
