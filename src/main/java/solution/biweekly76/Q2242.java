package solution.biweekly76;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Problem(
        title = "Maximum Score of a Node Sequence",
        difficulty = QDifficulty.HARD,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/contest/biweekly-contest-76/problems/maximum-score-of-a-node-sequence/"
)
public class Q2242 {
    /**
     * Time:  O((E + V) * log(E + V))
     * Space: O(E + V)
     * */
    public int maximumScore(int[] scores, int[][] edges) {
        int n = scores.length;
        int ans = -1;
        List<int[]>[] g = new List[n];

        for(int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

        for(int[] e : edges) {
            g[e[0]].add(new int[] {e[1], scores[e[1]]});
            g[e[1]].add(new int[] {e[0], scores[e[0]]});
        }

        for(int i = 0; i < n; i++) {
            Collections.sort(g[i], Comparator.comparing(a -> a[1], Comparator.reverseOrder()));
        }

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            List<int[]> neis1 = g[u];
            List<int[]> neis2 = g[v];

            int[] best1 = null;
            int[] best2 = null;
            for(int[] nei : neis1) {
                if(nei[0] != v) {
                    best1 = nei;
                    break;
                }
            }

            for(int[] nei : neis2) {
                if(nei[0] != u) {
                    best2 = nei;
                    break;
                }
            }

            if(best1 == null || best2 == null) {
                continue;
            }

            if(best1[0] != best2[0]) {
                ans = Math.max(ans, scores[u] + scores[v] + best1[1] + best2[1]);
            } else {
                int[] sbest1 = null;
                int[] sbest2 = null;
                for(int[] nei : neis1) {
                    if(nei[0] != v && nei[0] != best1[0]) {
                        sbest1 = nei;
                        break;
                    }
                }

                for(int[] nei : neis2) {
                    if(nei[0] != u && nei[0] != best2[0]) {
                        sbest2 = nei;
                        break;
                    }
                }

                if(sbest1 == null && sbest2 == null) {
                    continue;
                }

                if(sbest1 == null) {
                    ans = Math.max(ans, scores[u] + scores[v] + best1[1] + sbest2[1]);
                } else if(sbest2 == null) {
                    ans = Math.max(ans, scores[u] + scores[v] + sbest1[1] + best2[1]);
                } else {
                    ans = Math.max(ans, scores[u] + scores[v] + best1[1] + sbest2[1]);
                    ans = Math.max(ans, scores[u] + scores[v] + sbest1[1] + best2[1]);
                }
            }
        }

        return ans;
    }
}
