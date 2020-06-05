package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Stack;

@Problem(
        title = "Binary Search Tree Iterator",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/binary-search-tree-iterator/"
)
public class Q173 {
    /**
     * Your BSTIterator object will be instantiated and called as such:
     * BSTIterator obj = new BSTIterator(root);
     * int param_1 = obj.next();
     * boolean param_2 = obj.hasNext();
     */
//    private ArrayList<Integer> sortedArray;
//    private int index;
//    public Q173(TreeNode root) {
//        sortedArray = new ArrayList<>();
//        index = 0;
//        dfs(root);
//    }
//
//    private void dfs(TreeNode root) {
//        if (root == null)
//            return;
//        dfs(root.left);
//        sortedArray.add(root.val);
//        dfs(root.right);
//    }

    /** @return the next smallest number */
//    public int next() {
//        return sortedArray.get(index++);
//    }

    /** @return whether we have a next smallest number */
//    public boolean hasNext() {
//        return index < sortedArray.size();
//    }

    /** another approach using stack */
    private Stack<TreeNode> stack = new Stack<>();
    public Q173(TreeNode root) {
        dfsLeft(root);
    }

    private void dfsLeft(TreeNode root) {
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** 这种解法的next看似每次要把所有左子节点全部push到stack，
     *  但分析一下平均的time complexity是O(n)/n所有平均是O(1),
     *  而且相比之下space complexity稍微优化为O(h),
     *  worst case是O(n)*/
    public int next() {
        TreeNode curr = stack.pop();
        int retVal = curr.val;
        dfsLeft(curr.right);
        return retVal;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
