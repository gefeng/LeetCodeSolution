package data_structure;

import java.util.*;

public class BinaryTree {
    /*Construct*/
    public static TreeNode createBinaryTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        return root;
    }

    /*********************************************************************
                                preorder
     *********************************************************************/

    /*Recursion*/
    private static void preorder(TreeNode node, List<Integer> output) {
        if(node == null)
            return;
        output.add(node.val);
        preorder(node.left, output);
        preorder(node.right, output);

    }
    public static List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> values = new LinkedList<>();
        preorder(root, values);
        return values;
    }

    /*Iteration*/
    public static List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> values = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node == null) continue;
            values.add(node.val);
            stack.push(node.right);
            stack.push(node.left);
        }
        return values;
    }

    /*Morris*/
    public static List<Integer> preorderTraversalMorris(TreeNode root) {
        List<Integer> values = new LinkedList<>();
        TreeNode node = root;
        TreeNode predecessor;
        while(node != null) {
            if(node.left != null) {
                predecessor = node.left;
                while(predecessor.right != null && predecessor.right != node) {
                    predecessor = predecessor.right;
                }

                if(predecessor.right == node) {
                    predecessor.right = null;
                    node = node.right;
                } else {
                    values.add(node.val);
                    predecessor.right = node;
                    node = node.left;
                }
            } else {
                values.add(node.val);
                node = node.right;
            }
        }
        return values;
    }

    /*********************************************************************
                                inorder
     *********************************************************************/

    private static void inorder(TreeNode node, List<Integer> output) {
        if(node == null)
            return;
        inorder(node.left, output);
        output.add(node.val);
        inorder(node.right, output);
    }
    public static List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> output = new LinkedList();
        inorder(root, output);
        return output;
    }

    public static List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> output = new LinkedList();
        Stack<TreeNode> stack = new Stack();

        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            output.add(curr.val);
            curr = curr.right;
        }

        return output;
    }

    public static List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> output = new LinkedList();
        TreeNode curr = root;
        TreeNode predecessor;

        while(curr != null) {
            if(curr.left != null) {
                predecessor = curr.left;
                while(predecessor.right != null && predecessor.right != curr) {
                    predecessor = predecessor.right;
                }
                if(predecessor.right == curr) {
                    predecessor.right = null;
                    output.add(curr.val);
                    curr = curr.right;
                } else {
                    predecessor.right = curr;
                    curr = curr.left;
                }
            } else {
                output.add(curr.val);
                curr = curr.right;
            }
        }

        return output;
    }


    /*********************************************************************
                                postorder
     *********************************************************************/

    private static void postorder(TreeNode node, List<Integer> output) {
        if(node == null)
            return;
        postorder(node.left, output);
        postorder(node.right, output);
        output.add(node.val);
    }
    public static List<Integer> postorderTraversalRecursive(TreeNode root) {
        List<Integer> output = new ArrayList<>();
        postorder(root, output);
        return output;
    }

    public static List<Integer> postorderTraversalReverse(TreeNode root) {
        List<Integer> output = new LinkedList<>();
        if(root == null)
            return output;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            output.add(0, curr.val);
            if(curr.left != null)
                stack.push(curr.left);
            if(curr.right != null)
                stack.push(curr.right);
        }

        return output;
    }

    public static List<Integer> postorderTraversalIterative(TreeNode root) {
        List<Integer> output = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(true) {
            if(curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                if(stack.isEmpty())
                    break;
                curr = stack.peek().right;
                if(curr == null) {
                    TreeNode prev = null;
                    while(!stack.isEmpty() && prev == stack.peek().right) {
                        prev = stack.pop();
                        output.add(prev.val);
                    }
                }
            }
        }

        return output;
    }

    /*********************************************************************
                                 levelorder
     *********************************************************************/
    public static List<List<Integer>> levelOrderTraversal(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        if(root == null)
            return output;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int levelAmt = queue.size();
            List<Integer> values = new ArrayList<>();
            for(int i = 0; i < levelAmt; i++) {
                TreeNode curr = queue.poll();
                values.add(curr.val);
                if(curr.left != null)
                    queue.add(curr.left);
                if(curr.right != null)
                    queue.add(curr.right);
            }
            output.add(values);
        }

        return output;
    }
}
