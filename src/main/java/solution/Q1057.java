package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Campus Bikes",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/campus-bikes/"
)
public class Q1057 {
    /**
     * Time:  O(M * N * log(M * N))
     * Space: O(M * N)
     * */
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        int[] ans = new int[n];

        int[][] arr = new int[m * n][3];

        for(int i = 0, k = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int[] w = workers[i];
                int[] b = bikes[j];

                arr[k++] = new int[] {i, j, Math.abs(w[0] - b[0]) + Math.abs(w[1] - b[1])};
            }
        }

        Arrays.sort(arr, (a, b) -> {
            if(a[2] == b[2]) {
                if(a[0] == b[0]) {
                    return Integer.compare(a[1], b[1]);
                }

                return Integer.compare(a[0], b[0]);
            }

            return Integer.compare(a[2], b[2]);
        });

        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for(int[] x : arr) {
            if(set1.contains(x[0]) || set2.contains(x[1])) continue;

            ans[x[0]] = x[1];

            set1.add(x[0]);
            set2.add(x[1]);
        }

        return ans;
    }
}
