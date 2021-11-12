package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Letter Tile Possibilities",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/letter-tile-possibilities/"
)
public class Q1079 {
    /**
     * Time:  O(7!)
     * Space: O(7!)
     * */
    Set<String> ans = new HashSet<>();
    public int numTilePossibilities(String tiles) {
        char[] s = tiles.toCharArray();

        dfs(s, 0, "");

        return ans.size() - 1;
    }

    private void dfs(char[] s, int mask, String cur) {
        ans.add(cur);

        for(int i = 0; i < s.length; i++) {
            if((mask & (1 << i)) == 0) {
                dfs(s, mask | (1 << i), cur + s[i]);
            }
        }
    }
}
