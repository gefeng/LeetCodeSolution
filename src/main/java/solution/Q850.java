package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Rectangle Area II",
        difficulty = QDifficulty.HARD,
        tag = QTag.LINE_SWEEP,
        url = "https://leetcode.com/problems/rectangle-area-ii/"
)
public class Q850 {
    /**
     * Time:  O(N ^ 2 * logN)
     * Space: O(N)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int rectangleArea(int[][] rectangles) {
        int n = rectangles.length;
        int[][] events = new int[n * 2][4];

        for(int i = 0; i < n; i++) {
            int[] r = rectangles[i];
            events[i * 2] = new int[] {r[1], r[0], r[2], 1};
            events[i * 2 + 1] = new int[] {r[3], r[0], r[2], -1};
        }

        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));

        long area = 0;
        int preY = events[0][0];
        List<int[]> active = new ArrayList<>();
        for(int i = 0; i < n * 2; i++) {
            if(events[i][0] != preY) {
                Collections.sort(active, Comparator.comparingInt(a -> a[0]));
                long sumX = 0;
                int[] pre = null;
                for(int[] cur : active) {
                    if(pre != null && cur[0] <= pre[1]) {
                        sumX += Math.max(cur[1] - pre[1], 0);
                        pre[1] = Math.max(pre[1], cur[1]);
                    } else {
                        sumX += cur[1] - cur[0];
                        pre = new int[] {cur[0], cur[1]};
                    }
                }

                area += sumX * (events[i][0] - preY);
                preY = events[i][0];
            }

            if(events[i][3] == -1) {
                for(int j = 0; j < active.size(); j++) {
                    if(active.get(j)[0] == events[i][1] && active.get(j)[1] == events[i][2]) {
                        active.remove(j);
                        break;
                    }
                }
            } else {
                active.add(new int[] { events[i][1], events[i][2] });
            }
        }

        return (int)(area % MOD);
    }
}
