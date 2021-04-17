package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Path Sum II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/path-sum-ii/"
)
public class Q113 {
    /*
    * 为什么bfs不太好？ 题目要求找到左右满足条件的path，bfs会需要保存所有candidate而不是当前path
    * space是O(n) 而dfs是O(logN)
    * */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> path = new ArrayList<>();
        findPath(root, targetSum, new ArrayList<>(), path);
        return path;
    }

    private void findPath(TreeNode root, int remain, List<Integer> candidate, List<List<Integer>> path) {
        if(root == null)
            return;

        remain -= root.val;
        candidate.add(root.val);

        if(root.left == null && root.right == null && remain == 0)
            path.add(new ArrayList<>(candidate));

        findPath(root.left, remain, candidate, path);
        findPath(root.right, remain, candidate, path);

        candidate.remove(candidate.size() - 1);
    }
}
