package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Binary Tree Longest Consecutive Sequence II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/"
)
public class Q549 {
    class Solution {
        /**
         * 1. compare max(L + 1, R + 1, L + R + 1)
         * 2. return longest increasing sequence and longest decreasing sequence
         *
         * Time:  O(N)
         * Space: O(N)
         */
        int maxLen = 1;

        public int longestConsecutive(TreeNode root) {
            postorder(root);
            return maxLen;
        }

        // 0: length of the increasing path end with current node
        // 1: length of the decreasing path end with current node
        private int[] postorder(TreeNode root) {
            if (root == null) {
                return new int[]{0, 0};
            }

            int[] pathL = postorder(root.left);
            int[] pathR = postorder(root.right);
            int[] path = new int[2];

            if (pathL[0] == 0) {
                path[0] = 1;
            } else {
                path[0] = root.val - root.left.val == 1 ? pathL[0] + 1 : 1;
            }

            if (pathL[1] == 0) {
                path[1] = 1;
            } else {
                path[1] = root.val - root.left.val == -1 ? pathL[1] + 1 : 1;
            }

            if (pathR[0] != 0 && root.val - root.right.val == 1) {
                path[0] = Math.max(path[0], pathR[0] + 1);
            }

            if (pathR[1] != 0 && root.val - root.right.val == -1) {
                path[1] = Math.max(path[1], pathR[1] + 1);
            }

            maxLen = Math.max(maxLen, Math.max(path[0], path[1]));

            if (pathL[0] > 0 && pathR[1] > 0 && root.val - root.left.val == 1 && root.val - root.right.val == -1) {
                maxLen = Math.max(maxLen, pathL[0] + pathR[1] + 1);
            }
            if (pathL[1] > 0 && pathR[0] > 0 && root.val - root.left.val == -1 && root.val - root.right.val == 1) {
                maxLen = Math.max(maxLen, pathL[1] + pathR[0] + 1);
            }

            return path;
        }
    }
}
