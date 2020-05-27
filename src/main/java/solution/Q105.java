package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;

@Problem(
        title = "Construct Binary Tree from Preorder and Inorder Traversal",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/"
)
public class Q105 {
    /*这题用hashmap可以空间换时间,减少lookup time to O(1)*/
    private TreeNode build(int[] preorder, int preIndex, int[] inorder, int inStart, int inEnd) {
        if(preIndex > preorder.length - 1 || inEnd < inStart)
            return null;

        TreeNode root = new TreeNode(preorder[preIndex]);

        int inMiddle = 0;
        for(int i = inStart; i < inEnd + 1; i++) {
            if(preorder[preIndex] == inorder[i]) {
                inMiddle = i;
                break;
            }
        }

        root.left = build(preorder, preIndex + 1, inorder, inStart, inMiddle - 1);
        root.right = build(preorder, preIndex + inMiddle - inStart + 1, inorder, inMiddle + 1, inEnd);

        return root;
    }
    private TreeNode buildOptimized(int[] preorder, int preIndex, HashMap<Integer, Integer> inorderMap, int inStart, int inEnd) {
        if(inStart > inEnd)
            return null;

        TreeNode root = new TreeNode(preorder[preIndex]);

        int inMiddle = inorderMap.get(preorder[preIndex]);

        root.left = buildOptimized(preorder, preIndex + 1, inorderMap, inStart, inMiddle - 1);
        root.right = buildOptimized(preorder, preIndex + (inMiddle - inStart + 1), inorderMap, inMiddle + 1, inEnd);

        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> inorderMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++)
            inorderMap.put(inorder[i], i);

        return buildOptimized(preorder, 0, inorderMap, 0, inorder.length - 1);
    }
}
