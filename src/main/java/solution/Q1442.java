package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Triplets That Can Form Two Arrays of Equal XOR",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/"
)
public class Q1442 {
    /**
     * Came up with the O(N ^ 3) solution during contest. But we can do better.
     * Notice that a == b which means a ^ b == 0 and a, b is adjacent =>
     * xor(a + b) == 0
     * if we find a subarray whose xor is 0 we can add it's length - 1 to answer.
     * i.e. (2 4 8 14) let k be the split point and any k between 1 to 3 is a valid
     * split which means we have 3 answer for this case.
     *
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int countTriplets(int[] arr) {
        int n = arr.length;
        int ans = 0;
        int[] preSum = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] ^ arr[i - 1];
        }

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if((preSum[j + 1] ^ preSum[i]) == 0) {
                    ans += j - i;
                }
            }
        }

        return ans;
    }
}
