package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "All Elements in Two Binary Search Trees",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/all-elements-in-two-binary-search-trees/"
)
public class Q1305 {
    /**
     * Time:  O(M + N)
     * Space: O(M + N)
     * */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        inorder(root1, l1);
        inorder(root2, l2);
        return merge(l1, l2);
    }

    private void inorder(TreeNode root, List<Integer> values) {
        if(root == null)
            return;

        inorder(root.left, values);
        values.add(root.val);
        inorder(root.right, values);
    }

    private List<Integer> merge(List<Integer> l1, List<Integer> l2) {
        List<Integer> ans = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i != l1.size() || j != l2.size()) {
            if(i == l1.size())
                ans.add(l2.get(j++));
            else if(j == l2.size())
                ans.add(l1.get(i++));
            else {
                if(l1.get(i) < l2.get(j))
                    ans.add(l1.get(i++));
                else
                    ans.add(l2.get(j++));
            }
        }

        return ans;
    }
}
