package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "The Number of Weak Characters in the Game",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/"
)
public class Q1996 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int numberOfWeakCharacters(int[][] properties) {
        int n = properties.length;
        int res = 0;

        Arrays.sort(properties, (a, b) -> {
            if(a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(b[0], a[0]);
        });

        int maxDef = 0;
        for(int i = 0; i < n; i++) {
            res += maxDef > properties[i][1] ? 1 : 0;
            maxDef = Math.max(maxDef, properties[i][1]);
        }

        return res;
    }
}
