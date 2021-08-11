package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Ways to Reorder Array to Get Same BST",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/"
)
public class Q1569 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    private class Node {
        Node left;
        Node right;
        int val;
        Node(int val) {
            this.val = val;
        }
    }
    private static final int MOD = (int)1e9 + 7;
    private int[][] c = new int[1001][1001];

    public int numOfWays(int[] nums) {
        int n = nums.length;

        pascal();

        Node root = null;
        for(int i = 0; i < n; i++) {
            root = add(root, nums[i]);
        }

        return dfs(root)[1] - 1;
    }

    private void pascal() {
        c[0][0] = 1;
        c[1][0] = 1;
        c[1][1] = 1;

        for(int i = 2; i < 1001; i++) {
            c[i][0] = 1;
            c[i][i] = 1;
            for(int j = 1; j < i; j++) {
                c[i][j] = (int)(((long)c[i - 1][j - 1] + c[i - 1][j]) % MOD);
            }
        }
    }

    private Node add(Node root, int val) {
        if(root == null) {
            return new Node(val);
        }

        if(root.val > val) {
            root.left = add(root.left, val);
        } else {
            root.right = add(root.right, val);
        }

        return root;
    }

    private int[] dfs(Node root) {
        if(root == null) {
            return new int[] {0, 1};
        }

        int[] l = dfs(root.left);
        int[] r = dfs(root.right);
        int n = l[0] + r[0] + 1;

        if(n < 3) {
            return new int[] { n, 1 };
        } else {
            return new int[] { n, (int)(((long)c[n - 1][l[0]] * l[1] % MOD) * r[1] % MOD) };
        }
    }
}
