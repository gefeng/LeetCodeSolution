package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Construct Binary Tree from Inorder and Postorder Traversal",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/"
)
public class Q106 {
    /*这题和105一样用hashmap可以减少lookup时间*/
    private TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int poIndex) {
        if(inStart > inEnd)
            return null;

        TreeNode root = new TreeNode(postorder[poIndex]);

        int inMiddle = 0;
        for(int i = inStart; i < inEnd + 1; i++) {
            if(postorder[poIndex] == inorder[i]) {
                inMiddle = i;
                break;
            }
        }

        root.left = build(inorder, inStart, inMiddle - 1, postorder, poIndex - (inEnd - inMiddle + 1));
        root.right = build(inorder, inMiddle + 1, inEnd, postorder, poIndex - 1);

        return root;

    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, 0, inorder.length - 1, postorder, postorder.length - 1);
    }
}
