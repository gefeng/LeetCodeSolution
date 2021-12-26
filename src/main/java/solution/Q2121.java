package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Intervals Between Identical Elements",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/intervals-between-identical-elements/"
)
public class Q2121 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public long[] getDistances(int[] arr) {
        int n = arr.length;
        long[] ans = new long[n];

        Map<Integer, List<Integer>> map = new HashMap<>();


        for(int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        for(int i : map.keySet()) {
            List<Integer> l = map.get(i);
            int m = l.size();

            long[] dpl = new long[m];
            long[] dpr = new long[m];
            dpl[0] = 0;
            dpr[m - 1] = 0;
            for(int j = 1; j < m; j++) {
                dpl[j] = dpl[j - 1] + ((long)l.get(j) - l.get(j - 1)) * j;
            }

            for(int j = m - 2; j >= 0; j--) {
                dpr[j] = dpr[j + 1] + ((long)l.get(j + 1) - l.get(j)) * (m - j - 1);
            }

            for(int j = 0; j < m; j++) {
                ans[l.get(j)] = dpl[j] + dpr[j];
            }
        }

        return ans;
    }
}
