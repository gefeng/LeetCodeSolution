package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Race Car",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/race-car/"
)
public class Q818 {
    /**
     * Time:  O(2 ^ N)
     * Space: O(2 ^ N)
     * */
    public int racecar(int target) {
        int maxp = 2 * target;
        int maxs = 2 * target;
        int ans = 0;

        Queue<Node> q = new ArrayDeque<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();

        q.offer(new Node(0, 1, 0));
        map.computeIfAbsent(0, k -> new HashSet<>()).add(1);

        while(!q.isEmpty()) {
            Node cur = q.poll();
            int p = cur.p;
            int s = cur.s;
            int steps = cur.steps;

            if(p == target) {
                ans = steps;
                break;
            }

            // accelerate
            {
                int np = p + s;
                int ns = s * 2;

                if(np >= -maxp && np <= maxp && ns >= -maxs && ns <= maxs) {
                    if(!map.containsKey(np) || !map.get(np).contains(ns)) {
                        q.offer(new Node(np, ns, steps + 1));
                        map.computeIfAbsent(np, k -> new HashSet<>()).add(ns);
                    }
                }
            }
            // reverse
            {
                int np = p;
                int ns = s > 0 ? -1 : 1;

                if(np >= -maxp && np <= maxp && ns >= -maxs && ns <= maxs) {
                    if(!map.containsKey(np) || !map.get(np).contains(ns)) {
                        q.offer(new Node(np, ns, steps + 1));
                        map.computeIfAbsent(np, k -> new HashSet<>()).add(ns);
                    }
                }
            }
        }

        return ans;
    }

    private class Node {
        int p;
        int s;
        int steps;
        Node(int p, int s, int steps) {
            this.p = p;
            this.s = s;
            this.steps = steps;
        }
    }
}
