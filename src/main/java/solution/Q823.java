package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Binary Trees With Factors",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/binary-trees-with-factors/"
)
public class Q823 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    private static final long MOD = (long)1e9 + 7;
    public int numFactoredBinaryTrees(int[] arr) {
        int n = arr.length;
        long ans = 0;

        Arrays.sort(arr);

        Set<Integer> set = new HashSet<>();
        for(int x : arr) set.add(x);

        Map<Integer, Long> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            int root = arr[i];
            long tot = 1;
            for(int j = 0; j < i; j++) {
                if(root % arr[j] == 0 && set.contains(root / arr[j])) {
                    int f1 = arr[j];
                    int f2 = root / arr[j];
                    tot = (tot + map.get(f1) * map.get(f2) % MOD) % MOD;
                }
            }

            map.put(root, tot);
            ans = (ans + tot) % MOD;
        }

        return (int)ans;
    }
}
