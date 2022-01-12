package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Falling Squares",
        difficulty = QDifficulty.HARD,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/falling-squares/"
)
public class Q699 {
    /**
     * Coordinate compression, trick to map large coordinates into a smaller range.
     *
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        int n = positions.length;

        Set<Integer> set = new TreeSet<>();
        for(int[] p : positions) {
            set.add(p[0]);
            set.add(p[0] + p[1] - 1);
        }

        int idx = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int x : set) {
            map.put(x, idx++);
        }

        int[] hmap = new int[set.size() + 1];
        int maxh = 0;
        for(int[] p : positions) {
            int l = map.get(p[0]);
            int h = p[1];
            int r = map.get(p[0] + p[1] - 1);
            int max = 0;

            for(int i = l; i <= r; i++) {
                max = Math.max(max, hmap[i]);
            }

            for(int i = l; i <= r; i++) {
                hmap[i] = max + h;
            }

            maxh = Math.max(maxh, max + h);
            ans.add(maxh);
        }

        return ans;
    }
}
