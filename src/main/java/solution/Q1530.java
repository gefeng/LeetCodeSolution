package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import javax.swing.table.TableRowSorter;

@Problem(
        title = "Number of Good Leaf Nodes Pairs",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/number-of-good-leaf-nodes-pairs/"
)
public class Q1530 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private int count = 0;
    private int[] traverse(TreeNode root, int distance) {
        int[] ret = new int[distance + 1];
        if(root == null || distance < 2)
            return ret;
        if(root.left == null && root.right == null) {
            ret[1] = 1;
            return ret;
        }

        int[] left = traverse(root.left, distance);
        int[] right = traverse(root.right, distance);

        for(int i = 1; i <= distance; i++) {
            for(int j = 1; j <= distance; j++) {
                if(i + j <= distance)
                    count += (left[i] * right[j]);
            }
        }

        for(int i = 1; i <= distance; i++)
            ret[i] = left[i - 1] + right[i - 1];

        return ret;
    }
    public int countPairs(TreeNode root, int distance) {
        traverse(root, distance);
        return count;
    }
}
