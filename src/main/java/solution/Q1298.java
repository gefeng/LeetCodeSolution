package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Maximum Candies You Can Get from Boxes",
        difficulty = QDifficulty.HARD,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes/"
)
public class Q1298 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int ans = 0;
        int n = status.length;
        boolean[] got = new boolean[n];
        boolean[] taken = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();

        for(int b : initialBoxes) {
            if(status[b] == 1) {
                q.offer(b);
                taken[b] = true;
            }
            got[b] = true;
        }

        while(!q.isEmpty()) {
            int cur = q.poll();
            ans += candies[cur];

            for(int k : keys[cur]) {
                status[k] = 1;
                if(got[k] && !taken[k]) {
                    q.offer(k);
                    taken[k] = true;
                }
            }

            for(int cb : containedBoxes[cur]) {
                got[cb] = true;
                if(status[cb] == 1 && !taken[cb]) {
                    q.offer(cb);
                    taken[cb] = true;
                }
            }
        }

        return ans;
    }
}
