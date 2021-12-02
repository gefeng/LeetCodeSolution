package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "3Sum With Multiplicity",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/3sum-with-multiplicity/"
)
public class Q923 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int threeSumMulti(int[] arr, int target) {
        int n = arr.length;
        int ans = 0;

        for(int i = 0; i < n - 2; i++) {
            ans = (ans + twoSum(arr, i + 1, target - arr[i])) % MOD;
        }

        return ans;
    }

    private int twoSum(int[] arr, int st, int t) {
        int n = arr.length;
        int ans = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for(int i = st; i < n; i++) {
            ans = (ans + map.getOrDefault(t - arr[i], 0)) % MOD;
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        return ans;
    }
}
