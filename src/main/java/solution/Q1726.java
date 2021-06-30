package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Tuple with Same Product",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/tuple-with-same-product/"
)
public class Q1726 {
    public int tupleSameProduct(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> freqMap = new HashMap<>();

        for(int i = 0; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                int prod = nums[i] * nums[j];
                freqMap.put(prod, freqMap.getOrDefault(prod, 0) + 1);
            }
        }

        int cnt = 0;
        for(int prod : freqMap.keySet()) {
            int freq = freqMap.get(prod);
            if(freq < 2) {
                continue;
            }

            cnt += perm(freq);
        }

        return cnt;
    }

    // 2 * 2P2 * 2P(freq)
    private int perm(int n) {
        int res = n * (n - 1);
        return res * 4;
    }
}
