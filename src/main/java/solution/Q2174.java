package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Remove All Ones With Row and Column Flips II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/remove-all-ones-with-row-and-column-flips-ii/"
)
public class Q2174 {
    /**
     * Time:  O(2 ^ (M * N) * M * N)
     * Space: O(2 ^ (M * N))
     * */
    public int removeOnes(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] flip = new int[m][n];

        // pre-calculate flipped mask
        int one = (1 << m * n) - 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int mask = one;
                for(int r = 0; r < m; r++) {
                    int k = r * n + j;
                    mask ^= (1 << k);
                }

                for(int c = 0; c < n; c++) {
                    int k = i * n + c;
                    mask ^= (1 << k);
                }

                mask ^= (1 << i * n + j);
                flip[i][j] = mask;
            }
        }

        int init = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    init |= (1 << i * n + j);
                }
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] seen = new boolean[1 << m * n];

        q.offer(init);
        seen[init] = true;

        int steps = 0;
        while(!q.isEmpty()) {
            int sz = q.size();
            for(int i = 0; i < sz; i++) {
                int cur = q.poll();

                if(cur == 0) {
                    return steps;
                }

                for(int r = 0; r < m; r++) {
                    for(int c = 0; c < n; c++) {
                        int k = r * n + c;
                        if((cur & (1 << k)) != 0) {
                            int nxt = cur & flip[r][c];
                            if(!seen[nxt]) {
                                seen[nxt] = true;
                                q.offer(nxt);
                            }
                        }
                    }
                }
            }
            steps++;
        }

        return -1;
    }
}
