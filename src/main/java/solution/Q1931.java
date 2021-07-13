package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Painting a Grid With Three Different Colors",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/painting-a-grid-with-three-different-colors/"
)
public class Q1931 {
    /*
        for each cell we can use 2 bits to represent its color
        00 white 01 red 10 green 11 blue

        thus there are totally 1024 states
        for each state, generate next column

        time: O(2^m * 2^m * n)
    */
    public int colorTheGrid(int m, int n) {
        return dfs(m, n, 0, 0, new Integer[n][1 << (2 * m)]);
    }

    private int dfs(int m, int n, int col, int prevMask, Integer[][] memo) {
        if(col == n) {
            return 1;
        }

        if(memo[col][prevMask] != null) {
            return memo[col][prevMask];
        }

        int cnt = 0;
        int mod = (int)1e9 + 7;

        List<Integer> masks = generate(m, prevMask);
        for(int mask : masks) {
            cnt = (cnt + dfs(m, n, col + 1, mask, memo)) % mod;
        }

        return memo[col][prevMask] = cnt;
    }

    private List<Integer> generate(int m, int prevCol) {
        List<Integer> nextCols = new ArrayList<>();
        helper(m, prevCol, 0, 0, nextCols);
        return nextCols;
    }

    private void helper(int m, int prevMask, int row, int currMask, List<Integer> nextCols) {
        if(row == m) {
            nextCols.add(currMask);
            return;
        }

        for(int c = 1; c <= 3; c++) {
            if(c != getColor(row, prevMask) && (row == 0 || c != getPrevColor(row, currMask))) {
                helper(m, prevMask, row + 1, currMask | (c << (row * 2)), nextCols);
            }
        }
    }

    private int getColor(int i, int mask) {
        return (mask >> i * 2) & 3;
    }

    private int getPrevColor(int i, int mask) {
        return (mask >> (i - 1) * 2) & 3;
    }
}
