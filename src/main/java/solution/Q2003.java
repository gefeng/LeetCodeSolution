package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = " Smallest Missing Genetic Value in Each Subtree",
        difficulty = QDifficulty.HARD,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/smallest-missing-genetic-value-in-each-subtree/"
)
public class Q2003 {
    /**
     * Only need to handle the path from node with genetic value 1 to root.
     * The rest of the nodes have answer 1.
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = parents.length;
        int[] ans = new int[n];
        List<Integer>[] adj = new List[n];

        Arrays.fill(ans, 1);

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        int one = -1;
        for(int i = 0; i < n; i++) {
            int p = parents[i];
            if(p != -1) {
                adj[p].add(i);
            }

            if(nums[i] == 1) {
                one = i;
            }
        }

        if(one == -1) {
            return ans;
        }

        int cur = one;
        int missing = 1;
        boolean[] seen = new boolean[100002];

        while(cur != -1) {
            dfs(adj, nums, cur, seen);
            while(seen[missing]) {
                missing++;
            }
            ans[cur] = missing;
            cur = parents[cur];
        }

        return ans;
    }

    private void dfs(List<Integer>[] adj, int[] nums, int cur, boolean[] seen) {
        seen[nums[cur]] = true;

        for(int nei : adj[cur]) {
            if(!seen[nums[nei]]) {
                dfs(adj, nums, nei, seen);
            }
        }
    }
}
