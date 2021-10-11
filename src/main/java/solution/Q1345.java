package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Jump Game IV",
        difficulty = QDifficulty.HARD,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/jump-game-iv/"
)
public class Q1345 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int minJumps(int[] arr) {
        int n = arr.length;
        int ans = -1;
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        int steps = 0;

        queue.offer(0);
        visited[0] = true;

        while(!queue.isEmpty()) {
            int sz = queue.size();

            for(int i = 0; i < sz; i++) {
                int cur = queue.poll();

                if(cur == n - 1) {
                    ans = steps;
                    break;
                }

                if(cur > 0 && !visited[cur - 1]) {
                    queue.offer(cur - 1);
                    visited[cur - 1] = true;
                }
                if(!visited[cur + 1]) {
                    queue.offer(cur + 1);
                    visited[cur + 1] = true;
                }

                for(int idx : map.get(arr[cur])) {
                    if(idx != cur && !visited[idx]) {
                        queue.offer(idx);
                        visited[idx] = true;
                    }
                }

                // clear all enqueued neighbors to avoid over-check
                // i.e. [7,7,7,7,7,7,7,7...,7,11]
                map.get(arr[cur]).clear();
            }

            if(ans != -1) {
                break;
            }

            steps++;
        }

        return ans;
    }
}
