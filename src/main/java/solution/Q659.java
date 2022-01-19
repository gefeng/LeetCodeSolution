package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Split Array into Consecutive Subsequences",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/split-array-into-consecutive-subsequences/"
)
public class Q659 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public boolean isPossible(int[] nums) {
        int n = nums.length;
        Map<Integer, Queue<Integer>> map = new HashMap<>();

        for(int x : nums) {
            if(map.containsKey(x - 1)) {
                Queue<Integer> q = map.get(x - 1);
                int len = q.poll() + 1;
                map.computeIfAbsent(x, k -> new PriorityQueue<>()).offer(len);

                if(q.isEmpty()) map.remove(x - 1);
            } else {
                map.computeIfAbsent(x, k -> new PriorityQueue<>()).offer(1);
            }
        }

        for(int last : map.keySet()) {
            Queue<Integer> q = map.get(last);

            while(!q.isEmpty()) {
                int len = q.poll();
                if(len < 3) return false;
            }
        }

        return true;
    }
}
