package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Jump Game III",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/jump-game-iii/"
)
public class Q1306 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();

            if(arr[cur] == 0) {
                return true;
            }

            for(int d = -1; d < 2; d += 2) {
                int nex = cur + d * arr[cur];
                if(nex >= 0 && nex < n && !visited[nex]) {
                    q.offer(nex);
                    visited[nex] = true;
                }
            }
        }

        return false;
    }
}
