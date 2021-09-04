package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Find Mode in Binary Search Tree",
        difficulty = QDifficulty.EASY,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/find-mode-in-binary-search-tree/"
)
public class Q501 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private int pre = 100001;
    private int maxFreq = 0;
    private int cnt = 0;
    public int[] findMode(TreeNode root) {
        List<Integer> modes = new ArrayList<>();

        findMax(root);
        System.out.println(maxFreq);
        pre = 100001;
        cnt = 0;
        findModes(root, modes);

        int[] res = new int[modes.size()];
        int i = 0;
        for(int m : modes) {
            res[i++] = m;
        }
        return res;
    }

    private void findMax(TreeNode root) {
        if(root == null) {
            return;
        }

        findMax(root.left);

        if(pre == 100001 || pre == root.val) {
            cnt++;
        } else {
            cnt = 1;
        }

        maxFreq = Math.max(maxFreq, cnt);
        pre = root.val;

        findMax(root.right);
    }

    private void findModes(TreeNode root, List<Integer> modes) {
        if(root == null) {
            return;
        }

        findModes(root.left, modes);

        if(pre == 100001 || root.val == pre) {
            cnt++;
        } else {
            cnt = 1;
        }
        if(cnt == maxFreq) {
            modes.add(root.val);
        }

        pre = root.val;

        findModes(root.right, modes);
    }
}
