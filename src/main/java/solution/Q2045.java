package solution;

import java.util.*;

public class Q2045 {
    /**
     * Time:  O(E * logV)
     * Space: O(E + V)
     * */
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<Integer>[] adj = new List[n + 1];
        for(int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[][] best = new int[n + 1][2];
        for(int i = 0; i < n + 1; i++) {
            Arrays.fill(best[i], Integer.MAX_VALUE);
        }
        //Arrays.fill(best, Integer.MAX_VALUE);

        pq.offer(new int[] {1, 0});
        best[1][0] = 0;

        int min = -1;
        int ans = 0;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int v = cur[0];
            int t = cur[1];

            if(v == n) {
                if(best[v][1] != Integer.MAX_VALUE) {
                    //System.out.println();
                    ans = best[v][1];
                    break;
                }
            }

            for(int nei : adj[v]) {
                boolean green = (t / change) % 2 == 0;
                int nt = 0;
                if(green) {
                    nt = t + time;
                } else {
                    int wt = (t / change + 1) * change - t;
                    nt = t + wt + time;
                }
                //int nt = green ? t + time : ((t / change) + 1) * change -  + t + time;

                if(nt < best[nei][0]) {
                    best[nei][1] = best[nei][0];
                    best[nei][0] = nt;
                    pq.offer(new int[] {nei, nt});
                } else if(nt > best[nei][0] && nt < best[nei][1]) {
                    best[nei][1] = nt;
                    pq.offer(new int[] {nei, nt});
                }
            }

            //0 10 20  change = 3

        }
        //System.out.println(min);
        return ans;
    }
}
