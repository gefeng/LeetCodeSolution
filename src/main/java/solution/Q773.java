package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Sliding Puzzle",
        difficulty = QDifficulty.HARD,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/sliding-puzzle/"
)
public class Q773 {
    /**
     * Time:  O(6!)
     * Space: O(6!)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int slidingPuzzle(int[][] board) {
        Queue<int[][]> q = new ArrayDeque<>();
        Set<Integer> seen = new HashSet<>();

        q.offer(board);
        seen.add(hash(board));

        int steps = 0;
        while(!q.isEmpty()) {
            int sz = q.size();
            for(int l = 0; l < sz; l++) {
                int[][] cur = q.poll();

                if(win(cur)) {
                    return steps;
                }

                for(int i = 0; i < 2; i++) {
                    for(int j = 0; j < 3; j++) {
                        if(cur[i][j] == 0) {
                            for(int[] dir : DIRECTIONS) {
                                int ni = i + dir[0];
                                int nj = j + dir[1];
                                if(ni >= 0 && nj >= 0 && ni < 2 && nj < 3) {
                                    int[][] next = new int[2][3];
                                    for(int k = 0; k < 2; k++) {
                                        next[k] = Arrays.copyOf(cur[k], 3);
                                    }
                                    next[i][j] = cur[ni][nj];
                                    next[ni][nj] = 0;

                                    int hash = hash(next);
                                    if(!seen.contains(hash)) {
                                        q.offer(next);
                                        seen.add(hash);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            steps++;
        }

        return -1;
    }

    private boolean win(int[][] board) {
        for(int i = 0; i < 3; i++) {
            if(board[0][i] != i + 1) return false;
        }

        for(int i = 0; i < 2; i++) {
            if(board[1][i] != 4 + i) return false;
        }

        return true;
    }

    private int hash(int[][] board) {
        int base = 1;
        int hash = 0;
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 3; j++) {
                hash += base * board[i][j];
                base *= 10;
            }
        }
        return hash;
    }
}
