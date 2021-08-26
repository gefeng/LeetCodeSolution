package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Logical OR of Two Binary Grids Represented as Quad-Trees",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/logical-or-of-two-binary-grids-represented-as-quad-trees/"
)
public class Q558 {
    private class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}

        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }

    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public Node intersect(Node quadTree1, Node quadTree2) {
        return dfs(quadTree1, quadTree2, true, true);
    }

    private Node dfs(Node n1, Node n2, boolean v1, boolean v2) {
        if(n1 == null && n2 == null) {
            return null;
        }

        Node root = new Node();
        if(n1 == null) {
            if(n2.isLeaf) {
                root.val = n2.val | v1;
                root.isLeaf = true;
                return root;
            }
            root.topLeft = dfs(null, n2.topLeft, v1, n2.val);
            root.topRight = dfs(null, n2.topRight, v1, n2.val);
            root.bottomLeft = dfs(null, n2.bottomLeft, v1, n2.val);
            root.bottomRight = dfs(null, n2.bottomRight, v1, n2.val);
        } else if(n2 == null) {
            if(n1.isLeaf) {
                root.val = n1.val | v2;
                root.isLeaf = true;
                return root;
            }
            root.topLeft = dfs(n1.topLeft, null, n1.val, v2);
            root.topRight = dfs(n1.topRight, null, n1.val, v2);
            root.bottomLeft = dfs(n1.bottomLeft, null, n1.val, v2);
            root.bottomRight = dfs(n1.bottomRight, null, n1.val, v2);
        } else {
            if(n1.isLeaf && n2.isLeaf) {
                root.val = n1.val | n2.val;
                root.isLeaf = true;
                return root;
            }
            root.topLeft = dfs(n1.topLeft, n2.topLeft, n1.val, n2.val);
            root.topRight = dfs(n1.topRight, n2.topRight, n1.val, n2.val);
            root.bottomLeft = dfs(n1.bottomLeft, n2.bottomLeft, n1.val, n2.val);
            root.bottomRight = dfs(n1.bottomRight, n2.bottomRight, n1.val, n2.val);
        }

        if(root.topLeft.isLeaf && root.topRight.isLeaf && root.bottomLeft.isLeaf && root.bottomRight.isLeaf &&
                allEqual(root.topLeft.val, root.topRight.val, root.bottomLeft.val, root.bottomRight.val)) {
            root.isLeaf = true;
            root.val = root.topLeft.val;
            root.topLeft = null;
            root.topRight = null;
            root.bottomLeft = null;
            root.bottomRight = null;
        } else {
            root.isLeaf = false;
        }

        return root;
    }

    private boolean allEqual(boolean a, boolean b, boolean c, boolean d) {
        return a == b && a == c && a == d;
    }
}
