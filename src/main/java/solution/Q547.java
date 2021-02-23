package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Friend Circles",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/friend-circles/"
)
public class Q547 {
    public int findCircleNumDFS(int[][] M) {
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for(int i = 0; i < M.length; i++) {
            if(!visited.contains(i)) {
                dfs(M, i, visited);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] M, int i, Set<Integer> visited) {
        for(int j = 0; j < M.length; j++) {
            if(M[i][j] == 1 && !visited.contains(j)) {
                visited.add(j);
                dfs(M, j, visited);
            }
        }
    }

    public int findCircleNumBFS(int[][] M) {
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < M.length; i++) {
            if(!visited.contains(i)) {
                queue.offer(i);
                while(!queue.isEmpty()) {
                    int v = queue.poll();
                    visited.add(v);
                    for(int j = 0; j < M.length; j++) {
                        if(!visited.contains(j) && M[v][j] == 1)
                            queue.offer(j);
                    }
                }
                count++;
            }
        }
        return count;
    }

    public int findCircleNumUnionFind(int[][] M) {
        int count = 0;
        int n = M.length;
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i != j && M[i][j] == 1)
                    union(parent, i, j);
            }
        }

        for(int i = 0; i < n; i++) {
            if(parent[i] == -1)
                count++;
        }
        return count;
    }

    private void union(int[] parent, int setX, int setY) {
        int headX = find(parent, setX);
        int headY = find(parent, setY);
        if(headX != headY)
            parent[headX] = headY;
    }

    private int find(int[] parent, int set) {
        if(parent[set] == -1)
            return set;
        int head = find(parent, parent[set]);
        parent[set] = head; // path compression
        return head;
    }
}
