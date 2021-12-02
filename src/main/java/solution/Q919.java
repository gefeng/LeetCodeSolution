package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Problem(
        title = "Complete Binary Tree Inserter",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/complete-binary-tree-inserter/"
)
public class Q919 {
    TreeNode root;
    List<TreeNode> lv;
    int pos;

    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public Q919(TreeNode root) {
        this.root = root;
        setLast();
        setPos();
    }

    /**
     * Time:  O(1)
     * */
    public int insert(int val) {
        TreeNode p = lv.get(pos);
        if(p.left == null) {
            p.left = new TreeNode(val);
        } else {
            p.right = new TreeNode(val);
            pos++;
            if(pos == lv.size()) {
                List<TreeNode> nLv = new ArrayList<>();
                for(TreeNode x : lv) {
                    nLv.add(x.left);
                    nLv.add(x.right);
                }
                lv = nLv;
                pos = 0;
            }
        }

        return p.val;
    }

    /**
     * Time:  O(1)
     * */
    public TreeNode get_root() {
        return root;
    }

    private void setLast() {
        lv = null;

        boolean isLast = false;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()) {
            List<TreeNode> cur = new ArrayList<>();
            int sz = q.size();

            for(int i = 0; i < sz; i++) {
                TreeNode x = q.poll();
                cur.add(x);

                if(x.left != null) {
                    q.offer(x.left);
                } else {
                    if(!isLast) {
                        isLast = true;
                    }
                }

                if(x.right != null) {
                    q.offer(x.right);
                } else {
                    if(!isLast) {
                        isLast = true;
                    }
                }
            }

            if(isLast && lv == null) {
                lv = cur;
            }
        }
    }

    private void setPos() {
        pos = 0;
        int sz = lv.size();
        for(int i = 0; i < sz; i++) {
            if(lv.get(i).left == null) {
                pos = i;
                break;
            }
            if(lv.get(i).right == null) {
                pos = i;
                break;
            }
        }
    }
}
