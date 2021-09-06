package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Operations on Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/operations-on-tree/"
)
public class Q1993 {
    private int n;
    private int[] parent;
    private List<Integer>[] adj;
    private Map<Integer, int[]> map;

    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public Q1993(int[] parent) {
        this.n = parent.length;
        this.parent = parent;
        this.adj = new List[n];
        this.map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        map.put(0, new int[] {0, -1});
        for(int i = 1; i < n; i++) {
            int u = parent[i];
            int v = i;
            map.put(v, new int[] {0, -1});
            adj[u].add(v);
            adj[v].add(u);
        }
    }

    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public boolean lock(int num, int user) {
        int[] node = map.get(num);
        if(node[0] == 0) {
            node[0] = 1;
            node[1] = user;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public boolean unlock(int num, int user) {
        int[] node = map.get(num);
        if(node[0] == 1 && node[1] == user) {
            node[0] = 0;
            node[1] = -1;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean upgrade(int num, int user) {
        int[] node = map.get(num);

        if(node[0] == 1) {
            return false;
        }

        if(!dfsDown(num)) {
            return false;
        }

        if(!dfsUp(num)) {
            return false;
        }

        dfsUnlock(num);

        node[0] = 1;
        node[1] = user;

        return true;
    }

    private boolean dfsDown(int cur) {
        int[] node = map.get(cur);

        if(node[0] == 1) {
            return true;
        }

        for(int nei : adj[cur]) {
            if(nei != parent[cur]) {
                if(dfsDown(nei)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfsUp(int cur) {
        int[] node = map.get(cur);

        if(node[0] == 1) {
            return false;
        }

        for(int nei : adj[cur]) {
            if(nei == parent[cur]) {
                if(!dfsUp(nei)) {
                    return false;
                }
            }
        }

        return true;
    }

    private void dfsUnlock(int cur) {
        int[] node = map.get(cur);
        if(node[0] == 1) {
            node[0] = 0;
            node[1] = -1;
        }

        for(int nei : adj[cur]) {
            if(nei != parent[cur]) {
                dfsUnlock(nei);
            }
        }
    }
}
