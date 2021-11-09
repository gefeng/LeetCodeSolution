package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Filling Bookcase Shelves",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/filling-bookcase-shelves/"
)
public class Q1105 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;

        return dfs(books, shelfWidth, 0, new Integer[n]);
    }

    private int dfs(int[][] books, int shelfWidth, int start, Integer[] memo) {
        int n = books.length;
        if(start == n) {
            return 0;
        }

        if(memo[start] != null) {
            return memo[start];
        }

        int w = 0;
        int h = 0;
        int min = Integer.MAX_VALUE;
        for(int i = start; i < n; i++) {
            w += books[i][0];
            h = Math.max(h, books[i][1]);

            if(w > shelfWidth) {
                break;
            }

            min = Math.min(min, dfs(books, shelfWidth, i + 1, memo) + h);
        }

        return memo[start] = min;
    }
}
