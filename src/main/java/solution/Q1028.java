package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Recover a Tree From Preorder Traversal",
        difficulty = QDifficulty.HARD,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/"
)
public class Q1028 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public TreeNode recoverFromPreorder(String traversal) {
        char[] s = traversal.toCharArray();
        int n = s.length;
        int i = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();

        int preD = -1;
        int curD = 0;
        int curV = 0;
        while(i < n) {
            while(readDepth(s, i) == preD + 1) {
                curD = 0;
                while(i < n && s[i] == '-') {
                    curD++;
                    i++;
                }

                curV = 0;
                while(i < n && s[i] != '-') {
                    curV = curV * 10 + s[i] - '0';
                    i++;
                }

                TreeNode x = new TreeNode(curV);
                if(!stack.isEmpty()) {
                    stack.peek().left = x;
                }

                stack.push(x);
                preD = curD;
            }

            if(i == n) {
                break;
            }

            curD = 0;
            while(i < n && s[i] == '-') {
                curD++;
                i++;
            }

            curV = 0;
            while(i < n && s[i] != '-') {
                curV = curV * 10 + s[i] - '0';
                i++;
            }

            TreeNode x = new TreeNode(curV);

            int cnt = Math.abs(curD - preD) + 1;


            while(!stack.isEmpty() && cnt != 0) {
                stack.pop();
                cnt--;
            }

            if(!stack.isEmpty()) {
                stack.peek().right = x;
            }

            stack.push(x);
            preD = curD;
        }

        TreeNode root = null;
        while(!stack.isEmpty()) {
            root = stack.pop();
        }

        return root;
    }

    private int readDepth(char[] s, int start) {
        int i = start;
        int d = 0;
        while(i < s.length && s[i] == '-') {
            d++;
            i++;
        }

        return d;
    }
}
