package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Shorest Distance to Target Color",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/shortest-distance-to-target-color/"
)
public class Q1182 {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int n = colors.length;
        int[][] closeColors = new int[n][3];

        int[] seen = new int[] {-1, -1, -1};  // keep track of the most recent found colors
        // scan forwards
        for(int i = 0; i < n; i++) {
            Arrays.fill(closeColors[i], -1);

            seen[colors[i] - 1] = i;

            for(int j = 0; j < 3; j++) {
                closeColors[i][j] = seen[j] == -1 ? -1 : i - seen[j];
            }
        }

        seen = new int[] {-1, -1, -1};
        // scan backwards
        for(int i = n - 1; i >= 0; i--) {
            seen[colors[i] - 1] = i;

            for(int j = 0; j < 3; j++) {
                if(seen[j] == -1) {
                    continue;
                }
                int prevDist = closeColors[i][j];
                int currDist = seen[j] - i;
                closeColors[i][j] = prevDist == -1 ? currDist : Math.min(prevDist, currDist);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for(int[] q : queries) {
            ans.add(closeColors[q[0]][q[1] - 1]);
        }
        return ans;
    }
}
