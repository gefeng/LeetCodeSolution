package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.math.BigInteger;

@Problem(
        title = "Maximize Number of Nice Divisors",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/maximize-number-of-nice-divisors/"
)
public class Q1808 {
    private static final int MOD = (int)1e9 + 7;
    public int maxNiceDivisors(int primeFactors) {
        if(primeFactors <= 4) {
            return primeFactors;
        }

        BigInteger x = new BigInteger(Integer.toString(primeFactors));
        BigInteger mod = new BigInteger("1000000007");
        BigInteger base3 = new BigInteger("3");
        BigInteger base4 = new BigInteger("4");
        BigInteger base2 = new BigInteger("2");

        if(primeFactors % 3 == 0) {
            return base3.modPow(x.divide(base3), mod).intValue();
        }
        if(primeFactors % 3 == 1) {
            return base3.modPow(x.subtract(base4).divide(base3), mod).multiply(base4).mod(mod).intValue();
        }

        return base3.modPow(x.subtract(base2).divide(base3), mod).multiply(base2).mod(mod).intValue();
    }
}
