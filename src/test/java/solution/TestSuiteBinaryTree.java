package solution;

import data_structure.BinaryTree;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import data_structure.TreeNode;

import java.util.List;

public class TestSuiteBinaryTree {
    @Test
    public void preorderTraversalTest() {
        TreeNode root = BinaryTree.createBinaryTree();
        int[] expected = {1, 2, 4, 5, 3};
        List<Integer> output = BinaryTree.preorderTraversalRecursive(root);
        for(int i = 0; i < expected.length; i++)
            assertEquals(expected[i], output.get(i));

        output = BinaryTree.preorderTraversalIterative(root);
        for(int i = 0; i < expected.length; i++)
            assertEquals(expected[i], output.get(i));

        output = BinaryTree.preorderTraversalMorris(root);
        for(int i = 0; i < expected.length; i++)
            assertEquals(expected[i], output.get(i));
    }

    @Test
    public void inorderTraversalTest() {
        TreeNode root = BinaryTree.createBinaryTree();
        int[] expected = {4, 2, 5, 1, 3};
        List<Integer> output = BinaryTree.inorderTraversalRecursive(root);
        for(int i = 0; i < expected.length; i++)
            assertEquals(expected[i], output.get(i));

        output = BinaryTree.inorderTraversalIterative(root);
        for(int i = 0; i < expected.length; i++)
            assertEquals(expected[i], output.get(i));

        output = BinaryTree.inorderTraversalMorris(root);
        for(int i = 0; i < expected.length; i++)
            assertEquals(expected[i], output.get(i));
    }

    @Test
    public void postorderTraversalTest() {
        TreeNode root = BinaryTree.createBinaryTree();
        int[] expected = {4, 5, 2, 3, 1};
        List<Integer> output = BinaryTree.postorderTraversalRecursive(root);
        for(int i = 0; i < expected.length; i++)
            assertEquals(expected[i], output.get(i));

        output = BinaryTree.postorderTraversalReverse(root);
        for(int i = 0; i < expected.length; i++)
            assertEquals(expected[i], output.get(i));

        output = BinaryTree.postorderTraversalIterative(root);
        for(int i = 0; i < expected.length; i++)
            assertEquals(expected[i], output.get(i));
    }

    @Test
    public void levelorderTraversalTest() {
        TreeNode root = BinaryTree.createBinaryTree();
        int[][] expected = {{1}, {2,3}, {4,5}};
        List<List<Integer>> output = BinaryTree.levelOrderTraversal(root);
        for(int i = 0; i < output.size(); i++) {
            for(int j = 0; j < output.get(i).size(); j++) {
                assertEquals(expected[i][j], output.get(i).get(j));
            }
        }
    }

    @Test
    public void maximumDepthTest() {
        TreeNode root = BinaryTree.createBinaryTree();
        int expected = 3;
        int output = new Q104().maxDepthRecursive(root);
        assertEquals(expected, output);

        output = new Q104().maxDepthIterative(root);
        assertEquals(expected, output);
    }

    @Test
    public void hasPathSum() {
        TreeNode root = BinaryTree.createBinaryTree();
        assertTrue(new Q112().hasPathSumIterative(root, 8));
    }

    @Test
    public void findUnivalSubtreesTest() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        int expected = 1;
        assertEquals(expected, new Q250().countUnivalSubtrees(root));
    }
}
