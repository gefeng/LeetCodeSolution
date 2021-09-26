package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Transform to Chessboard",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/transform-to-chessboard/"
)
public class Q782 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int movesToChessboard(int[][] board) {
        int n = board.length;
        int ans = 0;

        Map<Integer, Integer> cntMap = new HashMap<>();
        for(int i = 0; i < n; i++) {
            int mask = 0;
            for(int j = 0; j < n; j++) {
                mask = (mask << 1) + board[i][j];
            }
            cntMap.put(mask, cntMap.getOrDefault(mask, 0) + 1);
        }

        int row = solve(cntMap, n);
        if(row == -1) {
            return -1;
        }

        cntMap = new HashMap<>();
        for(int i = 0; i < n; i++) {
            int mask = 0;
            for(int j = 0; j < n; j++) {
                mask = (mask << 1) + board[j][i];
            }
            cntMap.put(mask, cntMap.getOrDefault(mask, 0) + 1);
        }

        int col = solve(cntMap, n);
        if(col == -1) {
            return -1;
        }

        return row + col;
    }

    private int solve(Map<Integer, Integer> cntMap, int n) {
        if(cntMap.size() != 2) {
            return -1;
        }

        List<Integer> keys = new ArrayList<>(cntMap.keySet());
        int r1 = keys.get(0);
        int r2 = keys.get(1);
        int cnt1 = cntMap.get(r1);
        int cnt2 = cntMap.get(r2);

        if(cnt1 != cnt2 && Math.abs(cnt1 - cnt2) != 1) {
            return -1;
        }

        if((r1 ^ r2) != (1 << n) - 1) {
            return -1;
        }

        int cntOnes = Integer.bitCount(r1);
        int mask0 = (1 << n) - 1;
        int mask1 = 0xAAAAAAAA;
        int mask2 = 0x55555555;
        int min = Integer.MAX_VALUE;

        if(n % 2 == 0) {
            min = Math.min(min, Integer.bitCount(r1 ^ mask1 & mask0) / 2);
            min = Math.min(min, Integer.bitCount(r1 ^ mask2 & mask0) / 2);
        } else {
            if(cntOnes == n / 2) {
                min = Math.min(min, Integer.bitCount(r1 ^ mask1 & mask0) / 2);
            } else {
                min = Math.min(min, Integer.bitCount(r1 ^ mask2 & mask0) / 2);
            }
        }

        return min;
    }
}
