package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Prime Number of Set Bits in Binary Representation",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/"
)
public class Q762 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for(int i = left; i <= right; i++) {
            if(isPrime(Integer.bitCount(i))) {
                ans++;
            }
        }

        return ans;
    }

    private boolean isPrime(int x) {
        if(x == 1) return false;
        if(x == 2) return true;

        for(int i = 2; i * i <= x; i++) {
            if(x % i == 0) return false;
        }

        return true;
    }
}
