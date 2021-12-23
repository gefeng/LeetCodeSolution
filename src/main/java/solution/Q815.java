package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Bus Route",
        difficulty = QDifficulty.HARD,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/bus-routes/"
)
public class Q815 {
    /**
     * Image i am at stop x and i can take y buses at this stop x. Pick a bus and check each stops it contains to
     * see if destination is there. Push all the stops haven't visited to queue. Level order BFS can guarantee we take
     * least number buses when arriving at the destination. The time complexity looks huge since we have nested loops but
     * we only take a bus once and visit a stop once so overall it's linear.
     *
     * Time:  O(S + R)
     * Space: O(S + R)
     * */
    public int numBusesToDestination(int[][] routes, int source, int target) {
        int n = routes.length;
        int ans = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            int[] r = routes[i];

            for(int s : r) {
                map.computeIfAbsent(s, k -> new ArrayList<>()).add(i);
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] seenr = new boolean[n];
        boolean[] seens = new boolean[1000000];
        int buses = 0;
        q.offer(source);

        while(!q.isEmpty()) {
            int sz = q.size();
            for(int i = 0; i < sz; i++) {
                int p = q.poll();

                if(p == target) return buses;

                for(int r : map.get(p)) {
                    if(seenr[r]) continue;

                    for(int s : routes[r]) {
                        if(seens[s]) continue;
                        q.offer(s);
                        seens[s] = true;
                    }

                    seenr[r] = true;
                }
            }

            buses++;
        }

        return -1;
    }
}
