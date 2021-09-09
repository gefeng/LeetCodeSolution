package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Path Crossing",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/path-crossing/"
)
public class Q1496 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean isPathCrossing(String path) {
        int n = path.length();
        Set<Integer> seen = new HashSet<>();

        int[] pos = new int[2];
        int[] dir = new int[2];
        seen.add(0);
        for(int i = 0; i < n; i++) {
            char c = path.charAt(i);

            switch(c) {
                case 'N': dir = new int[] {-1, 0}; break;
                case 'S': dir = new int[] {1, 0}; break;
                case 'E': dir = new int[] {0, 1}; break;
                case 'W': dir = new int[] {0, -1}; break;
            }

            int[] np = new int[] {pos[0] + dir[0], pos[1] + dir[1]};
            int mapping = np[0] * 10001 + np[1];

            if(seen.contains(mapping)) {
                return true;
            }

            seen.add(mapping);
            pos = np;
        }

        return false;
    }
}
