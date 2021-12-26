package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Abbreviating the Product of a Range",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/abbreviating-the-product-of-a-range/"
)
public class Q2117 {
    /**
     * count trailing zeros:
     *  count 2 and 5 when doing prime factorization
     *  since 2 and 5 are the only two prime factors of 10
     *  #trailing_zeros = min(#factor_two, #factor_five)
     * find last 5 digits:
     *  tail = (tail * j) % 10^5
     *  j is the result after applying 2 & 5 factorization of current_number
     *  do mod 10^10 in implementation to check if it has more 10 digits.
     * find first 5 digits:
     *  head *= current_number
     *  while(head >= 100000) head /= 10
     *
     * Time:  O(N * logN)
     * Space: O(1)
     * */
    public String abbreviateProduct(int left, int right) {
        int cnt2 = 0;
        int cnt5 = 0;

        for(int i = left; i <= right; i++) {
            for(int j = i; j % 2 == 0; j /= 2) {
                cnt2++;
            }
            for(int j = i; j % 5 == 0; j /= 5) {
                cnt5++;
            }
        }

        int tz = Math.min(cnt2, cnt5);

        cnt2 = 0;
        cnt5 = 0;
        long tail = 1L;
        double head = 1L;
        long p = 1L;
        long mod = (long)1e10;
        boolean ten = false;
        for(int i = left; i <= right; i++) {
            int j = i;
            for(; j % 2 == 0 && cnt2 < tz; j /= 2) {
                cnt2++;
            }
            for(; j % 5 == 0 && cnt5 < tz; j /= 5) {
                cnt5++;
            }

            if(tail * j > mod) {
                ten = true;
            }

            tail = (tail * j) % mod;

            head = head * i;
            while(head >= 100000) {
                head = head / 10;
            }
        }
        if(ten) {
            return (int)head + "..." + String.format("%05d", (tail % 100000)) + "e" + tz;
        }

        return tail + "e" + tz;
    }
}
