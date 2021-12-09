package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Minimum Number of Refueling Stops",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-number-of-refueling-stops/"
)
public class Q871 {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        return greedySolution(target, startFuel, stations);
    }

    private int greedySolution(int target, int startFuel, int[][] stations) {
        int n = stations.length;

        Queue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        maxHeap.offer(new int[] {0, 0});

        int currPos = startFuel;
        int idx = 0;
        int fuel = 0;
        while(!maxHeap.isEmpty()) {
            if(currPos >= target) {
                return fuel;
            }

            while(idx < n && stations[idx][0] <= currPos) {
                maxHeap.offer(stations[idx]);
                idx++;
            }

            if(!maxHeap.isEmpty()) {
                currPos += maxHeap.poll()[1];
                fuel++;
            }
        }

        return -1;
    }

    /*
        state:
            dp[i][j] means max fuel in tank at ith stop with j times refuel
        transition:
            dp[i][j] = max(dp[i - 1][j - 1] + s[i][1], dp[i - 1][j])
                       if dp[i - 1][j - 1] >= s[i][0] and dp[i - 1][j] > s[i][0]
    */
    private int tabulationSolution(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        int[][] dp = new int[n + 1][n + 1];

        dp[0][0] = startFuel;
        for(int i = 1; i < n + 1; i++) {
            dp[i][0] = dp[i - 1][0] >= stations[i - 1][0] ? dp[i - 1][0] : -1;
            for(int j = 1; j <= i; j++) {
                int fuel = dp[i - 1][j - 1] >= stations[i - 1][0] ?
                        dp[i - 1][j - 1] + stations[i - 1][1] : -1;

                int skip = dp[i - 1][j] >= stations[i - 1][0] ?
                        dp[i - 1][j] : -1;

                dp[i][j] = Math.max(fuel, skip);
            }
        }

        for(int j = 0; j < n + 1; j++) {
            if(dp[n][j] >= target) {
                return j;
            }
        }

        return -1;
    }

    // TLE
    private int recursionNaive(int target, int startFuel, int[][] stations) {
        int n = stations.length;

        int[][] s = new int[n + 2][2];
        s[0] = new int[] {0, 0};
        s[n + 1] = new int[] {target, 0};
        for(int i = 0; i < n; i++) {
            s[i + 1] = stations[i];
        }

        return dfs(s, 1, startFuel, new HashMap<>());
    }

    private int dfs(int[][] s, int i, int feulLeft, Map<String, Integer> memo) {
        int n = s.length;

        int cost = s[i][0] - s[i - 1][0];

        // reach end
        if(i == n - 1) {
            return feulLeft - cost < 0 ? -1 : 0;
        }


        String key = new StringBuilder().append(i).append("-").append(feulLeft).toString();
        if(memo.containsKey(key)) {
            return memo.get(key);
        }

        // not reachable
        if(feulLeft - cost < 0) {
            memo.put(key, -1);
            return -1;
        }

        int refuel = dfs(s, i + 1, feulLeft - cost + s[i][1], memo);
        int skip = dfs(s, i + 1, feulLeft - cost, memo);

        int min = 0;
        if(refuel == -1 && skip == -1) {
            min = -1;
        } else if(refuel == -1 || skip == -1) {
            min = refuel == -1 ? skip : refuel + 1;
        } else {
            min = Math.min(skip, refuel + 1);
        }

        memo.put(key, min);

        return min;
    }
}
