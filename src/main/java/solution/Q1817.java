package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Problem(
        title = "Finding the Users Active Minutes",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/finding-the-users-active-minutes/"
)
public class Q1817 {
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer, Set<Integer>> uamMap = new HashMap<>();

        for(int[] log : logs) {
            uamMap.computeIfAbsent(log[0], key -> new HashSet<>());
            uamMap.get(log[0]).add(log[1]);
        }

        int[] cnt = new int[k];
        for(int key : uamMap.keySet()) {
            cnt[uamMap.get(key).size() - 1]++;
        }

        return cnt;
    }
}
