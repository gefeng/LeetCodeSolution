package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;

@Problem(
        title = "Kth Largest Element in a Stream",
        difficulty = QDifficulty.EASY,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/kth-largest-element-in-a-stream/"
)
public class Q703 {
    PriorityQueue<Integer> minHeap;
    int size;
    public Q703(int k, int[] nums) {
        minHeap = new PriorityQueue<>();
        size = k;
        for(int n : nums) {
            minHeap.offer(n);
            if(minHeap.size() > size)
                minHeap.poll();
        }
    }

    public int add(int val) {
        minHeap.offer(val);
        if(minHeap.size() > size)
            minHeap.poll();
        return minHeap.peek();
    }
    /*class TreeNode {
        int val, cnt;
        TreeNode left, right;
        TreeNode(int val, int cnt) { this.val = val; this.cnt = 1; }
    }
    private int k;
    TreeNode root;

    public Q703(int k, int[] nums) {
        this.k = k;
        for(int n : nums)
            root = insert(root, n);
    }

    public int add(int val) {
        root = insert(root, val);
        return searchKth(root, k).val;
    }

    private TreeNode insert(TreeNode root, int val) {
        if(root == null)
            return new TreeNode(val,  1);

        if(val <= root.val)
            root.left = insert(root.left, val);
        else
            root.right = insert(root.right, val);

        root.cnt++;
        return root;
    }

    private TreeNode searchKth(TreeNode root, int k) {
        int rootKth = (root.right == null ? 0 : root.right.cnt) + 1;
        if(rootKth == k)
            return root;
        if(rootKth > k)
            return searchKth(root.right, k);
        else
            return searchKth(root.left, k - rootKth);
    }*/
}
