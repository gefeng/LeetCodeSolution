package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Grid Happiness",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximize-grid-happiness/"
)
public class Q1659 {
    /**
     * dfs + memo
     * use bit mask to memo previous n cells' states
     * The left most 2 bits represent the state of up cell
     * The right most 2 bits represent the state of left cell
     * 00: empty
     * 01: introvert
     * 02: extrovert
     *
     * Time:  O(m * n * in * ex * 2 ^ n)
     * Space: O(m * n * in * ex * 2 ^ n)
     * */
    private int m;
    private int n;
    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        this.m = m;
        this.n = n;

        return dfs(0, 0, introvertsCount, extrovertsCount, 0,
                new Integer[m][n][introvertsCount + 1][extrovertsCount + 1][1 << (2 * n)]);
    }

    private int dfs(int r, int c, int in, int ex, int prevN, Integer[][][][][] memo) {
        if(c == n) {
            r++;
            c = 0;
        }

        if(r == m || (in == 0 && ex == 0)) {
            return 0;
        }

        if(memo[r][c][in][ex][prevN] != null) {
            return memo[r][c][in][ex][prevN];
        }

        int max = dfs(r, c + 1, in, ex, set(prevN, 0), memo);

        int left = getLeft(prevN);
        int up = getUp(prevN);
        //System.out.println(left + " " + up);

        if(in > 0) {
            int score = 120;

            if(c > 0 && left != 0) {
                score -= 30;
                if(left == 1) {
                    score -= 30;
                } else {
                    score += 20;
                }
            }

            if(r > 0 && up != 0) {
                score -= 30;
                if(up == 1) {
                    score -= 30;
                } else {
                    score += 20;
                }
            }

            max = Math.max(max, dfs(r, c + 1, in - 1, ex, set(prevN, 1), memo) + score);
        }
        if(ex > 0) {
            int score = 40;

            if(c > 0 && left != 0) {
                score += 20;
                if(left == 1) {
                    score -= 30;
                } else {
                    score += 20;
                }
            }

            if(r > 0 && up != 0) {
                score += 20;
                if(up == 1) {
                    score -= 30;
                } else {
                    score += 20;
                }
            }

            max = Math.max(max, dfs(r, c + 1, in, ex - 1, set(prevN, 2), memo) + score);
        }

        return memo[r][c][in][ex][prevN] = max;
    }

    private int set(int mask, int val) {
        mask = (mask << 2) | val;

        int m1 = (1 << 2 * n);
        int m2 = (1 << 2 * n + 1);
        if((mask & m1) != 0) {
            mask ^= m1;
        }
        if((mask & m2) != 0) {
            mask ^= m2;
        }

        return mask;
    }

    private int getLeft(int mask) {
        return mask & 3;
    }

    private int getUp(int mask) {
        return mask >> (n - 1) * 2;
    }
}
