package solution;

import data_structure.BinaryTree;
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

    @Test
    public void lowestCommonAncestorTest() {
        TreeNode root = BinaryTree.createBinaryTree();
        new Q236().lowestCommonAncestor(root, root.left, root.right);
    }

    @Test
    public void serializeAndDeserializeBinaryTreeTest() {
//        TreeNode root = BinaryTree.createBinaryTree();
//        String expected = "[1,2,3,4,5]";
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.left.left = new TreeNode(3);
        root.right.left.right = new TreeNode(1);
        //String expected = "[5,2,3,null,null,2,4,3,1]";
        //assertEquals(expected, new Q297().serializeDFS(root));
        System.out.println(new Q297().serializeDFS(root));

        //new Q297().deserialize("[5,2,3,null,null,2,4,3,1]");
    }

    @Test
    public void Q199Test() {
        TreeNode root = BinaryTree.createBinaryTree();
        int[] expected = {1, 3, 5};
        List<Integer> output = new Q199().rightSideViewBFS(root);
        for(int i = 0; i < expected.length; i++)
            assertEquals(expected[i], output.get(i));
    }

    @Test
    public void Q703() {
        Q703 q = new Q703(3, new int[] {4, 5, 8, 2});
        assertEquals(4, q.add(4));
        assertEquals(5, q.add(6));
        assertEquals(6, q.add(10));
    }

    @Test void Q220() {
        //assertEquals(true, new Q220().containsNearbyAlmostDuplicate(new int[] {1,2,3,1}, 3, 0));
        //assertEquals(true, new Q220().containsNearbyAlmostDuplicate(new int[] {1,0,1,1}, 1, 2));
        assertEquals(false, new Q220().containsNearbyAlmostDuplicate(new int[] {1,5,9,1,5,9}, 2, 3));
    }
}
