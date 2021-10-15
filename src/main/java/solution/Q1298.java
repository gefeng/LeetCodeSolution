package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Maximum Candies You Can Get from Boxes",
        difficulty = QDifficulty.HARD,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes/"
)
public class Q1298 {
    /**
     * Time:  O(V * (E + V))
     * Space: O(E + V)
     * */
    private int n;
    private List<Integer>[] adj;
    private int[] sta;
    private int[] can;
    private int[] ini;
    private Set<Integer>[] key;
    private Set<Integer> have;
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int ans = 0;
        this.n = status.length;
        this.sta = status;
        this.can = candies;
        this.ini = initialBoxes;
        this.key = new Set[n];
        adj = new List[n];
        have = new HashSet<>();

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            for(int b : containedBoxes[i]) {
                adj[i].add(b);
            }

            key[i] = new HashSet<>();
            for(int k : keys[i]) {
                key[i].add(k);
            }
        }

        for(int i = 0; i < n; i++) {
            ans += bfs();
        }

        return ans;
    }

    private int bfs() {
        int res = 0;
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        for(int b : ini) {
            if(sta[b] == 1 || have.contains(b)) {
                q.offer(b);
                visited[b] = true;
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            res += can[cur];
            can[cur] = 0;

            for(int k : key[cur]) {
                have.add(k);
            }

            key[cur].clear();

            for(int nei : adj[cur]) {
                if(sta[nei] == 1 || have.contains(nei)) {
                    q.offer(nei);
                    visited[nei] = true;
                }
            }
        }

        return res;
    }
}
