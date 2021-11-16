package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Problem(
        title = "Flower Planting With No Adjacent",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/flower-planting-with-no-adjacent/"
)
public class Q1042 {
    /**
     * Time:  O(E + V)
     * Space: O(E + V)
     * */
    public int[] gardenNoAdj(int n, int[][] paths) {
        List<Integer>[] adj = new List[n + 1];
        int[] fMap = new int[n + 1];
        int[] ans = new int[n];

        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] p : paths) {
            int u = p[0];
            int v = p[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        for(int i = 1; i <= n; i++) {
            Set<Integer> set = new HashSet<>();

            for(int nei : adj[i]) {
                set.add(fMap[nei]);
            }

            for(int f = 1; f <= 4; f++) {
                if(!set.contains(f)) {
                    ans[i - 1] = f;
                    fMap[i] = f;
                    break;
                }
            }
        }

        return ans;
    }
}
