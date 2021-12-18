package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Keys and Rooms",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/keys-and-rooms/"
)
public class Q841 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    class Solution {
        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            int n = rooms.size();

            Set<Integer> keys = new HashSet<>();
            Set<Integer> visited = new HashSet<>();

            Queue<Integer> q = new ArrayDeque<>();
            q.offer(0);
            visited.add(0);
            while(!q.isEmpty()) {
                int sz = q.size();
                for(int i = 0; i < sz; i++) {
                    int key = q.poll();

                    for(int k : rooms.get(key)) {
                        if(!visited.contains(k)) {
                            q.offer(k);
                            visited.add(k);
                        }
                    }
                }
            }

            return visited.size() == n;
        }
    }
}
