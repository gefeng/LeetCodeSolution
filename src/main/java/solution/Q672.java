package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Bulb Switcher II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/bulb-switcher-ii/"
)
public class Q672 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int flipLights(int n, int presses) {
        if(presses == 0) return 1;

        n = Math.min(n, 20);

        int[] op = new int[4];
        op[0] = 0xFFFFFFFF;
        op[1] = 0xAAAAAAAA;
        op[2] = 0x55555555;

        for(int i = 0; i < 32; i += 3) {
            op[3] |= 1 << i;
        }

        int mask = (1 << n) - 1;
        Set<Integer> seen = new HashSet<>();
        for(int i = 1; i < (1 << 4); i++) {
            if(Integer.bitCount(i) <= presses) {
                int cur = mask;
                for(int j = 0; j < 4; j++) {
                    if((i & (1 << j)) != 0) {
                        cur ^= op[j] & mask;
                        seen.add(cur);
                    }
                }
            }
        }

        return seen.size();
    }
}
