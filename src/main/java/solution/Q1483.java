package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Kth Ancestor of a Tree Node",
        difficulty = QDifficulty.HARD,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/kth-ancestor-of-a-tree-node/"
)
public class Q1483 {
    /**
     * Binary lifting
     *
     * Preprocess
     * Time:  O(N * logN)
     * Space: O(N * logN)
     *
     * Query
     * Time:  O(M * logN)
     * Space: O(1)
     * */
    private int[][] up;
    public Q1483(int n, int[] parent) {
        up = new int[n][16];

        for(int i = 0; i < n; i++) {
            Arrays.fill(up[i], -1);
            up[i][0] = parent[i];
        }

        for(int j = 1; j < 16; j++) {
            for(int i = 0; i < n; i++) {
                if(up[i][j - 1] != -1) {
                    up[i][j] = up[up[i][j - 1]][j - 1];
                }
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        for(int j = 15; j >= 0; j--) {
            if((1 << j) <= k) {
                k -= (1 << j);
                node = up[node][j];
                if(node == -1) {
                    return node;
                }
            }
        }

        return node;
    }
}
