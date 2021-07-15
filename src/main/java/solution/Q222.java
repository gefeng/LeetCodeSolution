package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Complete Tree Nodes",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/count-complete-tree-nodes/"
)
public class Q222 {
    public int countNodes(TreeNode root) {
        int h = getHeight(root);
        if(h == -1) {
            return 0;
        }

        int lo = (int)Math.pow(2, h);
        int hi = (int)Math.pow(2, h + 1) - 1;
        int lastNode = 0;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(exist(root, h, mid)) {
                lastNode = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return lastNode;
    }

    private int getHeight(TreeNode root) {
        if(root == null) {
            return -1;
        }

        int h = 0;
        while(root.left != null) {
            root = root.left;
            h++;
        }
        return h;
    }

    // binary decode - take last h bits - 0 go left 1 go right
    private boolean exist(TreeNode root, int h, int idx) {
        for(int i = h - 1; i >= 0; i--) {
            if(((1 << i) & idx) == 0) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root != null;
    }
}
