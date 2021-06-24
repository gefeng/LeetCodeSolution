package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Restore the Array From Adjacent Pairs",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/restore-the-array-from-adjacent-pairs/"
)
public class Q1743 {
    public int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length + 1;
        Map<Integer, List<Integer>> adj = new HashMap<>();
        Set<Integer> seen = new HashSet<>();
        int[] ans = new int[n];

        for(int[] pair : adjacentPairs) {
            adj.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
            adj.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
        }

        int head = 0;
        for(int key : adj.keySet()) {
            if(adj.get(key).size() == 1) {
                head = key;
                break;
            }
        }

        int curr = head;
        for(int i = 0; i < n; i++) {
            ans[i] = curr;
            seen.add(curr);

            for(int nei : adj.get(curr)) {
                if(!seen.contains(nei)) {
                    curr = nei;
                }
            }
        }

        return ans;
    }
}
