package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Encode N-ary Tree to Binary Tree",
        difficulty = QDifficulty.HARD,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/"
)
public class Q431 {
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

    class Pair {
        Node naNode;
        TreeNode btNode;
        Pair(Node naNode, TreeNode btNode) {
            this.naNode = naNode;
            this.btNode = btNode;
        }
    }

    public TreeNode encode(Node root) {
        if(root == null)
            return null;
        TreeNode btRoot =  new TreeNode(root.val);
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, btRoot));

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Pair curr = queue.poll();

                TreeNode prevNode = null;
                for(int j = 0; j < curr.naNode.children.size(); j++) {
                    Node naChild = curr.naNode.children.get(j);
                    TreeNode btChild = new TreeNode(naChild.val);
                    if(prevNode == null)
                        curr.btNode.left = btChild;
                    else
                        prevNode.right = btChild;
                    prevNode = btChild;
                    queue.offer(new Pair(naChild, btChild));
                }
            }
        }

        return btRoot;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if(root == null)
            return null;
        Node naRoot = new Node(root.val, new ArrayList<>());
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(naRoot, root));
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Pair curr = queue.poll();

                TreeNode btChild = curr.btNode.left;
                while (btChild != null) {
                    Node naChild = new Node(btChild.val, new ArrayList<>());
                    curr.naNode.children.add(naChild);

                    queue.offer(new Pair(naChild, btChild));

                    btChild = btChild.right;
                }
            }
        }
        return naRoot;
    }
}
