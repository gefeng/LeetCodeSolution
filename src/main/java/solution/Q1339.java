package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Maximum Product of Splitted Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/"
)
public class Q1339 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int maxProduct(TreeNode root) {
        List<Integer> sum = new ArrayList<>();
        int rootSum = getSum(root, sum);
        long max = 0;

        int close = 0;
        int minDiff = Integer.MAX_VALUE;
        for(int s : sum) {
            if(s == rootSum) {
                continue;
            }
            int diff = Math.abs(rootSum - s * 2);
            if(diff < minDiff) {
                minDiff = diff;
                close = s;
            }
        }

        // using integer only and avoid overflow
        return modMul(close, rootSum - close);
    }

    private int getSum(TreeNode root, List<Integer> sum) {
        if(root == null) {
            return 0;
        }

        int l = getSum(root.left, sum);
        int r = getSum(root.right, sum);

        sum.add(l + r + root.val);

        return l + r + root.val;
    }

    /**
     * similar to fast power 
     * */
    private int modMul(int x, int y) {
        int res = 0;
        while(y != 0) {
            if(y % 2 == 1) {
                res = (res + x) % MOD;
            }

            y /= 2;
            x = (x * 2) % MOD;
        }
        return res;
    }
}
