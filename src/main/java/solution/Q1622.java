package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Fancy Sequence",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/fancy-sequence/"
)
public class Q1622 {
    /**
     * key observations:
     *  1. append x -> add 2: x + 2 -> mul 3: 3x + 6 -> add 5: 3x + 11 -> x = (val - accum_add) / accum_mul
     *  2. since mod is involved, we can't use divide instead we should use inverse modulo. (Fermat's little theorem)
     *
     * Time:  O(1)
     * Space: O(N)
     * */
    private static final int MOD = (int)1e9 + 7;
    private int inc;
    private int mul;
    private List<Integer> nums;
    public Q1622() {
        inc = 0;
        mul = 1;
        nums = new ArrayList<>();
    }

    public void append(int val) {
        val = (int)(((long)MOD + val - inc) % MOD * modInverse(mul) % MOD);
        nums.add(val);
    }

    public void addAll(int inc) {
        this.inc = (int)(((long)this.inc + inc) % MOD);
    }

    public void multAll(int m) {
        this.inc = (int)(((long)this.inc * m) % MOD);
        this.mul = (int)(((long)this.mul * m) % MOD);
    }

    public int getIndex(int idx) {
        if(idx >= nums.size()) {
            return -1;
        }

        return (int)(((long)nums.get(idx) * mul % MOD + inc) % MOD);
    }

    // Fermat's little theorem
    private int modInverse(int val) {
        return modPower(val, MOD - 2);
    }

    // quick power
    private int modPower(int x, int y) {
        int res = 1;

        while(y > 0) {
            if(y % 2 == 1) {
                res = (int)((long)res * x % MOD);
            }

            y /= 2;
            x = (int)((long)x * x % MOD);
        }

        return res;
    }
}
