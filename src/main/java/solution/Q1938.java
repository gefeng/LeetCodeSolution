package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Maximum Genetic Difference Query",
        difficulty = QDifficulty.HARD,
        tag = QTag.TRIE,
        url = "https://leetcode.com/problems/maximum-genetic-difference-query/"
)
public class Q1938 {
    /*
    * Time: O(N + M)
    * Space: O(N + M)
    * */
    private class TrieNode {
        TrieNode[] children = new TrieNode[2];
        int cnt = 0;
        int val = -1;
    }
    private void add(int val) {
        TrieNode curr = root;

        // 2^20
        for(int i = 19; i >= 0; i--) {
            int bit = ((1 << i) & val) == 0 ? 0 : 1;
            if(curr.children[bit] == null) {
                curr.children[bit] = new TrieNode();
            }
            curr = curr.children[bit];
            curr.cnt++;
        }
        curr.val = val;
    }

    private int search(int val) {
        TrieNode curr = root;
        for(int i = 19; i >= 0; i--) {
            int bit = ((1 << i) & val) == 0 ? 0 : 1;
            int rBit = bit ^ 1;

            if(curr.children[rBit] != null) {
                curr = curr.children[rBit];
            } else {
                curr = curr.children[bit];
            }
        }

        return curr.val ^ val;
    }

    private void remove(int val) {
        TrieNode curr = root;

        for(int i = 19; i >= 0; i--) {
            int bit = ((1 << i) & val) == 0 ? 0 : 1;
            curr.children[bit].cnt--;
            if(curr.children[bit].cnt == 0) {
                curr.children[bit] = null;
                break;
            }
            curr = curr.children[bit];
        }
    }

    private List<Integer>[] adj;
    private List<int[]>[] q;
    private TrieNode root;
    public int[] maxGeneticDifference(int[] parents, int[][] queries) {
        int m = queries.length;
        int n = parents.length;
        adj = new List[n];
        q = new List[n];
        root = new TrieNode();

        int rt = 0;
        for(int i = 0; i < n; i++) {
            int p = parents[i];
            if(p == -1) {
                rt = i;
                continue;
            }
            if(adj[p] == null) {
                adj[p] = new ArrayList<>();
            }
            adj[p].add(i);
        }

        for(int i = 0; i < m; i++) {
            if(q[queries[i][0]] == null) {
                q[queries[i][0]] = new ArrayList<>();
            }
            q[queries[i][0]].add(new int[] {queries[i][1], i});
        }

        int[] ans = new int[m];
        dfs(rt, ans);

        return ans;
    }

    private void dfs(int node, int[] ans) {
        add(node);
        List<int[]> queries = q[node];
        if(queries != null) {
            for(int[] query : queries) {
                ans[query[1]] = search(query[0]);
            }
        }

        List<Integer> children = adj[node];
        if(children != null) {
            for(int child : children) {
                dfs(child, ans);
            }
        }

        remove(node);
    }
}
