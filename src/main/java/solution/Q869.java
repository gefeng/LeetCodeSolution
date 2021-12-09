package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Reordered Power of 2",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/reordered-power-of-2/"
)
public class Q869 {
    /**
     * Time:  O(logN)
     * Space: O(logN)
     * */
    public boolean reorderedPowerOf2(int n) {
        int[] f = cnt(n);

        for(int i = 0; i < 31; i++) {
            if(Arrays.equals(f, cnt(1 << i))) {
                return true;
            }
        }

        return false;
    }

    private int[] cnt(int x) {
        int[] f = new int[10];
        while(x != 0) {
            f[x % 10]++;
            x /= 10;
        }
        return f;
    }
}
