package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

@Problem(
        title = "Escape a Large Maze",
        difficulty = QDifficulty.HARD,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/escape-a-large-maze/"
)
public class Q1036 {
    /**
     * The maze is huge so instead of exploring the whole maze we can see if we can "escape" from source and target.
     * I mean "escape" is since blocked celling are up to 200, we just need to reach a point over 200 unit from the start
     * point to lead to a "escape".
     *
     * Time:  O(200 * 200)
     * Space: O(200 * 200)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final long N = (long)1e6;
    private static final int D = 201;
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<Long> set = new HashSet<>();
        for(int[] b : blocked) {
            set.add(b[0] * N + b[1]);
        }

        boolean[] res1 = bfs(source[0], source[1], target[0], target[1], set);
        boolean[] res2 = bfs(target[0], target[1], source[0], source[1], set);

        if(res1[0]) {
            return true;
        }
        return res1[1] && res2[1];
    }

    private boolean[] bfs(int sr, int sc, int tr, int tc, Set<Long> blocked) {
        Queue<int[]> queue = new ArrayDeque<>();
        Set<Long> seen = new HashSet<>();

        queue.offer(new int[] {sr, sc});
        seen.add(sr * N + sc);

        int steps = 0;
        while(!queue.isEmpty()) {
            int sz = queue.size();
            for(int i = 0; i < sz; i++) {
                int[] cur = queue.poll();

                if(cur[0] == tr && cur[1] == tc) {
                    return new boolean[] {true, true};
                }

                for(int[] dir : DIRECTIONS) {
                    int nr = cur[0] + dir[0];
                    int nc = cur[1] + dir[1];
                    long map = nr * N + nc;
                    if(nr >= 0 && nc >= 0 && nr < N && nc < N && !seen.contains(map) && !blocked.contains(map)) {
                        queue.offer(new int[] {nr, nc});
                        seen.add(map);
                    }
                }
            }

            steps++;
            if(steps > D) {
                break;
            }
        }

        return new boolean[] {false, steps > D};
    }
}
