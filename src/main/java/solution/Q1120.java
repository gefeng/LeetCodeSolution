package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Average Subtree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/maximum-average-subtree/"
)
public class Q1120 {
    public double maximumAverageSubtree(TreeNode root) {
        return postorder(root)[2];
    }

    private double[] postorder(TreeNode root) {
        if(root == null)
            return new double[] {0, 0, 0};

        double[] lInfo = postorder(root.left);
        double[] rInfo = postorder(root.right);

        double sum = lInfo[0] + rInfo[0] + root.val;
        double numNodes = lInfo[1] + rInfo[1] + 1;
        double maxAverage = Math.max(Math.max(lInfo[2], rInfo[2]), sum / numNodes);

        return new double[] {sum, numNodes, maxAverage};
    }
}
