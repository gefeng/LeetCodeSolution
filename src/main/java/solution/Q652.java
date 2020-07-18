package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

@Problem(
        title = "Find Duplicate Subtrees",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/find-duplicate-subtrees/"
)
public class Q652 {
    private int uidGenerator = 1;
    private int lookUp(TreeNode root, HashMap<String, Integer> uidMap,
                       HashMap<Integer, Integer> treeMap, List<TreeNode> dup) {
        if(root == null)
            return 0;

        String serial = root.val + "," +
                lookUp(root.left, uidMap, treeMap, dup) + "," +
                lookUp(root.right, uidMap, treeMap, dup);

        if(!uidMap.containsKey(serial))
            uidMap.put(serial, uidGenerator++);

        int uid = uidMap.get(serial);
        int count = treeMap.getOrDefault(uid, 0) + 1;
        if(count == 2)
            dup.add(root);
        treeMap.put(uid, count);
        return uid;
    }
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        HashMap<String, Integer> uidMap = new HashMap<>();
        HashMap<Integer, Integer> treeMap = new HashMap<>();
        List<TreeNode> dup = new ArrayList<>();

        lookUp(root, uidMap, treeMap, dup);

        return dup;
    }
}
