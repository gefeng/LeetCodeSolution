package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Nth Digit",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/nth-digit/"
)
public class Q400 {
    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    public int findNthDigit(int n) {
        long[] cnt = new long[11];

        long k = 1;
        for(int i = 1; i < 11; i++) {
            cnt[i] = (k * 10 - k) * i;
            k *= 10;
        }

        long sum = 0;
        long base = 1;
        long digits = 0;
        for(int i = 1; i < 11; i++) {
            if(sum + cnt[i] >= n) {
                digits = i;
                break;
            }
            sum += cnt[i];
            base *= 10;
        }

        long num = base;
        long lo = base;
        long hi = base * 10 - 1;
        while(lo <= hi) {
            long mid = lo + (hi - lo) / 2;

            if(sum + (mid - base + 1) * digits >= n) {
                num = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        sum += (num - base) * digits;

        long left = n - sum;
        long res = 0;
        while(left != 0) {
            res = num / base;
            num = num - res * base;
            base /= 10;
            left--;
        }

        return (int)res;
    }
}
