package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Smallest String With Swaps",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/smallest-string-with-swaps/"
)
public class Q1202 {
    /**
     * Time:  O(M + N)
     * Space: O(N)
     * */
    private class DisjointSet {
        private int[] parent;
        private int[] weight;
        DisjointSet(int n) {
            parent = new int[n];
            weight = new int[n];

            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int i) {
            if(parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        void union(int i, int j) {
            int x = find(i);
            int y = find(j);
            if(x != y) {
                if(weight[x] == weight[y]) {
                    parent[y] = x;
                    weight[x]++;
                } else if(weight[x] > weight[y]) {
                    parent[y] = x;
                } else {
                    parent[x] = y;
                }
            }
        }
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        DisjointSet ds = new DisjointSet(n);

        for(List<Integer> p : pairs) {
            int i = p.get(0);
            int j = p.get(1);
            ds.union(i, j);
        }

        Map<Integer, List<Integer>> map1 = new HashMap<>();
        Map<Integer, int[]> map2 = new HashMap<>();
        for(int i = 0; i < n; i++) {
            int p = ds.find(i);

            List<Integer> e1 = map1.computeIfAbsent(p, k -> new ArrayList<>());
            e1.add(i);

            int[] e2 = map2.computeIfAbsent(p, k -> new int[26]);
            e2[s.charAt(i) - 'a']++;
        }

        char[] ans = new char[n];
        for(int key : map1.keySet()) {
            int[] cnt = map2.get(key);

            for(int i : map1.get(key)) {
                ans[i] = getNext(cnt);
            }
        }

        return new String(ans);
    }

    private char getNext(int[] cnt) {
        int next = 0;
        for(int i = 0; i < 26; i++) {
            if(cnt[i] != 0) {
                next = i;
                cnt[i]--;
                break;
            }
        }

        return (char)(next + 'a');
    }
}
