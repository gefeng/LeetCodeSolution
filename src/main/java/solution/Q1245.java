package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Tree Diameter",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/tree-diameter/"
)
public class Q1245 {
    /*
    * 泛化的求tree的diameter.
    * 给定一个node，找到最长和第二长的两条以当前node为root的path，两者和为可能的最大直径.
    * */
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int treeDiameter(int[][] edges) {
        if(edges.length == 0) {
            return 0;
        }

        int n = edges.length + 1;
        List<Integer>[] g = new List[n];

        // build adjacency list
        for(int[] e : edges) {
            if(g[e[0]] == null) {
                g[e[0]] = new ArrayList<>();
            }
            if(g[e[1]] == null) {
                g[e[1]] = new ArrayList<>();
            }

            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }

        return getDiameter(g, -1, 0)[0];
    }

    // 0: diameter 1: max height
    private int[] getDiameter(List<Integer>[] g, int parent, int currNode) {
        int max1 = 0;
        int max2 = 0;
        int maxD = 0;
        for(int nei : g[currNode]) {
            if(parent == nei) {
                continue;
            }
            int[] info = getDiameter(g, currNode, nei);

            if(info[1] > max1) {
                max2 = max1;
                max1 = info[1];
            } else if(info[1] > max2) {
                max2 = info[1];
            }

            maxD = Math.max(maxD, info[0]);
        }

        maxD = Math.max(maxD, max1 + max2);

        return new int[] {maxD, max1 + 1};
    }
}
