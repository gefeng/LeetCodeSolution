package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Clone Graph",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/clone-graph/"
)
public class Q133 {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**这题的time complexity乍一看是O(n)。
     * 但是仔细分析，其实如果一个节点被多个父节点共享，那该节点就会被多次访问并且检查是否在hashmap里
     * 所以time complexity实际是O(n+m) where m = number of edges**/
    private Node cloneNode(Node node, HashMap<Integer, Node> created) {
        if(node == null)
            return null;

        if(created.containsKey(node.val))
            return created.get(node.val);

        Node copy = new Node(node.val);
        created.put(copy.val, copy);
        for(Node n : node.neighbors) {
            copy.neighbors.add(cloneNode(n, created));
        }
        return copy;
    }
    public Node cloneGraph(Node node) {
        HashMap<Integer, Node> created = new HashMap<>();
        return cloneNode(node, created);
    }
}
