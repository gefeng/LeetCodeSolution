package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Unique Good Subsequence",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-unique-good-subsequences/"
)
public class Q1987 {
    /**
     * The idea is to save previous total unique subsequence ending with 1 and 0.
     * We will reproduce subsequence by appending 1 or 0 which generate duplicates.
     * For instance, we have 101 and we append 1.
     * for 101,
     * subsequence not counting leading 0: 1, 10, 11, 101
     * subsequence ending with 1: 1, 11, 101
     * for 1011,
     * ideally the we would append 1 to all previous subsequence plus 1 it self,
     * 1, 10, 11, 101, 11, 101, 111, 1011, 1
     * now we need to remove duplicates, we remove those subsequence previous ending with 1 ,
     * 10, 11, 101, 111, 1011, 1
     * update ending with 1 subsequence counter and repeat. The same for 0.
     *
     * Time:  O(N)
     * SpaceL O(1)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int numberOfUniqueGoodSubsequences(String binary) {
        int n = binary.length();
        long cur = 0;
        long end1 = 0;
        long end0 = 0;
        long hasZero = 0;

        if(binary.charAt(0) == '0') {
            hasZero = 1;
        } else {
            end1 = 1;
            cur = 1;
        }

        for(int i = 1; i < n; i++) {
            char c = binary.charAt(i);
            long next = 0;
            if(c == '0') {
                hasZero = 1;
                next = (MOD + (cur * 2) % MOD - end0) % MOD;
                end0 = cur % MOD;
            } else {
                next = (MOD + (cur * 2) % MOD - end1 + 1) % MOD;
                end1 = (cur + 1) % MOD;
            }

            cur = next % MOD;
        }

        return (int)((cur + hasZero) % MOD);
    }
}
