package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Find Servers That Handled Most Number of Requests",
        difficulty = QDifficulty.HARD,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests/"
)
public class Q1606 {
    /**
     * Time:  O(K * logN)
     * Space: O(K)
     * */
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int n = arrival.length;
        int maxCnt = 0;
        List<Integer> res = new ArrayList<>();

        int[][] sMap = new int[k][3];
        TreeSet<int[]> free = new TreeSet<>(Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for(int i = 0; i < k; i++) {
            int[] s = new int[] {i, 0, 0};

            sMap[i] = s;
            free.add(s);
        }

        int currTime = 0;
        for(int i = 0; i < n; i++) {
            int a = arrival[i];
            int l = load[i];

            currTime = a;

            while(!busy.isEmpty() && busy.peek()[1] <= currTime) {
                free.add(busy.poll());
            }

            if(free.isEmpty()) {
                continue;
            }

            int[] server = free.ceiling(sMap[i % k]);
            if(server != null) {
                server[1] = currTime + l;
                server[2] += 1;
                busy.offer(server);
                free.remove(server);
            } else {
                server = free.pollFirst();
                server[1] = currTime + l;
                server[2] += 1;
                busy.offer(server);
                free.remove(server);
            }

            maxCnt = Math.max(maxCnt, server[2]);
        }


        for(int i = 0; i < k; i++) {
            if(sMap[i][2] == maxCnt) {
                res.add(i);
            }
        }

        return res;
    }
}
