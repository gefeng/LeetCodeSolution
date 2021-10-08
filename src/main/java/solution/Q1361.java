package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Validate Binary Tree Nodes",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/validate-binary-tree-nodes/"
)
public class Q1361 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] cntP = new int[n];

        for(int i = 0; i < n; i++) {
            if(leftChild[i] != -1) {
                cntP[leftChild[i]]++;
            }
            if(rightChild[i] != -1) {
                cntP[rightChild[i]]++;
            }
        }

        int root = 0;
        for(int i = 0; i < n; i++) {
            if(cntP[i] == 0) {
                root = i;
            }
        }

        boolean[] seen = new boolean[n];
        if(!dfs(root, leftChild, rightChild, seen)) {
            return false;
        }

        for(int i = 0; i < n; i++) {
            if(!seen[i]) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int root, int[] lc, int[] rc, boolean[] seen) {
        if(seen[root]) {
            return false;
        }

        seen[root] = true;

        int l = lc[root];
        int r = rc[root];

        if(l != -1) {
            if(!dfs(l, lc, rc, seen)) {
                return false;
            }
        }
        if(r != -1) {
            if(!dfs(r, lc, rc, seen)) {
                return false;
            }
        }

        return true;
    }
}
