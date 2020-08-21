package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;

@Problem(
        title = "Path Sum III",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/path-sum-iii/https://leetcode.com/problems/path-sum-iii/"
)
public class Q437 {
    int count = 0;
    public int pathSum(TreeNode root, int sum) {
        findPath(root, sum, 0, new HashMap<>());
        return count;
    }

    private void findPath(TreeNode root, int target, int prefixSum, HashMap<Integer, Integer> map) {
        if(root == null)
            return;

        prefixSum += root.val;
        if(prefixSum == target)
            count++;
        count += map.getOrDefault(prefixSum - target, 0);
        map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);

        findPath(root.left, target, prefixSum, map);
        findPath(root.right, target, prefixSum, map);

        map.put(prefixSum, map.get(prefixSum) - 1);
        if(map.get(prefixSum) == 0)
            map.remove(prefixSum);
    }
}