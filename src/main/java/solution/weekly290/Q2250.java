package solution.weekly290;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Count Number of Rectangles Containing Each Point",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/contest/weekly-contest-290/problems/count-number-of-rectangles-containing-each-point/"
)
public class Q2250 {
    /**
     * Time:  O(M * H * logN)
     * Space: O(M + N)
     * */
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        int n = rectangles.length;
        int m = points.length;
        int[] ans = new int[m];

        List<Integer>[] hmap = new List[101];

        for(int i = 0; i <= 100; i++) {
            hmap[i] = new ArrayList<>();
        }

        for(int[] r : rectangles) {
            hmap[r[1]].add(r[0]);
        }

        for(int i = 0; i <= 100; i++) {
            Collections.sort(hmap[i]);
        }

        for(int i = 0; i < m; i++) {
            int cnt = 0;
            int[] p = points[i];

            for(int h = 0; h <= 100; h++) {
                if(h >= p[1]) {
                    int idx = lb(hmap[h], p[0]);

                    if(idx == -1) {
                        cnt += hmap[h].size();
                    } else {
                        cnt += hmap[h].size() - (idx + 1);
                    }
                }
            }

            ans[i] = cnt;
        }

        return ans;
    }

    private int lb(List<Integer> l, int t) {
        int lo = 0;
        int hi = l.size() - 1;
        int idx = -1;
        while(lo <= hi) {
            int mid = lo + hi >> 1;

            if(l.get(mid) < t) {
                idx = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return idx;
    }

    /**
     * Time:  O((M + N) * log(M + N) + (M + N) * H)
     * Space: O(M + N)
     * */
    private int[] eventsSol(int[][] rectangles, int[][] points) {
        int n = rectangles.length;
        int m = points.length;
        int[][] events = new int[m + n][4];
        int[] ans = new int[m];

        for(int i = 0; i < n; i++) {
            events[i] = new int[] {rectangles[i][0], rectangles[i][1], 0, -1};
        }

        for(int i = 0; i < m; i++) {
            events[n + i] = new int[] {points[i][0], points[i][1], 1, i};
        }

        Arrays.sort(events, (a, b) -> {
            if(a[0] == b[0]) {
                return Integer.compare(a[2], b[2]);
            }

            return Integer.compare(b[0], a[0]);
        });

        int[] cnt = new int[101];
        for(int[] e : events) {
            if(e[2] == 0) {
                cnt[e[1]]++;
            } else {
                int tot = 0;

                for(int h = e[1]; h <= 100; h++) {
                    tot += cnt[h];
                }

                ans[e[3]] = tot;
            }
        }

        return ans;
    }
}
