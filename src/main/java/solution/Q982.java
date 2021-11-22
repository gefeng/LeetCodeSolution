package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Triples with Bitwise AND Equal To Zero",
        difficulty = QDifficulty.HARD,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/triples-with-bitwise-and-equal-to-zero/"
)
public class Q982 {
    /**
     * Time:  O(N ^ 2 + N * 2 ^ 16)
     * Space: O(N ^ 2)
     * */
    public int countTriplets(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int ans = 0;

        for(int x : nums) {
            for(int y : nums) {
                freqMap.put(x & y, freqMap.getOrDefault(x & y, 0) + 1);
            }
        }

        for(int x : nums) {
            int y = x ^ 0x0000FFFF;
            for(int sm = y; sm > 0; sm = (sm - 1) & y) {
                ans += freqMap.getOrDefault(sm, 0);
            }
            ans += freqMap.getOrDefault(0, 0);
        }

        return ans;
    }
}
