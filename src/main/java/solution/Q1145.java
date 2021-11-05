package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Binary Tree Coloring Game",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/binary-tree-coloring-game/"
)
public class Q1145 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    int px = -1;
    int lx = -1;
    int rx = -1;
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        int[] cnt = new int[n + 1];

        dfs(root, -1, x, cnt);

        if(px != -1) {
            if(cnt[x] < n - cnt[x]) {
                return true;
            }
        }
        if(lx != -1) {
            if(cnt[lx] > n - cnt[lx]) {
                return true;
            }
        }

        if(rx != -1) {
            if(cnt[rx] > n - cnt[rx]) {
                return true;
            }
        }

        return false;
    }

    private int dfs(TreeNode root, int p, int x, int[] cnt) {
        if(root == null) {
            return 0;
        }

        int l = dfs(root.left, root.val, x, cnt);
        int r = dfs(root.right, root.val, x, cnt);

        cnt[root.val] = l + r + 1;

        if(root.val == x) {
            px = p;
            lx = root.left == null ? -1 : root.left.val;
            rx = root.right == null ? -1 : root.right.val;
        }

        return l + r + 1;
    }
}
