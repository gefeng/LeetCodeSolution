package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Check If Two Expression Trees are Equivalent",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/check-if-two-expression-trees-are-equivalent/"
)
public class Q1612 {
    /**
     * Time:  O(N)
     * Space: O(H)
     * */
    public boolean checkEquivalence(Node root1, Node root2) {
        return Arrays.equals(eva(root1), eva(root2));
    }

    private int[] eva(Node root) {
        int[] ret = new int[26];

        if(root.val != '+') {
            ret[root.val - 'a']++;
            return ret;
        }

        int[] l = eva(root.left);
        int[] r = eva(root.right);

        for(int i = 0; i < 26; i++) {
            ret[i] = l[i] + r[i];
        }
        return ret;
    }

    private class Node {
          char val;
          Node left;
          Node right;
          Node() {this.val = ' ';}
          Node(char val) { this.val = val; }
          Node(char val, Node left, Node right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
    }
}
