package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check If Array Pairs Are Divisible by k",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/"
)
public class Q1497 {
    /**
     * Modular operation indicates a implicit range from 0 inclusive to divisor k exclusive
     *   * Time:  O(N)
     * Space: O(K)
     * */
    public boolean canArrange(int[] arr, int k) {
        int n = arr.length;

        int[] freq = new int[k];

        for(int num : arr) {
            int rem = (num % k + k) % k;
            freq[rem]++;
        }

        for(int i = 1; i < k; i++) {
            if(freq[i] != freq[k - i]) {
                return false;
            }
        }

        return freq[0] % 2 == 0;
    }
}
