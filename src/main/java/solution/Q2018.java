package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Check if Word Can Be Placed In Crossword",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/check-if-word-can-be-placed-in-crossword/"
)
public class Q2018 {
    /**
     * Time:  O(M * N * max(M, N))
     * Space: O(M * N)
     * */
    public boolean placeWordInCrossword(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        String rWord = new StringBuilder(word).reverse().toString();

        List<String> hor = new ArrayList<>();
        List<String> ver = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < n; j++) {
                char c = board[i][j];
                if(c != '#') {
                    sb.append(c);
                }

                if(c == '#' || j == n - 1) {
                    if(sb.length() == word.length()) {
                        ver.add(sb.toString());
                    }
                    sb = new StringBuilder();
                }
            }
        }

        for(int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < m; j++) {
                char c = board[j][i];
                if(c != '#') {
                    sb.append(c);
                }

                if(c == '#' || j == m - 1) {
                    if(sb.length() == word.length()) {
                        ver.add(sb.toString());
                    }
                    sb = new StringBuilder();
                }
            }
        }

        for(String s : hor) {
            if(isSubseq(s, word) || isSubseq(s, rWord)) {
                return true;
            }
        }

        for(String s : ver) {
            if(isSubseq(s, word) || isSubseq(s, rWord)) {

                return true;
            }
        }

        return false;
    }


    private boolean isSubseq(String s1, String s2) {
        int n = s1.length();

        for(int i = 0; i < n; i++) {
            if(s1.charAt(i) != ' ' && s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
