package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Unique Binary Search Trees II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/unique-binary-search-trees-ii/"
)
public class Q95 {
    private List<TreeNode> generate(int start, int end) {
        List<TreeNode> roots = new ArrayList<>();
        if(start < end) {
            roots.add(null);
            return roots;
        }
        for(int i = start; i <= end; i++) {
            List<TreeNode> left = generate(start, i - 1);
            List<TreeNode> right = generate(i + 1, end);
            for(TreeNode l : left) {
                for(TreeNode r : right) {
                    roots.add(new TreeNode(i, l, r));
                }
            }
        }
        return roots;
    }
    public List<TreeNode> generateTrees(int n) {
        if(n == 0)
            return new ArrayList<>();
        return generate(1, n);
    }
}
