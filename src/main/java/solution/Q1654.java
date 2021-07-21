package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

@Problem(
        title = "Minimum Jumps to Reach Home",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/minimum-jumps-to-reach-home/"
)
public class Q1654 {
    /**
     * This problem is tricky, especially to find the termination
     *
     * Time:  O(max(x, max(forbidden)) + a + b)
     * Space: O(max(x, max(forbidden)) + a + b)
     * */
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        int upper = x;
        Set<Integer> f = new HashSet<>();
        for(int pos : forbidden) {
            f.add(pos);
            upper = Math.max(upper, pos);
        }

        upper += (a + b);

        return bfs(a, b, x, upper, f);
    }

    private int bfs(int a, int b, int x, int upper, Set<Integer> f) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[upper + 1][2];

        queue.offer(new int[] {0, 0});
        visited[0][0] = true;

        int min = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] pos = queue.poll();

                if(pos[0] == x) {
                    return min;
                }

                int nf = pos[0] + a;
                int nb = pos[0] - b;

                if(nf <= upper && !f.contains(nf) && !visited[nf][0]) {
                    queue.offer(new int[] {nf, 0});
                    visited[nf][0] = true;
                }
                if(nb >= 0 && pos[1] == 0 && !f.contains(nb) && !visited[nb][1]) {
                    queue.offer(new int[] {nb, 1});
                    visited[nb][1] = true;
                }
            }
            min++;
        }

        return -1;
    }
}
