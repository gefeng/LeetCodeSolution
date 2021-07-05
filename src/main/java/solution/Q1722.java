package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Minimize Hamming Distance After Swap Operations",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations/"
)
public class Q1722 {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;

        int[] parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for(int[] e : allowedSwaps) {
            int x = find(parent, e[0]);
            int y = find(parent, e[1]);
            if(x != y) {
                union(parent, x, y);
            }
        }

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int min = 0;
        for(int i = 0; i < n; i++) {
            int p = find(parent, i);
            Map<Integer, Integer> entry = map.computeIfAbsent(p, k -> new HashMap<>());
            entry.put(source[i], entry.getOrDefault(source[i], 0) + 1);
            entry.put(target[i], entry.getOrDefault(target[i], 0) - 1);
        }

        for(int k1 : map.keySet()) {
            Map<Integer, Integer> entry = map.get(k1);
            for(int k2 : entry.keySet()) {
                int cnt = entry.get(k2);
                min = cnt > 0 ? min + cnt : min;
            }
        }

        return min;
    }

    private int find(int[] parent, int i) {
        if(parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    private void union(int[] parent, int i, int j) {
        int x = find(parent, i);
        int y = find(parent, j);
        parent[x] = y;
    }
}
