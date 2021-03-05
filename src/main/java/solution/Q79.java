package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;

@Problem(
        title = "Word Search",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/word-search/"
)
public class Q79 {
    private static final int[][] DIR = new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public boolean exist(char[][] board, String word) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(searchWord(board, word, 0, i, j, new HashSet<>()))
                    return true;
            }
        }
        return false;
    }

    private boolean searchWord(char[][] board, String word, int idx, int sr, int sc, HashSet<Integer> visited) {
        if(idx == word.length() - 1 && word.charAt(idx) == board[sr][sc])
            return true;
        if(idx > word.length() - 1 || word.charAt(idx) != board[sr][sc])
            return false;

        visited.add(sr * board[0].length + sc);

        for(int[] dir : DIR) {
            int r = sr + dir[0];
            int c = sc + dir[1];
            if(r >= 0 && c >= 0 && r < board.length && c < board[0].length && !visited.contains(r * board[0].length + c))
                if(searchWord(board, word, idx + 1, r, c, visited))
                    return true;
        }

        visited.remove(sr * board[0].length + sc);
        return false;
    }
}
