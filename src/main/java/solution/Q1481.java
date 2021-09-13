package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Least Number of Unique Integers after K Removals",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/"
)
public class Q1481 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        int n = arr.length;
        int ans = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();

        for(int i = 0; i < n; i++) {
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i], 0) + 1);
        }

        ans = freqMap.size();
        int[] cntOcc = new int[n + 1];
        for(int freq : freqMap.values()) {
            cntOcc[freq]++;
        }

        for(int i = 1; i <= n; i++) {
            if(cntOcc[i] == 0) {
                continue;
            }

            ans -= Math.min(k / i, cntOcc[i]);
            k = Math.max(0, k - cntOcc[i] * i);

            if(k == 0) {
                break;
            }
        }

        return ans;
    }
}
