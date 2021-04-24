package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Design File System",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/design-file-system/"
)
public class Q1166 {
    private class Node {
        String name;
        int val;
        Map<String, Node> subDir;
        Node(String name, int val) {
            this.name = name;
            this.val = val;
            this.subDir = new HashMap<>();
        }
    }

    private Node root;
    public Q1166() {
        root = new Node("", 0);
    }

    /*
        -1 if path already exists or parent path doesn't exist
    */
    public boolean createPath(String path, int value) {
        Node curr = root;
        String[] s = path.split("/");

        for(int i = 1; i < s.length; i++) {
            Node sub = curr.subDir.get(s[i]);
            if(i != s.length - 1) {
                if(sub == null)
                    return false;
                curr = sub;
            } else {
                if(sub != null)
                    return false;
                curr.subDir.put(s[i], new Node(s[i], value));
            }
        }

        return true;
    }

    public int get(String path) {
        Node curr = root;
        String[] s = path.split("/");

        for(int i = 1; i < s.length; i++) {
            Node sub = curr.subDir.get(s[i]);
            if(sub == null)
                return -1;
            curr = sub;
        }

        return curr.val;
    }
}
