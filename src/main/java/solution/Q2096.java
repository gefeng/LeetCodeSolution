package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Step-By-Step Directions From a Binary Tree Node to Another",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/"
)
public class Q2096 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String getDirections(TreeNode root, int startValue, int destValue) {
        List<Character> p1 = new ArrayList<>();
        List<Character> p2 = new ArrayList<>();
        dfs(root, startValue, p1);
        dfs(root, destValue, p2);

        int i = p1.size() - 1;
        int j = p2.size() - 1;
        for(; i >= 0 && j >= 0; i--, j--) {
            if(p1.get(i) != p2.get(j)) {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(; i >= 0; i--) {
            sb.append('U');
        }
        for(; j >= 0; j--) {
            sb.append(p2.get(j));
        }

        return sb.toString();
    }

    private boolean dfs(TreeNode root, int x, List<Character> p) {
        if(root == null) {
            return false;
        }

        if(root.val == x) {
            return true;
        }

        boolean l = dfs(root.left, x, p);
        boolean r = dfs(root.right, x, p);

        if(l) {
            p.add('L');
        }
        if(r) {
            p.add('R');
        }

        return l || r;
    }
}
