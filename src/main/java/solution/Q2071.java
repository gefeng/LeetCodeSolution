package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.TreeMap;

@Problem(
        title = "Maximum Number of Tasks You Can Assign",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/maximum-number-of-tasks-you-can-assign/"
)
public class Q2071 {
    /**
     * Time:  O(M * logM + N * logN + log(min(M, N)) * min(M, N) * log(min(M, N)))
     * Space: O(min(M, N))
     * */
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        int n = tasks.length;
        int m = workers.length;

        Arrays.sort(tasks);
        Arrays.sort(workers);

        int lo = 0;
        int hi = Math.min(m, n);
        int ans = 0;
        while(lo <= hi) {
            int mid = lo + hi >> 1;

            boolean isOk = canAssign(tasks, workers, pills, strength, mid);

            if(isOk) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    /*
         [5, 6, 7]
         [1, 6, 8]  p = 3, s = 4

         MUST start with the most difficult task, see the above example.
    */
    private boolean canAssign(int[] tasks, int[] workers, int pills, int strength, int k) {
        int n = tasks.length;
        int m = workers.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for(int i = m - k; i < m; i++) {
            map.put(workers[i], map.getOrDefault(workers[i], 0) + 1);
        }

        for(int i = k - 1; i >= 0; i--) {
            int t = tasks[i];

            Integer c = map.ceilingKey(t);
            if(c != null) {
                map.put(c, map.get(c) - 1);
                if(map.get(c) == 0) {
                    map.remove(c);
                }
            } else {
                if(pills > 0) {
                    c = map.ceilingKey(t - strength);
                    if(c != null) {
                        map.put(c, map.get(c) - 1);
                        if(map.get(c) == 0) {
                            map.remove(c);
                        }
                        pills--;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}
