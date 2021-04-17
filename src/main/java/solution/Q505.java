package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "The Maze II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/the-maze-ii/"
)
public class Q505 {
    private static final int[][] DIRECTIONS = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        return dijkstraSolution(maze, start, destination);
    }

    private int bfsSolution(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] distance = new int[m][n];
        for(int[] d : distance)
            Arrays.fill(d, -1);
        distance[start[0]][start[1]] = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { start[0], start[1]});

        while(!queue.isEmpty()) {
            int[] pos = queue.poll();
            for(int[] dir : DIRECTIONS) {
                int r = pos[0];
                int c = pos[1];
                int d = 0;
                while(!hitWall(maze, r + dir[0], c + dir[1])) {
                    r += dir[0];
                    c += dir[1];
                    d++;
                }

                d = d + distance[pos[0]][pos[1]];
                if(distance[r][c] == -1 || d < distance[r][c]) {
                    queue.offer(new int[] {r, c});
                    distance[r][c] = d;
                }
            }
        }

        return distance[destination[0]][destination[1]];
    }

    private boolean hitWall(int[][] maze, int r, int c) {
        return r < 0 || c < 0 || r > maze.length - 1 || c > maze[0].length - 1 || maze[r][c] == 1;
    }

    private class Node implements Comparable<Node> {
        int r;
        int c;
        int d;
        Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return d - o.d;
        }
    }
    private int dijkstraSolution(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] distance = new int[m][n];
        for(int[] dist : distance)
            Arrays.fill(dist, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;

        Queue<Node> pQueue = new PriorityQueue<>(); // lambda comparator needs to be loaded and warmed up first, could be slower
        pQueue.offer(new Node(start[0], start[1], 0));
        while(!pQueue.isEmpty()) {
            Node node = pQueue.poll();
            if(node.r == destination[0] && node.c == destination[1])
                return node.d;

            for(int[] dir : DIRECTIONS) {
                int r = node.r;
                int c = node.c;
                int d = node.d;
                while(!hitWall(maze, r + dir[0], c + dir[1])) {
                    r += dir[0];
                    c += dir[1];
                    d++;
                }

                if(d < distance[r][c]) {
                    pQueue.offer(new Node(r, c, d));
                    distance[r][c] = d;
                }
            }
        }

        return -1;
    }
}
