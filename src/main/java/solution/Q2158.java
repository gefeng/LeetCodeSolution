package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.TreeMap;

@Problem(
        title = "Amount of New Area Painted Each Day",
        difficulty = QDifficulty.HARD,
        tag = QTag.ORDERED_MAP,
        url = "https://leetcode.com/problems/amount-of-new-area-painted-each-day/"
)
public class Q2158 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int[] amountPainted(int[][] paint) {
        int n = paint.length;
        int[] ans = new int[n];

        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 50000);

        for(int i = 0; i < n; i++) {
            int[] p = paint[i];
            int area = 0;
            Integer cur = map.ceilingKey(p[0]);

            while(cur != null && map.get(cur) <= p[1]) {
                area += map.get(cur) - cur;
                map.remove(cur);

                cur = map.ceilingKey(cur);
            }

            Integer fk = map.floorKey(p[0]);
            if(fk != null) {
                int r = map.get(fk);
                if(r >= p[1]) {
                    area += p[1] - p[0];

                    map.remove(fk);
                    if(p[0] - fk > 0) {
                        map.put(fk, p[0]);
                    }
                    if(r - p[1] > 0) {
                        map.put(p[1], r);
                    }
                } else if(r > p[0]) {
                    area += r - p[0];
                    map.put(fk, p[0]);
                }
            }

            Integer lk = map.lowerKey(p[1]);
            if(lk != null) {
                int r = map.get(lk);
                if(r > p[1]) {
                    area += p[1] - lk;
                    map.remove(lk);
                    map.put(p[1], r);
                }
            }


            ans[i] = area;
        }

        return ans;
    }
}
