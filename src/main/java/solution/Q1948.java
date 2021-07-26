package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Delete Duplicate Folders in System",
        difficulty = QDifficulty.HARD,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/delete-duplicate-folders-in-system/"
)
public class Q1948 {
    /**
     * N: number of folders
     * M: length of folder name
     *
     * Time:  O(N * M + N * N * M + N * M)
     * Space: O(N * N * M)
     * */
    private class Node {
        Map<String, Node> children = new HashMap<>();
        String name = "";
        boolean del = false;
        Node(String name) {
            this.name = name;
        }
    }

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        List<List<String>> res = new ArrayList<>();
        Node root = new Node("/");

        for(List<String> path : paths) {
            add(root, path);
        }

        dedupe(root, new HashMap<>());

        generate(root, new ArrayList<>(), res);

        return res;
    }

    private void add(Node root, List<String> path) {
        Node curr = root;
        for(String p : path) {
            curr = curr.children.computeIfAbsent(p, k -> new Node(p));
        }
    }

    private String dedupe(Node root, Map<String, Node> seen) {
        StringBuilder tb = new StringBuilder();
        StringBuilder stb = new StringBuilder();

        tb.append("(").append(root.name);

        for(String s : root.children.keySet()) {
            stb.append(dedupe(root.children.get(s), seen));
        }

        String subtree = stb.toString();

        if(!subtree.isEmpty()) {
            if(seen.containsKey(subtree)) {
                seen.get(subtree).del = true;
                root.del = true;
            } else {
                seen.put(subtree, root);
            }
        }

        return tb.append(subtree).append(")").toString();
    }

    private void generate(Node root, List<String> path, List<List<String>> res) {
        path.add(root.name);

        if(!root.del) {
            if(path.size() > 1) {
                res.add(new ArrayList<>(path.subList(1, path.size())));
            }

            for(String sub : root.children.keySet()) {
                generate(root.children.get(sub), path, res);
            }
        }

        path.remove(root.name);
    }
}
