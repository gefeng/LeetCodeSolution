package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Constructed Quad Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/construct-quad-tree/"
)
public class Q427 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(1)
     * */
    private class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
    public Node construct(int[][] grid) {
        int n = grid.length;
        return build(grid, new int[] {0, 0}, new int[] {n - 1, n - 1});
    }

    private Node build(int[][] grid, int[] top, int[] bot) {
        int topR = top[0];
        int topC = top[1];
        int botR = bot[0];
        int botC = bot[1];

        if(topR == botR && topC == botC) {
            return new Node(grid[topR][topC] == 1, true);
        }

        int size = (botR - topR + 1) / 2;

        Node tL = build(grid, top, new int[] {topR + size - 1, topC + size - 1});
        Node tR = build(grid, new int[] {topR, topC + size}, new int[] {topR + size - 1, topC + size * 2 - 1});
        Node bL = build(grid, new int[] {topR + size, topC}, new int[] {botR, topC + size - 1});
        Node bR = build(grid, new int[] {topR + size, topC + size}, bot);

        if((tL.isLeaf && tR.isLeaf && bL.isLeaf && bR.isLeaf) &&
                ((tL.val && tR.val && bR.val && bL.val) || (!tL.val && !tR.val && !bR.val && !bL.val))) {
            return new Node(tL.val, true);
        } else {
            return new Node(true, false, tL, tR, bL, bR);
        }
    }
}
