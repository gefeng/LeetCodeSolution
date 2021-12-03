package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Problem(
        title = "Cat and Mouse",
        difficulty = QDifficulty.HARD,
        tag = QTag.GAME_THEORY,
        url = "https://leetcode.com/problems/cat-and-mouse/"
)
public class Q913 {
    /**
     * Time:  O(N ^ 3)
     * Space: O(N ^ 2)
     * */
    private final int M_WIN = 1, C_WIN = 2, DRAW = 0;
    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        int[][][] color = new int[n][n][2];
        int[][][] degree = new int[n][n][2];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                degree[i][j][0] = graph[i].length;
                degree[i][j][1] = graph[j].length;
                for(int x : graph[j]) {
                    if(x == 0) {
                        degree[i][j][1]--;
                        break;
                    }
                }
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < 2; j++) {
                color[0][i][j] = M_WIN;
                color[i][i][j] = C_WIN;
                q.offer(new int[] {0, i, j, M_WIN});
                q.offer(new int[] {i, i, j, C_WIN});
            }
        }

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int m = cur[0];
            int c = cur[1];
            int t = cur[2];
            int r = cur[3];

            for(int[] pre : preState(graph, cur)) {
                int pm = pre[0];
                int pc = pre[1];
                int pt = pre[2];
                if(color[pm][pc][pt] == DRAW) {
                    if((pt == 0 && r == M_WIN) || (pt == 1 && r == C_WIN)) {
                        color[pm][pc][pt] = r;
                        q.offer(new int[] {pm, pc, pt, r});
                    } else {
                        degree[pm][pc][pt]--;
                        if(degree[pm][pc][pt] == 0) {
                            color[pm][pc][pt] = r;
                            q.offer(new int[] {pm, pc, pt, r});
                        }
                    }
                }
            }
        }

        return color[1][2][0];
    }

    private List<int[]> preState(int[][] g, int[] cur) {
        List<int[]> ret = new ArrayList<>();

        if(cur[2] == 0) {
            for(int x : g[cur[1]]) {
                if(x != 0) {
                    ret.add(new int[] {cur[0], x, 1});
                }
            }
        } else {
            for(int x : g[cur[0]]) {
                ret.add(new int[] {x, cur[1], 0});
            }
        }

        return ret;
    }
}
