package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Maximum Employees to Be Invited to a Meeting",
        difficulty = QDifficulty.HARD,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/"
)
public class Q2127 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int maximumInvitations(int[] favorite) {
        return Math.max(findCycle1(favorite), findCycle2(favorite));
    }

    private int findCycle1(int[] fav) {
        int n = fav.length;
        int ans = 0;

        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;

            List<Integer> l = new ArrayList<>();
            int cur = i;
            while(!visited[cur]) {
                visited[cur] = true;
                l.add(cur);
                cur = fav[cur];
            }

            for(int j = 0; j < l.size(); j++) {
                if(l.get(j) == cur) {
                    ans = Math.max(ans, l.size() - j);
                }
            }
        }

        return ans;
    }

    private int findCycle2(int[] fav) {
        int n = fav.length;
        int ans = 0;
        List<Integer>[] g = new List[n];

        for(int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

        for(int i = 0; i < n; i++) {
            g[fav[i]].add(i);  // reverse
        }

        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;

            if(fav[fav[i]] == i) {
                int l = dfs(g, visited, i, fav[i]);
                int r = dfs(g, visited, fav[i], i);
                ans += l + r + 2;
            }
        }

        return ans;
    }

    private int dfs(List<Integer>[] g, boolean[] visited, int cur, int pair) {
        visited[cur] = true;

        int max = 0;
        for(int nei : g[cur]) {
            if(visited[nei] || nei == pair) continue;

            max = Math.max(max, dfs(g, visited, nei, pair) + 1);
        }

        return max;
    }
}
