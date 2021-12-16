package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Exam Room",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/exam-room/"
)
public class Q855 {
    /**
     * Time:  O(logN)
     * Space: O(N)
     * */
    private Map<Integer, int[]> map1;
    private Map<Integer, int[]> map2;
    private Queue<int[]> pq;
    private int n;
    public Q855(int n) {
        this.n = n;
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        pq = new PriorityQueue<>((a, b) -> {
            int r1 = a[1] - a[0] - 1;
            int r2 = b[1] - b[0] - 1;

            if(pq.size() > 1 && (r1 + 1) / 2 == (r2 + 1) / 2) {
                return Integer.compare(a[0], b[0]);
            }

            return Integer.compare(r2, r1);
        });

        int[] r = new int[] {-1, n};
        pq.offer(r);
        map1.put(-1, r);
        map2.put(n, r);
    }

    public int seat() {
        int s = 0;

        int[] cur = pq.poll();
        if(pq.isEmpty()) {
            s = 0;
        } else if(cur[0] == -1 || cur[1] == n) {
            s = cur[0] == -1 ? 0 : n - 1;
        } else {
            s = cur[0] + (cur[1] - cur[0]) / 2;
        }

        int[] l = new int[] {cur[0], s};
        int[] r = new int[] {s, cur[1]};

        map1.remove(cur[0]);
        map2.remove(cur[1]);
        map1.put(cur[0], l);
        map1.put(s, r);
        map2.put(s, l);
        map2.put(cur[1], r);

        pq.offer(l);
        pq.offer(r);

        return s;
    }

    public void leave(int p) {
        int[] l = map2.get(p);
        int[] r = map1.get(p);

        pq.remove(l);
        pq.remove(r);
        map1.remove(p);
        map2.remove(p);

        int[] merge = new int[] {l[0], r[1]};
        pq.offer(merge);
        map1.put(l[0], merge);
        map2.put(r[1], merge);
    }
}
