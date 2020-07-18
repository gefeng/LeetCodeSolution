package data_structure;

public class TreeBucket implements Bucket {
    private TreeNode root;

    public TreeBucket() {
    }

    public void add(int key) {
        root = insert(root, key);
    }

    public void remove(int key) {
        root = delete(root, key);
    }

    public boolean contains(int key) {
        return search(root, key) != null;
    }

    private TreeNode insert(TreeNode root, int val) {
        if(root == null)
            return new TreeNode(val);

        if(val == root.val)
            return root;
        else if(val < root.val)
            root.left = insert(root.left, val);
        else
            root.right = insert(root.right, val);

        return root;
    }

    private TreeNode delete(TreeNode root, int val) {
        if(root == null)
            return null;

        if(root.val == val) {
            if(root.left == null)
                return root.right;
            else if(root.right == null)
                return root.left;
            else {
                TreeNode newRoot = root.right;
                while(newRoot.left != null) {
                    newRoot = newRoot.left;
                }
                root.val = newRoot.val;
                root.right = delete(root.right, newRoot.val);
            }
        }
        else if(val < root.val)
            root.left = delete(root.left, val);
        else
            root.right = delete(root.right, val);

        return root;
    }

    private TreeNode search(TreeNode root, int val) {
        if(root == null || root.val == val)
            return root;

        return val < root.val ? search(root.left, val) : search(root.right, val);
    }
}
