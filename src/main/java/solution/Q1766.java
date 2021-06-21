package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Problem(
        title = "Tree of Coprimes",
        difficulty = QDifficulty.HARD,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/tree-of-coprimes/"
)
public class Q1766 {
    private List<Integer>[] graph;
    private List<Integer>[] coprime;
    public int[] getCoprimes(int[] nums, int[][] edges) {
        int n = nums.length;
        graph = new List[n];
        coprime = new List[51];

        // build graph
        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            if(graph[u] == null) {
                graph[u] = new ArrayList<>();
            }
            if(graph[v] == null) {
                graph[v] = new ArrayList<>();
            }

            graph[u].add(v);
            graph[v].add(u);
        }

        // pre-calculate coprime
        Set<Integer> unique = new HashSet<>();
        for(int num : nums) {
            unique.add(num);
        }
        for(int x : unique) {
            for(int y : unique) {
                if(gcd(x, y) == 1) {
                    coprime[x] = coprime[x] == null ? new ArrayList<>() : coprime[x];
                    coprime[y] = coprime[y] == null ? new ArrayList<>() : coprime[y];
                    coprime[x].add(y);
                    coprime[y].add(x);
                }
            }
        }

        int[] ans = new int[n];
        List<int[]>[] ancestors = new List[51];
        traverse(nums, ancestors, 0, 0, 0, ans);
        return ans;
    }

    private void traverse(int[] nums, List<int[]>[] ancestors, int i, int parent, int level, int[] ans) {
        int val = nums[i];
        ans[i] = -1;

        List<Integer> cops = coprime[val];
        if(cops != null) {
            int maxLevel = -1;
            for(int cop : cops) {
                if(ancestors[cop] == null || ancestors[cop].size() == 0) {
                    continue;
                }
                
                // just check the most recent added node.
                int[] anc = ancestors[cop].get(ancestors[cop].size() - 1);
                if(anc[1] > maxLevel) {
                    maxLevel = anc[1];
                    ans[i] = anc[0];
                }
            }
        }

        ancestors[val] = ancestors[val] == null ? new ArrayList<>() : ancestors[val];
        ancestors[val].add(new int[] {i, level});

        if(graph[i] != null) {
            for(int nei : graph[i]) {
                if(nei == parent) {
                    continue;
                }

                traverse(nums, ancestors, nei, i, level + 1, ans);
            }
        }
        ancestors[val].remove(ancestors[val].size() - 1);
    }

    private int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }
}
