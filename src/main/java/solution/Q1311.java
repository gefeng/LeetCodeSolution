package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Get Watched Videos by Your Friends",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/get-watched-videos-by-your-friends/"
)
public class Q1311 {
    /**
     * Time:  O(E + V + M * logM)
     * Space: O(E + V + M)
     * */
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = watchedVideos.size();
        List<String> ans = new ArrayList<>();
        List<Integer>[] adj = new List[n];

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();

            for(int f : friends[i]) {
                adj[i].add(f);
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] seen = new boolean[n];

        q.offer(id);
        seen[id] = true;

        int cnt = 0;
        Map<String, Integer> freqMap = new HashMap<>();

        while(!q.isEmpty()) {
            int sz = q.size();

            for(int i = 0; i < sz; i++) {
                int cur = q.poll();
                if(cnt == level) {
                    for(String m : watchedVideos.get(cur)) {
                        freqMap.put(m, freqMap.getOrDefault(m, 0) + 1);
                    }
                }

                for(int nei : adj[cur]) {
                    if(!seen[nei]) {
                        q.offer(nei);
                        seen[nei] = true;
                    }
                }
            }

            cnt++;
            if(cnt > level) {
                break;
            }
        }

        TreeSet<String>[] sets = new TreeSet[n + 1];
        for(Map.Entry<String, Integer> e : freqMap.entrySet()) {
            String m = e.getKey();
            int f = e.getValue();

            if(sets[f] == null) {
                sets[f] = new TreeSet<>();
            }
            sets[f].add(m);
        }

        for(int i = 1; i < n + 1; i++) {
            if(sets[i] != null) {
                for(String m : sets[i]) {
                    ans.add(m);
                }
            }
        }

        return ans;
    }
}
