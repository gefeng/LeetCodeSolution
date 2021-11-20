package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Problem(
        title = "Grid Illumination",
        difficulty = QDifficulty.HARD,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/grid-illumination/"
)
public class Q1001 {
    /**
     * Time:  O(M + N)
     * Space: O(M + N)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        Set<Long> set = new HashSet<>();
        Map<Integer, Integer> rMap = new HashMap<>();
        Map<Integer, Integer> cMap = new HashMap<>();
        Map<Integer, Integer> dMap1 = new HashMap<>();
        Map<Integer, Integer> dMap2 = new HashMap<>();

        for(int[] l : lamps) {
            int r = l[0];
            int c = l[1];
            if(set.contains(r * (long)n + c)) {
                continue;
            }
            set.add(r * (long)n + c);
            rMap.put(r, rMap.getOrDefault(r, 0) + 1);
            cMap.put(c, cMap.getOrDefault(c, 0) + 1);
            dMap1.put(r - c, dMap1.getOrDefault(r - c, 0) + 1);
            dMap2.put(r + c, dMap2.getOrDefault(r + c, 0) + 1);
        }

        for(int i = 0; i < m; i++) {
            int[] q = queries[i];
            int r = q[0];
            int c = q[1];

            if(rMap.getOrDefault(r, 0) != 0 || cMap.getOrDefault(c, 0) != 0 ||
                    dMap1.getOrDefault(r - c, 0) != 0 || dMap2.getOrDefault(r + c, 0) != 0) {
                ans[i] = 1;
            } else {
                ans[i] = 0;
            }

            for(int[] dir : DIRECTIONS) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if(nr >= 0 && nc >= 0 && nr < n && nc < n) {
                    if(set.contains(nr * (long)n + nc)) {
                        turnOff(rMap, nr);
                        turnOff(cMap, nc);
                        turnOff(dMap1, nr - nc);
                        turnOff(dMap2, nr + nc);
                        set.remove(nr * (long)n + nc);
                    }
                }
            }
        }

        return ans;
    }

    private void turnOff(Map<Integer, Integer> map, int key) {
        int cnt = map.getOrDefault(key, 0);
        if(cnt == 1) {
            map.remove(key);
        } else if(cnt > 1) {
            map.put(key, cnt - 1);
        }
    }
}
