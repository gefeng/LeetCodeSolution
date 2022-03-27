package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "The Maze III",
        difficulty = QDifficulty.HARD,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/the-maze-iii/"
)
public class Q499 {
    /**
     * Time:  O((M * N) ^ 2 * log((M * N) ^ 2))
     * Space: O(M * N * M * N)
     * */
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length;
        int n = maze[0].length;
        String ans = "";
        int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        char[] instructions = new char[] {'u', 'd', 'l', 'r'};

        Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.d));
        int[][] best = new int[m][n];

        for(int i = 0; i < m; i++) {
            Arrays.fill(best[i], Integer.MAX_VALUE);
        }

        pq.offer(new Node(ball[0], ball[1], 0, new StringBuilder()));
        best[ball[0]][ball[1]] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(cur.r == hole[0] && cur.c == hole[1]) {
                if(ans.isEmpty()) {
                    ans = cur.path.toString();
                } else {
                    String cand = cur.path.toString();
                    if(cand.compareTo(ans) < 0) {
                        ans = cand;
                    }
                }

                continue;
            }

            if(best[cur.r][cur.c] < cur.d) continue;

            for(int i = 0; i < 4; i++) {
                int[] dir = directions[i];
                int nr = cur.r;
                int nc = cur.c;
                int nd = cur.d;
                while(isValid(maze, nr + dir[0], nc + dir[1])) {
                    nr += dir[0];
                    nc += dir[1];
                    nd++;
                    if(nr == hole[0] && nc == hole[1]) {
                        break;
                    }
                }

                if((nr != cur.r || nc != cur.c) && best[nr][nc] >= nd) {
                    StringBuilder npath = new StringBuilder(cur.path).append(instructions[i]);

                    pq.offer(new Node(nr, nc, nd, npath));

                    best[nr][nc] = nd;
                }
            }
        }

        return ans.isEmpty() ? "impossible" : ans;
    }

    private boolean isValid(int[][] maze, int r, int c) {
        int m = maze.length;
        int n = maze[0].length;
        return r >= 0 && c >= 0 && r < m && c < n && maze[r][c] != 1;
    }

    private class Node {
        int r;
        int c;
        int d;
        StringBuilder path;

        Node(int r, int c, int d, StringBuilder path) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.path = path;
        }
    }
}
