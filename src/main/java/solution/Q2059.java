package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

@Problem(
        title = "Minimum Operations to Convert Number",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/minimum-operations-to-convert-number/"
)
public class Q2059 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public int minimumOperations(int[] nums, int start, int goal) {
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> seen = new HashSet<>();
        q.offer(start);
        seen.add(start);

        int steps = 0;
        while(!q.isEmpty()) {
            int len = q.size();
            for(int i = 0; i < len; i++) {
                int cur = q.poll();

                if(cur == goal) {
                    return steps;
                }

                for(int x : nums) {
                    int[] next = new int[] {cur + x, cur - x, cur ^ x};

                    for(int y : next) {
                        if(seen.contains(y) || (y < 0 && y != goal) || (y > 1000 && y != goal)) {
                            continue;
                        }
                        q.offer(y);
                        seen.add(y);
                    }
                }
            }
            steps++;
        }

        return -1;
    }
}
