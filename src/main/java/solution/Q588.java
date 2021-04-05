package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Problem(
        title = "Design In-Memory File System",
        difficulty = QDifficulty.HARD,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/design-in-memory-file-system/"
)
public class Q588 {
    private class Node {
        String name = null;
        String content = null;
        TreeMap<String, Node> children = new TreeMap<>();
    }

    private static final String ROOT = "/";
    private Node root;
    public Q588() {
        root = new Node();
        root.name = ROOT;
    }

    public List<String> ls(String path) {
        List<String> ret = new ArrayList<>();
        Node node = search(path);
        if(node.content == null) {
            for(String key : node.children.keySet())
                ret.add(key);
        } else
            ret.add(node.name);

        return ret;
    }

    public void mkdir(String path) {
        add(path);
    }

    public void addContentToFile(String filePath, String content) {
        Node node = add(filePath);
        if(node.content != null)
            node.content += content;
        else
            node.content = content;
    }

    public String readContentFromFile(String filePath) {
        Node node = search(filePath);
        return node.content;
    }

    private Node search(String path) {
        String[] names = path.split("/");
        Node curr = root;
        for(int i = 1; i < names.length; i++) {
            curr = curr.children.get(names[i]);
        }

        return curr;
    }

    private Node add(String path) {
        String[] names = path.split("/");
        Node curr = root;
        for(int i = 1; i < names.length; i++) {
            String name = names[i];
            curr.children.putIfAbsent(name, new Node());
            curr = curr.children.get(name);
            curr.name = name;
        }

        return curr;
    }
}
